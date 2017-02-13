package iace.service.activity;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import core.util.ThumbnailUtil;
import iace.dao.activity.IActivityAttachDao;
import iace.entity.DbFile;
import iace.entity.activity.ActivityAttach;
import iace.service.BaseIaceService;

public class ActivityAttachService extends BaseIaceService<ActivityAttach> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private IActivityAttachDao dao;
	
	private String activityAttachFolder;
	
	public ActivityAttachService(IActivityAttachDao dao) {
		super(dao);
		this.dao = dao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.activityAttachFolder = prop.getProperty("activityAttachFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	@Override
	public void create(ActivityAttach entity) throws IOException, SQLException {
		if (entity.hasUpload()) {
			saveFile(entity);
			produceThumbnail(entity);
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
			produceThumbnail(entity);
		} else {
			entity.setFileSubPath(entityO.getFileSubPath());
			entity.setUploadContentType(entityO.getUploadContentType());
			entity.setUploadFileName(entityO.getUploadFileName());
			if (entity.getFileType() == DbFile.FILE_TYPE_IMAGE) {
				if (entity.getFileType() != entityO.getFileType()) {
					produceThumbnail(entity);
				} else {
					entity.setThumbnail(entityO.getThumbnail());
				}
			}
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
	
	private void produceThumbnail(ActivityAttach attach) throws IOException {
		if (attach.getFileType() == ActivityAttach.FILE_TYPE_IMAGE) {
			attach.setFileFolder(this.activityAttachFolder);
			attach.loadFileContentFromDisk();
			byte[] thumbnail = ThumbnailUtil.resize(attach.getFileContent(), 400, 300, true, 1f);
			attach.setThumbnail(thumbnail);
			if (attach.isSaveInDisk()) {
				attach.setFileContent(null);
			}
		}
	}

	public String getActivityAttachFolder() {
		return activityAttachFolder;
	}
	
	
}
