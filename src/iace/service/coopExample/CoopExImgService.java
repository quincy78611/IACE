package iace.service.coopExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import iace.dao.IBaseIaceDao;
import iace.entity.coopExample.CoopExImg;
import iace.service.BaseIaceService;

public class CoopExImgService extends BaseIaceService<CoopExImg> {

	private String coopExampleFolder;
	
	public CoopExImgService(IBaseIaceDao<CoopExImg> dao) {
		super(dao);
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.coopExampleFolder = prop.getProperty("coopExampleFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}
	
	public FileInputStream getImageIS(CoopExImg entity) throws FileNotFoundException {
		File f = new File(this.coopExampleFolder, entity.getFilePath());
		FileInputStream fis = new FileInputStream(f);
		return fis;
	}
	
	public FileInputStream getImageIS(long id) throws FileNotFoundException {
		CoopExImg entity = this.get(id);
		return getImageIS(entity);
	}

}
