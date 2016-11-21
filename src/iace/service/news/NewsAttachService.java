package iace.service.news;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import iace.dao.news.INewsAttachDao;
import iace.entity.news.NewsAttach;
import iace.service.BaseIaceService;

public class NewsAttachService extends BaseIaceService<NewsAttach> {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	protected INewsAttachDao dao;
	
	private String newsAttachFolder;
	
	public NewsAttachService(INewsAttachDao dao) {
		super(dao);
		this.dao = dao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.newsAttachFolder = prop.getProperty("newsAttachFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	@Override
	public NewsAttach get(Long id) {
		NewsAttach entity = this.dao.get(id);
		entity.setFileFolder(this.newsAttachFolder);
		try {
			entity.loadFileContentFromDisk();
		} catch (Exception e) {
			log.warn("Load attach file fail!", e);
		}
		return entity;
	}

	@Override
	public void create(NewsAttach entity) throws IOException, SQLException {
		if (entity.hasUpload()) {
			entity.setFileFolder(this.newsAttachFolder);
			String time = sdf.format(System.currentTimeMillis());
			String fileName = "news" + "_"+time+"_" + UUID.randomUUID().toString() + "_" + entity.getUploadFileName();
			entity.setFileSubPath(fileName);
			entity.saveUploadFile();
		}
		super.create(entity);
	}

	@Deprecated
	@Override
	public void update(NewsAttach entity) throws IOException, SQLException {
		if (entity.hasUpload()) {
			NewsAttach entityO = this.dao.get(entity.getId());
			File f = new File(this.newsAttachFolder, entityO.getFileSubPath());
			f.delete();
			
			entity.setFileFolder(this.newsAttachFolder);
			String time = sdf.format(System.currentTimeMillis());
			String fileName = "news" + "_"+time+"_" + UUID.randomUUID().toString() + "_" + entity.getUploadFileName();
			entity.setFileSubPath(fileName);
			entity.saveUploadFile();
		}
		super.update(entity);
	}

	@Override
	public void delete(NewsAttach entity) throws IOException, SQLException {
		File f = new File(this.newsAttachFolder, entity.getFileSubPath());
		f.delete();
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		NewsAttach entity = this.dao.get(id);
		delete(entity);
	}
	
	
}
