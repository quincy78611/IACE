package iace.service.coopExample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import core.util.PagedList;
import core.util.ThumbnailUtil;
import iace.dao.coopExample.ICoopExAttachFileDao;
import iace.dao.coopExample.ICoopExDao;
import iace.dao.coopExample.ICoopExImgDao;
import iace.dao.coopExample.ICoopExVideoDao;
import iace.entity.coopExample.CoopEx;
import iace.entity.coopExample.CoopExAttachFile;
import iace.entity.coopExample.CoopExFile;
import iace.entity.coopExample.CoopExImg;
import iace.entity.coopExample.CoopExSearchModel;
import iace.entity.coopExample.CoopExVideo;
import iace.service.BaseIaceService;

public class CoopExService extends BaseIaceService<CoopEx> {
	private ICoopExDao coopExDao;
	private ICoopExImgDao coopExImgDao;
	private ICoopExVideoDao coopExVideoDao;
	private ICoopExAttachFileDao coopExAttachFileDao;
	
	private String coopExampleFolder;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public CoopExService(ICoopExDao dao, ICoopExImgDao coopExImgDao, ICoopExVideoDao coopExVideoDao, ICoopExAttachFileDao coopExAttachFileDao) {
		super(dao);
		this.coopExDao = dao;
		this.coopExImgDao = coopExImgDao;
		this.coopExVideoDao = coopExVideoDao;
		this.coopExAttachFileDao = coopExAttachFileDao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.coopExampleFolder = prop.getProperty("coopExampleFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}
	
	public boolean isProjNameExist(long currentId, String name) {
		return this.coopExDao.isProjNameExist(currentId, name);
	}

	@Override
	public List<CoopEx> listAll() {
		List<CoopEx> list = super.listAll();
		for (CoopEx entity : list) {
			// load only first image
			if (entity.getImgs() != null && entity.getImgs().size() > 0) {
				File f = new File(this.coopExampleFolder, entity.getImgs().get(0).getFilePath());
				byte[] imgData = loadImg(f);
				entity.getImgs().get(0).setByteImg(imgData);
			}
		}	
		
		return list;
	}
	
	public PagedList<CoopEx> searchBy(CoopExSearchModel arg) {
		PagedList<CoopEx> list = this.coopExDao.searchBy(arg);
		for (CoopEx entity : list.getList()) {
			// load thumbnail
			if (entity.getImgs() != null && entity.getImgs().size() > 0) {
				File f = getThumbnailFile(entity);
				byte[] imgData = loadImg(f);
				entity.setThumbnail(imgData);
			}
		}
		return list;
	}
	
	@Override
	public CoopEx get(Long id) {
		CoopEx entity = this.dao.get(id);
		loadAllEntityImg(entity);
		// read & set attach file size
		if (entity.getAttachFiles() != null && entity.getAttachFiles().size() > 0) {
			for (CoopExAttachFile attach : entity.getAttachFiles()) {
				File f =new File(this.coopExampleFolder, attach.getFilePath());
				attach.setContentLength(f.length());
			}
		}
		
		return entity;
	}

	@Override
	public void create(CoopEx entity) throws IOException, SQLException {
		saveFile(entity);
		
		// produce and save thumbnail
		produceAndSaveThumbnail(entity);
		
		super.create(entity);
		
		// reload images
		loadAllEntityImg(entity);
	}
	
	@Override
	public void update(CoopEx entity) throws IOException, SQLException {
		CoopEx entityO = this.coopExDao.get(entity.getId());
		
		// (copy and rename unchanged files) and (save new upload file)
		updateFile(entity);
		
		// delete all old files because 
		// 1. all unchanged files have been copied and renamed
		// 2. don't need those files which have been changed
		deleteFile(entityO);
		
		// produce and save thumbnail
		produceAndSaveThumbnail(entity);
		
		// if projName changed, remove old folder;
		if (StringUtils.equals(entity.getProjName(), entityO.getProjName()) == false) {
			File oldDir = new File(this.coopExampleFolder, entityO.getProjName());
			oldDir.delete();
		}
		
		// deal with DB record
		removeRecordFromDb(entity, entityO);
		super.update(entity);
		
		// reload images
		loadAllEntityImg(entity);
	}

	@Override
	public void delete(CoopEx entity) throws IOException, SQLException {
		deleteFile(entity);
		getThumbnailFile(entity).delete();
		File dir = new File(this.coopExampleFolder, entity.getProjName());
		dir.delete();
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		CoopEx entity = this.coopExDao.get(id);
		delete(entity);
	}

	/** save new upload file :   
	 * 1. remove empty file from new upload file list. 
	 * 2.1. save new upload files. 
	 * 2.2. set entity's data fields (for each imgs, videos, attachFiles) 
	 * @param entity
	 */
	private void saveFile(CoopEx entity) {
		File dir = new File(this.coopExampleFolder, entity.getProjName());
		if (!dir.exists()) {
			dir.mkdirs();  
		}
		
		// remove empty file
		List<CoopExImg> imgs = new ArrayList<CoopExImg>();
		List<CoopExVideo> videos = new ArrayList<CoopExVideo>();
		List<CoopExAttachFile> attachFiles = new ArrayList<CoopExAttachFile>();
		for (CoopExImg f:entity.getImgs()) {
			if (f.hasUploadFile()) {
				imgs.add(f);
			}
		}
		for (CoopExVideo f:entity.getVideos()) {
			if (f.hasUploadFile()) {
				videos.add(f);
			}
		}
		for (CoopExAttachFile f:entity.getAttachFiles()) {
			if (f.hasUploadFile()) {
				attachFiles.add(f);
			}
		}
		entity.setImgs(imgs);
		entity.setVideos(videos);
		entity.setAttachFiles(attachFiles);
		
		// save image files
		for (int i=0; i<entity.getImgs().size(); i++) {
			CoopExImg img = entity.getImgs().get(i);
			setDataAndSaveImgForCoopExImg(entity, img, i);
		}
		// save video files
		for (int i=0; i<entity.getVideos().size(); i++) {
			CoopExVideo video = entity.getVideos().get(i);
			setDataAndSaveVideoForCoopExVideo(entity, video, i);
		}		
		// save attach files
		for (int i=0; i<entity.getAttachFiles().size(); i++) {
			CoopExAttachFile attach = entity.getAttachFiles().get(i);
			setDataAndSaveAttachForCoopExAttachFile(entity, attach, i);
		}		
	}
	
	/** (copy and rename unchanged files) and (save new upload file) :   
	 * 1. remove empty file from new upload file list. 
	 * 2.1. copy those unchanged files and rename.
	 * 2.2. save new upload or update files. 
	 * 2.3. set entity's data fields (for each imgs, videos, attachFiles) 
	 * @param entity
	 */
	private void updateFile(CoopEx entity) {
		File dir = new File(this.coopExampleFolder, entity.getProjName());
		if (!dir.exists()) {
			dir.mkdirs();  
		}
		
		// remove empty file from new upload files
		List<CoopExImg> imgs = new ArrayList<CoopExImg>();
		List<CoopExVideo> videos = new ArrayList<CoopExVideo>();
		List<CoopExAttachFile> attachFiles = new ArrayList<CoopExAttachFile>();
		for (CoopExImg f:entity.getImgs()) {
			if (f.getId() > 0) { 
				imgs.add(f); // keep old data
			} else if (f.hasUploadFile()) {
				imgs.add(f); // keep not null new data
			}
		}
		for (CoopExVideo f:entity.getVideos()) {
			if (f.getId() > 0) { 
				videos.add(f); // keep old data
			} else if (f.hasUploadFile()) {
				videos.add(f); // keep not null new data
			}
		}
		for (CoopExAttachFile f:entity.getAttachFiles()) {
			if (f.getId() > 0) { 
				attachFiles.add(f); // keep old data
			} else if (f.hasUploadFile()) {
				attachFiles.add(f); // keep not null new data
			}
		}
		entity.setImgs(imgs);
		entity.setVideos(videos);
		entity.setAttachFiles(attachFiles);
		
		// update image files
		for (int i=0; i<entity.getImgs().size(); i++) {
			CoopExImg img = entity.getImgs().get(i);
			if (img.getId() > 0) { // origin file
				CoopExImg imgO = this.coopExImgDao.get(img.getId());
				img.setIsValid(imgO.getIsValid());
				img.setCreateTime(imgO.getCreateTime());
				img.setCreateUser(imgO.getCreateUser());
				img.setUpdateTime(imgO.getUpdateTime());
				img.setUpdateUser(imgO.getUpdateUser());
				img.setVer(imgO.getVer());
				
				if (img.hasUploadFile() == false) {
					// set current file as upload file
					img.setUpload(new File(this.coopExampleFolder, img.getFilePath()));
					img.setUploadContentType(img.getFileContentType());
					img.setUploadFileName(img.getFileName());
				}
			} else { // new file
				img.create();
			}
			setDataAndSaveImgForCoopExImg(entity, img, i);
		}

		// update video files
		for (int i=0; i<entity.getVideos().size(); i++) {
			CoopExVideo video = entity.getVideos().get(i);
			if (video.getId() > 0) { // origin file
				CoopExVideo videoO = this.coopExVideoDao.get(video.getId());
				video.setIsValid(videoO.getIsValid());
				video.setCreateTime(videoO.getCreateTime());
				video.setCreateUser(videoO.getCreateUser());
				video.setUpdateTime(videoO.getUpdateTime());
				video.setUpdateUser(videoO.getUpdateUser());
				video.setVer(videoO.getVer());
				
				if (video.hasUploadFile() == false) {
					// set current file as upload file
					video.setUpload(new File(this.coopExampleFolder, video.getFilePath()));
					video.setUploadContentType(video.getFileContentType());
					video.setUploadFileName(video.getFileName());
				}
			} else { // new file
				video.create();
			}
			setDataAndSaveVideoForCoopExVideo(entity, video, i);
		}
		
		// update attach files
		for (int i=0; i<entity.getAttachFiles().size(); i++) {
			CoopExAttachFile attach = entity.getAttachFiles().get(i);
			if (attach.getId() > 0) { // origin file
				CoopExAttachFile attachO = this.coopExAttachFileDao.get(attach.getId());
				attach.setIsValid(attachO.getIsValid());
				attach.setCreateTime(attachO.getCreateTime());
				attach.setCreateUser(attachO.getCreateUser());
				attach.setUpdateTime(attachO.getUpdateTime());
				attach.setUpdateUser(attachO.getUpdateUser());
				attach.setVer(attachO.getVer());
				
				if (attach.hasUploadFile() == false) {
					// set current file as upload file
					attach.setUpload(new File(this.coopExampleFolder, attach.getFilePath()));
					attach.setUploadContentType(attach.getFileContentType());
					attach.setUploadFileName(attach.getFileName());
				}
			} else { // new file
				attach.create();
			}
			setDataAndSaveAttachForCoopExAttachFile(entity, attach, i);
		}
	}
	
	private File getThumbnailFile(CoopEx entity) {
		File path = new File(entity.getProjName(), "thumbnail.jpg");
		return new File(this.coopExampleFolder, path.getPath());
	}
	
	private void produceAndSaveThumbnail(CoopEx entity) throws IOException {
		if (entity.getImgs() != null && entity.getImgs().size() > 0) {
			byte[] firstImg;
			firstImg = loadImg(new File(this.coopExampleFolder, entity.getImgs().get(0).getFilePath()));
			byte[] thumbnail = ThumbnailUtil.resize(firstImg, 720, 540, true);
			OutputStream out = new FileOutputStream(getThumbnailFile(entity));
			out.write(thumbnail);
			out.flush();
			out.close();
		}
	}
	
	private void setDataAndSaveImgForCoopExImg(CoopEx entity, CoopExImg img, int i) {
		String fileExtension = FilenameUtils.getExtension(img.getUploadFileName());
		String time = sdf.format(System.currentTimeMillis());
		String fileName = "Img"+i+"_"+time+"."+fileExtension;
		String path = new File(entity.getProjName(), fileName).getPath();
		img.setFilePath(path);
		img.setFileContentType(img.getUploadContentType());
		img.setFileName(img.getUploadFileName());
		
		// copy temp-upload-file to "coopExampleFolder"
		img.getUpload().renameTo(new File(this.coopExampleFolder, path));
	}
	
	private void setDataAndSaveVideoForCoopExVideo(CoopEx entity, CoopExVideo video, int i) {
		String fileExtension = FilenameUtils.getExtension(video.getUploadFileName());
		String time = sdf.format(System.currentTimeMillis());
		String fileName = "Video"+i+"_"+time+"."+fileExtension;
		String path = new File(entity.getProjName(), fileName).getPath();
		video.setFilePath(path);
		video.setFileContentType(video.getUploadContentType());
		video.setFileName(video.getUploadFileName());
		
		// copy temp-upload-file to "coopExampleFolder"
		video.getUpload().renameTo(new File(this.coopExampleFolder, path));
	}
	
	private void setDataAndSaveAttachForCoopExAttachFile(CoopEx entity, CoopExAttachFile attach, int i) {
		String fileExtension = FilenameUtils.getExtension(attach.getUploadFileName());
		String time = sdf.format(System.currentTimeMillis());
		String fileName = "Attach"+i+"_"+time+"."+fileExtension;
		String path = new File(entity.getProjName(), fileName).getPath();
		attach.setFilePath(path);
		attach.setFileContentType(attach.getUploadContentType());
		attach.setFileName(attach.getUploadFileName());
		
		// copy temp-upload-file to "coopExampleFolder"
		attach.getUpload().renameTo(new File(this.coopExampleFolder, path));
	}

	/**
	 * remove {entityO}'s file(image, video, attach) DB record which is not in {entity}
	 * @param entity : current entity
	 * @param entityO : old entity
	 */
	private void removeRecordFromDb(CoopEx entity, CoopEx entityO) {
		Set<Long> imgIdSet = new HashSet<Long>();
		for (CoopExImg f : entity.getImgs()) {
			if (f.getId() > 0) {
				imgIdSet.add(f.getId());
			}
		}
		for (CoopExImg f : entityO.getImgs()) {
			if (imgIdSet.contains(f.getId()) == false) {
				this.coopExImgDao.delete(f);
			}
		}
		
		Set<Long> videoIdSet = new HashSet<Long>();
		for (CoopExVideo f : entity.getVideos()) {
			if (f.getId() > 0) {
				videoIdSet.add(f.getId());
			}
		}
		for (CoopExVideo f : entityO.getVideos()) {
			if (videoIdSet.contains(f.getId()) == false) {
				this.coopExVideoDao.delete(f);
			}
		}
		
		Set<Long> attachIdSet = new HashSet<Long>();
		for (CoopExAttachFile f : entity.getAttachFiles()) {
			if (f.getId() > 0) {
				attachIdSet.add(f.getId());
			}
		}
		for (CoopExAttachFile f : entityO.getAttachFiles()) {
			if (attachIdSet.contains(f.getId()) == false) {
				this.coopExAttachFileDao.delete(f);
			}
		}
	}
	
	/**
	 * delete all {entity}'s file(image, video, attach)
	 * @param entity
	 */
	private void deleteFile(CoopEx entity) {
		List<CoopExFile> files = new ArrayList<CoopExFile>();
		files.addAll(entity.getImgs());
		files.addAll(entity.getVideos());
		files.addAll(entity.getAttachFiles());
		for (CoopExFile file : files) {
			File f = new File(this.coopExampleFolder, file.getFilePath());
			f.delete();
		}
	}
	
	private void loadAllEntityImg(CoopEx entity) {
		for (CoopExImg img : entity.getImgs()) {
			File f = new File(this.coopExampleFolder, img.getFilePath());
			byte[] imgData = loadImg(f);
			img.setByteImg(imgData);
		}
	}
	
	private byte[] loadImg(File f) {
		try {
			byte[] data = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
			return data;
		} catch (IOException | NullPointerException e) {
			log.warn("load image fail");
			return null;
		}
	}
	
	public List<CoopEx> sampleForHomePage() {
		List<CoopEx> list = this.coopExDao.sampleForHomePage();
		for (CoopEx entity : list) {
			entity.setThumbnail(loadImg(getThumbnailFile(entity)));
		}
		return list;
	}
}
