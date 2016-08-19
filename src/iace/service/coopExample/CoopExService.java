package iace.service.coopExample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

import iace.dao.coopExample.ICoopExDao;
import iace.entity.coopExample.CoopEx;
import iace.entity.coopExample.CoopExAttachFile;
import iace.entity.coopExample.CoopExImg;
import iace.entity.coopExample.CoopExVideo;
import iace.service.BaseIaceService;

public class CoopExService extends BaseIaceService<CoopEx> {

	private String coopExampleFolder;
	
	public CoopExService(ICoopExDao dao) {
		super(dao);
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.coopExampleFolder = prop.getProperty("coopExampleFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	@Override
	public List<CoopEx> listAll() {
		List<CoopEx> list = super.listAll();
		for (CoopEx entity : list) {
			File f = new File(this.coopExampleFolder, entity.getImgs().get(0).getFilePath());
			byte[] imgData = loadImg(f);
			entity.getImgs().get(0).setByteImg(imgData);
		}	
		
		return list;
	}

	@Override
	public CoopEx get(Long id) {
		CoopEx entity = this.dao.get(id);
		for (CoopExImg img : entity.getImgs()) {
			File f = new File(this.coopExampleFolder, img.getFilePath());
			byte[] imgData = loadImg(f);
			img.setByteImg(imgData);
		}
		
		//TODO video and attach
		
		return entity;
	}

	@Override
	public void create(CoopEx entity) throws IOException, SQLException {
		saveFile(entity);
		super.create(entity);
	}
	
	@Override
	public void update(CoopEx entity) throws IOException, SQLException {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void delete(CoopEx entity) throws IOException, SQLException {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		// TODO Auto-generated method stub
		super.delete(id);
	}

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
			if (f.getUpload() != null && f.getUploadContentType() != null && f.getUploadFileName() != null) {
				imgs.add(f);
			}
		}
		for (CoopExVideo f:entity.getVideos()) {
			if (f.getUpload() != null && f.getUploadContentType() != null && f.getUploadFileName() != null) {
				videos.add(f);
			}
		}
		for (CoopExAttachFile f:entity.getAttachFiles()) {
			if (f.getUpload() != null && f.getUploadContentType() != null && f.getUploadFileName() != null) {
				attachFiles.add(f);
			}
		}
		entity.setImgs(imgs);
		entity.setVideos(videos);
		entity.setAttachFiles(attachFiles);
		
		// save image files
		for (int i=0; i<entity.getImgs().size(); i++) {
			CoopExImg img = entity.getImgs().get(i);
			String fileExtension = FilenameUtils.getExtension(img.getUploadFileName());
			String fileName = "Img"+i+"."+fileExtension;
			String path = new File(entity.getProjName(), fileName).getPath();
			img.setFilePath(path);
			img.setFileContentType(img.getUploadContentType());
			img.setFileName(img.getUploadFileName());
			img.getUpload().renameTo(new File(this.coopExampleFolder, path));
		}
		// save video files
		for (int i=0; i<entity.getVideos().size(); i++) {
			CoopExVideo video = entity.getVideos().get(i);
			String fileExtension = FilenameUtils.getExtension(video.getUploadFileName());
			String fileName = "Video"+i+"."+fileExtension;
			String path = new File(entity.getProjName(), fileName).getPath();
			video.setFilePath(path);
			video.setFileContentType(video.getUploadContentType());
			video.setFileName(video.getUploadFileName());
			video.getUpload().renameTo(new File(this.coopExampleFolder, path));
		}		
		// save attach files
		for (int i=0; i<entity.getAttachFiles().size(); i++) {
			CoopExAttachFile attach = entity.getAttachFiles().get(i);
			String fileExtension = FilenameUtils.getExtension(attach.getUploadFileName());
			String fileName = "Attach"+i+"."+fileExtension;
			String path = new File(entity.getProjName(), fileName).getPath();
			attach.setFilePath(path);
			attach.setFileContentType(attach.getUploadContentType());
			attach.setFileName(attach.getUploadFileName());
			attach.getUpload().renameTo(new File(this.coopExampleFolder, path));
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
}
