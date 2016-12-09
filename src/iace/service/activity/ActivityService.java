package iace.service.activity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.util.PagedList;
import iace.dao.activity.IActivityDao;
import iace.dao.activity.IActivityVideoDao;
import iace.entity.activity.Activity;
import iace.entity.activity.ActivityAttach;
import iace.entity.activity.ActivitySearchModel;
import iace.entity.activity.ActivityVideo;
import iace.service.BaseIaceService;
import iace.service.ServiceFactory;

public class ActivityService extends BaseIaceService<Activity> {
	private ActivityAttachService activityAttachService = ServiceFactory.getActivityAttachService();
	private IActivityDao activityDao;
	private IActivityVideoDao activityVideoDao;
	
	public ActivityService(IActivityDao activityDao, IActivityVideoDao activityVideoDao) {
		super(activityDao);
		this.activityDao = activityDao;
		this.activityVideoDao = activityVideoDao;
	}
	
	public PagedList<Activity> searchBy(ActivitySearchModel arg) {
		return this.activityDao.searchBy(arg);
	}
	
	public List<Activity> sampleForHomePage() {
		return this.activityDao.sampleForHomePage();
	}
	
	@Override
	public Activity get(Long id) {
		Activity entity = this.activityDao.get(id);
		for (ActivityAttach attach : entity.getAttachList()) {
			attach.setFileFolder(this.activityAttachService.getActivityAttachFolder());
			try {
				attach.loadFileContentFromDisk();
			} catch (Exception e) {
				log.warn("Load attach file fail!");
			}
		}
		return entity;
	}

	@Override
	public void create(Activity entity) throws IOException, SQLException {
		entity.getVideoList().forEach(e -> e.setActivity(entity));
		entity.getVideoList().forEach(e -> e.create());
		
		// remove empty file
		List<ActivityAttach> attachList = new ArrayList<ActivityAttach>();
		for (ActivityAttach attach : entity.getAttachList()) {
			if (attach.hasUpload()) {
				attach.setActivity(entity);
				attachList.add(attach);
			}
		}
		
		// create activity data to DB
		entity.setAttachList(attachList);
		super.create(entity);
		
		// save attach files and create attach data to DB
		for (ActivityAttach attach : attachList) {
			this.activityAttachService.create(attach);
		}
		entity.setAttachList(attachList);
	}

	@Override
	public void update(Activity entity) throws IOException, SQLException {
		// ----- 1. attach files -----------------------------------------------
		
		// remove empty from attachList
		List<ActivityAttach> attachList = new ArrayList<ActivityAttach>();
		for (ActivityAttach attach : entity.getAttachList()) {
			if (attach.hasUpload() || attach.getId() > 0) {
				attach.setActivity(entity);
				attachList.add(attach);  // keep new upload files or old files  
			}
		}
		entity.setAttachList(attachList);
		
		// create new upload files
		for (ActivityAttach attach : entity.getAttachList()) {
			if (attach.getId() <= 0) {
				this.activityAttachService.create(attach);
			}
		}
		
		// update old attach files
		for (ActivityAttach attach : entity.getAttachList()) {
			if (attach.getId() > 0) {
				this.activityAttachService.update(attach);
			}
		}
		
		// delete attach file which is not in current attaches anymore
		Set<Long> currentAttachIdSet = new HashSet<Long>();
		attachList.forEach(e -> currentAttachIdSet.add(e.getId()));
		Activity entityO = get(entity.getId());
		for (ActivityAttach attach : entityO.getAttachList()) {
			if(currentAttachIdSet.contains(attach.getId()) == false) {
				this.activityAttachService.delete(attach);
			}
		}
		
		// ----- 2. activity video ---------------------------------------------
		
		entity.getVideoList().forEach(e -> e.setActivity(entity));
		entity.getVideoList().forEach(e -> e.update());
		
		// delete video which is not in video list anymore
		Set<Long> currentVideoIdSet = new HashSet<Long>();
		entity.getVideoList().forEach(e -> currentVideoIdSet.add(e.getId()));
		for (ActivityVideo video : entityO.getVideoList()) {
			if (currentVideoIdSet.contains(video.getId()) == false) {
				this.activityVideoDao.delete(video);
			}
		}
		
		// ------ 3. activity --------------------------------------------------
		
		super.update(entity);
	}
	
	@Override
	public void delete(Activity entity) throws IOException, SQLException {
		for (ActivityAttach attach : entity.getAttachList()) {
			this.activityAttachService.delete(attach);
		}
		entity.setAttachList(null);
		super.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		Activity entity = this.activityDao.get(id);
		delete(entity);
	}
}
