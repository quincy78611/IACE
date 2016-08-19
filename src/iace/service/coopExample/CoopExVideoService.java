package iace.service.coopExample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import iace.dao.coopExample.ICoopExVideoDao;
import iace.entity.coopExample.CoopExVideo;
import iace.service.BaseIaceService;

public class CoopExVideoService extends BaseIaceService<CoopExVideo> {

	private String coopExampleFolder;
	
	public CoopExVideoService(ICoopExVideoDao dao) {
		super(dao);
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.coopExampleFolder = prop.getProperty("coopExampleFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}
	
	public FileInputStream getVideoIS(CoopExVideo entity) throws FileNotFoundException {
		File f = new File(this.coopExampleFolder, entity.getFilePath());
		FileInputStream fis = new FileInputStream(f);
		return fis;
	}
	
	public FileInputStream getVideoIS(long id) throws FileNotFoundException {
		CoopExVideo entity = this.get(id);
		return getVideoIS(entity);
	}

}
