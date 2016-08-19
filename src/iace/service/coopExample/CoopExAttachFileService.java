package iace.service.coopExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import iace.dao.coopExample.ICoopExAttachFileDao;
import iace.entity.coopExample.CoopExAttachFile;
import iace.service.BaseIaceService;

public class CoopExAttachFileService extends BaseIaceService<CoopExAttachFile> {

	private String coopExampleFolder;
	
	public CoopExAttachFileService(ICoopExAttachFileDao dao) {
		super(dao);
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.coopExampleFolder = prop.getProperty("coopExampleFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}
	
	public FileInputStream getAttachFileIS(CoopExAttachFile entity) throws FileNotFoundException {
		File f = new File(this.coopExampleFolder, entity.getFilePath());
		FileInputStream fis = new FileInputStream(f);
		return fis;
	}
	
	public FileInputStream getAttachFileIS(long id) throws FileNotFoundException {
		CoopExAttachFile entity = this.get(id);
		return getAttachFileIS(entity);
	}

}
