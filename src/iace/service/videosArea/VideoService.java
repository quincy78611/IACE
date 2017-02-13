package iace.service.videosArea;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import iace.dao.videosArea.IVideoDao;
import iace.entity.videosArea.Video;
import iace.service.BaseIaceService;

public class VideoService extends BaseIaceService<Video> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private IVideoDao dao;
	
	private String videoFolder;

	public VideoService(IVideoDao dao) {
		super(dao);
		this.dao = dao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.videoFolder = prop.getProperty("videoFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	@Override
	public void create(Video entity) throws IOException, SQLException {
		if (entity.hasUpload()) {
			saveFile(entity);
		}
		super.create(entity);
	}

	@Override
	public void update(Video entity) throws IOException, SQLException {
		Video entityO = this.dao.get(entity.getId());
		entity.setIsValid(entityO.getIsValid());
		entity.setCreateTime(entityO.getCreateTime());
		entity.setCreateUser(entityO.getCreateUser());
		entity.setUpdateTime(entityO.getUpdateTime());
		entity.setUpdateUser(entityO.getUpdateUser());
		entity.setVer(entityO.getVer());	
		
		if (entity.hasUpload()) {
			File f = new File(this.videoFolder, entityO.getFileSubPath());
			f.delete();
			saveFile(entity);
		} else {
			entity.setFileSubPath(entityO.getFileSubPath());
			entity.setUploadContentType(entityO.getUploadContentType());
			entity.setUploadFileName(entityO.getUploadFileName());
		}
		
		super.update(entity);
	}

	@Override
	public void delete(Video entity) throws IOException, SQLException {
		File f = new File(this.videoFolder, entity.getFileSubPath());
		f.delete();
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		Video entity = this.dao.get(id);
		delete(entity);
	}

	private void saveFile(Video entity) throws IOException {
		entity.setFileFolder(this.videoFolder);
		String time = sdf.format(System.currentTimeMillis());
		String fileName = "video" + "_"+time+"_" + UUID.randomUUID().toString() + "_" + entity.getUploadFileName();
		entity.setFileSubPath(fileName);
		entity.saveUploadFile();
	}

	public String getVideoFolder() {
		return videoFolder;
	}
	
}
