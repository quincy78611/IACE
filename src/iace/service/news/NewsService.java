package iace.service.news;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.util.PagedList;
import iace.dao.news.INewsDao;
import iace.entity.news.News;
import iace.entity.news.NewsAttach;
import iace.entity.news.NewsSearchModel;
import iace.service.BaseIaceService;
import iace.service.ServiceFactory;

public class NewsService extends BaseIaceService<News> {
	private NewsAttachService newsAttachService = ServiceFactory.getNewsAttachService();
	private INewsDao newsDao;
	
	public NewsService(INewsDao dao) {
		super(dao);
		this.newsDao = dao;
	}

	public List<News> sampleForHomePage() {
		return this.newsDao.sampleForHomePage();
	}
	
	public PagedList<News> searchBy(NewsSearchModel arg) {
		return this.newsDao.searchBy(arg);
	}

	@Override
	public News get(Long id) {
		News entity = this.newsDao.get(id);
		for (NewsAttach attach : entity.getAttachs()) {
			attach.setFileFolder(this.newsAttachService.getNewsAttachFolder());
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
				attach.setNews(entity);
				attachList.add(attach);
			}
		}
		
		// create news data to DB
		entity.setAttachs(null);
		super.create(entity);
		
		// save attach files and create attach data to DB
		for (NewsAttach attach : attachList) {
			this.newsAttachService.create(attach);
		}
		entity.setAttachs(attachList);
	}

	@Override
	public void update(News entity) throws IOException, SQLException {
		// remove empty from attachList
		List<NewsAttach> attachList = new ArrayList<NewsAttach>();
		for (NewsAttach attach : entity.getAttachs()) {
			if (attach.hasUpload() || attach.getId() > 0) {
				attach.setNews(entity);
				attachList.add(attach);  // keep new upload files or old files  
			}
		}
		entity.setAttachs(attachList);
		
		// create new upload files
		for (NewsAttach attach : entity.getAttachs()) {
			if (attach.getId() <= 0) {
				this.newsAttachService.create(attach);
			}
		}
		
		// update old attach files
		for (NewsAttach attach : entity.getAttachs()) {
			if (attach.getId() > 0) {
				this.newsAttachService.update(attach);
			}
		}
		
		// delete file which is not in current attaches anymore
		Set<Long> currentAttachIdSet = new HashSet<Long>();
		attachList.forEach(e -> currentAttachIdSet.add(e.getId()));
		News newsO = get(entity.getId());
		for (NewsAttach attach : newsO.getAttachs()) {
			if(currentAttachIdSet.contains(attach.getId()) == false) {
				this.newsAttachService.delete(attach);
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
