package iace.service.rdFocus;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import iace.dao.rdFocus.IRdFocusAttachDao;
import iace.entity.rdFocus.RdFocusAttach;
import iace.service.BaseIaceService;

public class RdFocusAttachService extends BaseIaceService<RdFocusAttach> {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	protected IRdFocusAttachDao dao;
	
	private String rdFocusAttachFolder;
	
	public RdFocusAttachService(IRdFocusAttachDao dao) {
		super(dao);
		this.dao = dao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.rdFocusAttachFolder = prop.getProperty("rdFocusAttachFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}


	@Override
	public void create(RdFocusAttach entity) throws IOException, SQLException {
		if (entity.hasUpload()) {
			saveFile(entity);
		}
		super.create(entity);
	}

	@Override
	public void update(RdFocusAttach entity) throws IOException, SQLException {
		RdFocusAttach entityO = this.dao.get(entity.getId());
		entity.setIsValid(entityO.getIsValid());
		entity.setCreateTime(entityO.getCreateTime());
		entity.setCreateUser(entityO.getCreateUser());
		entity.setUpdateTime(entityO.getUpdateTime());
		entity.setUpdateUser(entityO.getUpdateUser());
		entity.setVer(entityO.getVer());
		
		if (entity.hasUpload()) {
			File f = new File(this.rdFocusAttachFolder, entityO.getFileSubPath());
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
	public void delete(RdFocusAttach entity) throws IOException, SQLException {
		File f = new File(this.rdFocusAttachFolder, entity.getFileSubPath());
		f.delete();
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		RdFocusAttach entity = this.dao.get(id);
		delete(entity);
	}
	
	private void saveFile(RdFocusAttach attach) throws IOException {
		attach.setFileFolder(this.rdFocusAttachFolder);
		String time = sdf.format(System.currentTimeMillis());
		String fileName = "rdFocus" + "_"+time+"_" + UUID.randomUUID().toString() + "_" + attach.getUploadFileName();
		attach.setFileSubPath(fileName);
		attach.saveUploadFile();
	}

	public String getRdFocusAttachFolder() {
		return this.rdFocusAttachFolder;
	}
	
	
}
