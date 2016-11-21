package iace.service.news;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import core.util.PagedList;
import iace.dao.news.INewsDao;
import iace.entity.news.News;
import iace.entity.news.NewsAttach;
import iace.entity.news.NewsSearchModel;
import iace.service.BaseIaceService;
import iace.service.ServiceFactory;

public class NewsService extends BaseIaceService<News> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private NewsAttachService newsAttachService = ServiceFactory.getNewsAttachService();
	private INewsDao newsDao;
	
	private String newsAttachFolder;
	
	public NewsService(INewsDao dao) {
		super(dao);
		this.newsDao = dao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.newsAttachFolder = prop.getProperty("newsAttachFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	public PagedList<News> searchBy(NewsSearchModel arg) {
		return this.newsDao.searchBy(arg);
	}

	@Override
	public News get(Long id) {
		News entity = this.newsDao.get(id);
		for (NewsAttach attach : entity.getAttachs()) {
			attach.setFileFolder(this.newsAttachFolder);
			try {
				attach.loadFileContentFromDisk();
			} catch (Exception e) {
				log.warn("Load attach file fail!");
			}
		}
		return entity;
	}

	@Override
	public void create(News entity) throws IOException, SQLException {
		// remove empty file
		List<NewsAttach> attachList = new ArrayList<NewsAttach>();
		for (NewsAttach attach : entity.getAttachs()) {
			if (attach.hasUpload()) {
				attachList.add(attach);
			}
		}
		entity.setAttachs(attachList);
		entity.getAttachs().forEach(e -> e.setNews(entity));
		
		// save attach files
		for (int i=0; i<entity.getAttachs().size(); i++) {
			NewsAttach attach = entity.getAttachs().get(i);
			attach.setFileFolder(this.newsAttachFolder);
			String time = sdf.format(System.currentTimeMillis());
			String fileName = "news" + "_"+time+"_" + UUID.randomUUID().toString() + "_" + attach.getUploadFileName();
			attach.setFileSubPath(fileName);
			attach.saveUploadFile();
			attach.create();
		}
		
		super.create(entity);
		
	}

	@Override
	public void update(News entity) throws IOException, SQLException {
		// remove empty from attachList
		List<NewsAttach> attachList = new ArrayList<NewsAttach>();
		for (NewsAttach attach : entity.getAttachs()) {
			if (attach.hasUpload() || attach.getId() > 0) {
				attachList.add(attach);  // keep new upload files or old files  
			}
		}
		entity.setAttachs(attachList);
		entity.getAttachs().forEach(e -> e.setNews(entity));
		
		// delete file which is not in current attaches anymore
		Set<Long> currentAttachIdSet = new HashSet<Long>();
		attachList.forEach(e -> currentAttachIdSet.add(e.getId()));
		News newsO = get(entity.getId());
		for (NewsAttach attach : newsO.getAttachs()) {
			if(currentAttachIdSet.contains(attach.getId()) == false) {
				this.newsAttachService.delete(attach);
			}
		}
		
		// set attach files data
		for (NewsAttach attach : entity.getAttachs()) {
			if (attach.getId() > 0) { // origin file
				NewsAttach attachO = this.newsAttachService.get(attach.getId());
				attach.setIsValid(attachO.getIsValid());
				attach.setCreateTime(attachO.getCreateTime());
				attach.setCreateUser(attachO.getCreateUser());
				attach.setUpdateTime(attachO.getUpdateTime());
				attach.setUpdateUser(attachO.getUpdateUser());
				attach.setVer(attachO.getVer());
				
				if (attach.hasUpload() == false) {
					attach.setFileSubPath(attachO.getFileSubPath());
					attach.setUploadContentType(attachO.getUploadContentType());
					attach.setUploadFileName(attachO.getUploadFileName());
				}
				attach.update();
			} else { // new file
				attach.create();
			}
		}
		
		// save upload attach files
		for (NewsAttach attach : entity.getAttachs()) {
			if (attach.hasUpload()) {
				if (attach.getId() > 0) {
					NewsAttach attachO = this.newsAttachService.get(attach.getId());
					File f = new File(this.newsAttachFolder, attachO.getFileSubPath());
					f.delete(); //delete old file from disk
				}
				
				attach.setFileFolder(this.newsAttachFolder);
				String time = sdf.format(System.currentTimeMillis());
				String fileName = "news" + "_"+time+"_" + UUID.randomUUID().toString() + "_" + attach.getUploadFileName();
				attach.setFileSubPath(fileName);
				attach.saveUploadFile();
			}
		}
		
		super.update(entity);
	}

	@Override
	public void delete(News entity) throws IOException, SQLException {
		for (NewsAttach attach : entity.getAttachs()) {
			this.newsAttachService.delete(attach);
		}
		entity.setAttachs(null);
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		News entity = this.newsDao.get(id);
		delete(entity);
	}
	
	
}
