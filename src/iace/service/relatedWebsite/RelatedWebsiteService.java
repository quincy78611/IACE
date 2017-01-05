package iace.service.relatedWebsite;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import iace.dao.relatedWebsite.IRelatedWebsiteDao;
import iace.entity.relatedWebsite.RelatedWebsite;
import iace.service.BaseIaceService;

public class RelatedWebsiteService extends BaseIaceService<RelatedWebsite> {

	private String relatedWebsitePicFolder;
	
	public RelatedWebsiteService(IRelatedWebsiteDao relatedWebsiteDao) {
		super(relatedWebsiteDao);
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.relatedWebsitePicFolder = prop.getProperty("relatedWebsitePicFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	public ByteArrayInputStream getImageByName(String imageName) throws IOException {
		File f = new File(this.relatedWebsitePicFolder, imageName);
		return new ByteArrayInputStream(FileUtils.readFileToByteArray(f));
	}
}
