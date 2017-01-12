package iace.action;

import java.io.InputStream;

import core.util.PagedList;
import iace.dao.ClickNumCounterDao;
import iace.entity.activity.Activity;
import iace.entity.videosArea.Video;
import iace.entity.videosArea.VideosArea;
import iace.entity.videosArea.VideosAreaSearchModel;
import iace.service.ServiceFactory;
import iace.service.videosArea.VideoService;
import iace.service.videosArea.VideosAreaService;

public class VideosAreaAction extends BaseIaceAction {
	private static final long serialVersionUID = -433874959899230862L;

	private VideosAreaService videosAreaService = ServiceFactory.getVideosAreaService();
	private VideoService videoService = ServiceFactory.getVideoService();
	
	private VideosAreaSearchModel searchCondition = new VideosAreaSearchModel();
	private PagedList<VideosArea> videosAreaPagedList;
	
	private long id;
	private VideosArea videosArea;
	
	private long videoId;
	private String downloadFileName;
	private InputStream downloadFileInputStream;
	
	public VideosAreaAction() {
		super.setTitle("影音專區");
	}
	
	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.videosAreaPagedList = this.videosAreaService.searchBy(this.searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			new ClickNumCounterDao().increaseClickNum(this.id, Activity.class);
			this.videosArea = this.videosAreaService.get(this.id);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		validateBeforeSubmit();
	}
	
	public String createSubmit() {
		try {
			this.videosAreaService.create(this.videosArea, super.getCurrentSysUser(), false, super.getSysLog());
			this.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.videosArea = this.videosAreaService.get(this.id);
			if (this.videosArea == null) {
				this.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public void validateUpdateSubmit() {
		validateBeforeSubmit();
	}
	
	public String updateSubmit() {
		try {
			this.videosAreaService.update(this.videosArea, super.getCurrentSysUser(), false, super.getSysLog());
			this.addActionMessage("UPDATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String delete() {
		try {
			this.videosArea = this.videosAreaService.get(this.id);
			if (this.videosArea == null) {
				this.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String deleteSubmit() {
		try {
			this.videosAreaService.delete(this.id, false, super.getSysLog());
			super.addActionMessage("DELETE SUCCESS");
			index();
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	private void validateBeforeSubmit() {
		super.validateNotBlankNLength(this.videosArea.getTitle(), 200, "videosArea.title");
		super.validateTextMaxLength(this.videosArea.getMetaTitle(), 200, "videosArea.metaTitle");
	}
	
	public String downloadVideo() {
		try {
			Video entity = this.videoService.get(this.videoId); 
			this.downloadFileInputStream = entity.getFileContentInputStream();
			this.downloadFileName = entity.getUploadFileName();
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	// =========================================================================

	public VideosAreaSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(VideosAreaSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public VideosArea getVideosArea() {
		return videosArea;
	}

	public void setVideosArea(VideosArea videosArea) {
		this.videosArea = videosArea;
	}

	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	public PagedList<VideosArea> getVideosAreaPagedList() {
		return videosAreaPagedList;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public InputStream getDownloadFileInputStream() {
		return downloadFileInputStream;
	}
	
	
}
