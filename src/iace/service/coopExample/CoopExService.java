package iace.service.coopExample;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
	public void create(CoopEx entity) throws IOException, SQLException {
		saveFile(entity);
		super.create(entity);
	}

	private void saveFile(CoopEx entity) {
		File dir = new File(this.coopExampleFolder, entity.getProjName());
		if (!dir.exists()) {
			dir.mkdirs();  
		}
		
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
}
