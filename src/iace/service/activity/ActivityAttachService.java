package iace.service.activity;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import iace.dao.activity.IActivityAttachDao;
import iace.entity.activity.ActivityAttach;
import iace.service.BaseIaceService;

public class ActivityAttachService extends BaseIaceService<ActivityAttach> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private IActivityAttachDao activityAttachDao;
	
	private String activityAttachFolder;
	
	public ActivityAttachService(IActivityAttachDao dao) {
		super(dao);
		this.activityAttachDao = dao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.activityAttachFolder = prop.getProperty("activityAttachFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	@Override
	public ActivityAttach get(Long id) {
		ActivityAttach entity = this.activityAttachDao.get(id);
		entity.setFileFolder(this.activityAttachFolder);
		try {
			entity.loadFileContentFromDisk();
		} catch (Exception e) {
			log.warn("Load attach file fail!", e);
		}
		return entity;
	}

	@Override
	public void create(ActivityAttach entity) throws IOException, SQLException {
		if (entity.hasUpload()) {
			saveFile(entity);
		}
		super.create(entity);
	}

	@Override
	public void update(ActivityAttach entity) throws IOException, SQLException {
		ActivityAttach entityO = this.dao.get(entity.getId());
		entity.setIsValid(entityO.getIsValid());
		entity.setCreateTime(entityO.getCreateTime());
		entity.setCreateUser(entityO.getCreateUser());
		entity.setUpdateTime(entityO.getUpdateTime());
		entity.setUpdateUser(entityO.getUpdateUser());
		entity.setVer(entityO.getVer());
		
		if (entity.hasUpload()) {
			File f = new File(this.activityAttachFolder, entityO.getFileSubPath());
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
	public void delete(ActivityAttach entity) throws IOException, SQLException {
		File f = new File(this.activityAttachFolder, entity.getFileSubPath());
		f.delete();
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		ActivityAttach entity = this.dao.get(id);
		delete(entity);
	}
	
	private void saveFile(ActivityAttach attach) throws IOException {
		attach.setFileFolder(this.activityAttachFolder);
		String time = sdf.format(System.currentTimeMillis());
		String fileName = "activity" + "_"+time+"_" + UUID.randomUUID().toString() + "_" + attach.getUploadFileName();
		attach.setFileSubPath(fileName);
		attach.saveUploadFile();
	}

	public String getActivityAttachFolder() {
		return activityAttachFolder;
	}
	
	
}
