package iace.action;

import java.io.InputStream;

import core.util.PagedList;
import iace.entity.coopExample.CoopEx;
import iace.entity.coopExample.CoopExAttachFile;
import iace.entity.coopExample.CoopExSearchModel;
import iace.entity.coopExample.CoopExVideo;
import iace.service.ServiceFactory;
import iace.service.coopExample.CoopExAttachFileService;
import iace.service.coopExample.CoopExService;
import iace.service.coopExample.CoopExVideoService;

public class CoopExAction extends BaseIaceAction {

	private static final long serialVersionUID = -1853598489369057497L;
	
	private CoopExService coopExService = ServiceFactory.getCoopExService();
	private CoopExVideoService coopExVideoService = ServiceFactory.getCoopExVideoService();
	private CoopExAttachFileService coopExAttachFileService = ServiceFactory.getCoopExAttachFileService();
	
	private CoopExSearchModel searchCondition = new CoopExSearchModel();
	private PagedList<CoopEx> coopExPagedList;
	
	private long id; 
	private CoopEx coopEx;
	
	private long videoId;
	private long attachFileId;
	private String downloadFileName;
	private InputStream downloadFileInputStream;

	public CoopExAction() {
		super.setTitle("產學合作案例");
	}
	
	public String init() {
		return index();
	}

	public String index() {
		try {
			this.coopExPagedList = this.coopExService.searchBy(searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			this.coopEx = this.coopExService.get(this.id);
			if (this.coopEx == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			
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
			this.coopExService.create(this.coopEx);
			super.addActionMessage("CREATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.coopEx = this.coopExService.get(this.id);
			if (this.coopEx == null) {
				super.addActionError("找不到選擇的資料紀錄!");
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
			this.coopExService.update(this.coopEx);
			super.addActionMessage("UPDATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;			
		}
	}
	
	public String delete() {
		try {
			this.coopEx = this.coopExService.get(this.id);
			if (this.coopEx == null) {
				super.addActionError("找不到選擇的資料紀錄!");
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
			this.coopExService.delete(this.id);
			super.addActionMessage("DELETE SUCCESS");
			index();
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String downloadVideo() {
		try {
			CoopExVideo entity = this.coopExVideoService.get(this.videoId); 
			this.downloadFileInputStream = this.coopExVideoService.getVideoIS(entity);
			this.downloadFileName = entity.getFileName();
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String downloadAttach() {
		try {
			CoopExAttachFile entity = this.coopExAttachFileService.get(this.attachFileId); 
			this.downloadFileInputStream = this.coopExAttachFileService.getAttachFileIS(entity);
			this.downloadFileName = entity.getFileName();
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public void validateBeforeSubmit() {
		if (super.validateNotBlankNLength(this.coopEx.getProjName(), 1000, "coopEx.projName")) {
			boolean isExist = this.coopExService.isProjNameExist(this.coopEx.getId(), this.coopEx.getProjName());
			if (isExist) {
				super.addFieldError("coopEx.projName", "案名已存在");
			}
		}
		super.validateNotNull(this.coopEx.getYear(), "coopEx.year");
		super.validateNotBlankNLength(this.coopEx.getRdTeam(), 1000, "coopEx.rdTeam");
		super.validateNotBlankNLength(this.coopEx.getAssisTeam(), 1000, "coopEx.assisTeam");
	}
	
	// ========================================================================

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CoopEx getCoopEx() {
		return coopEx;
	}

	public void setCoopEx(CoopEx coopEx) {
		this.coopEx = coopEx;
	}

	public long getAttachFileId() {
		return attachFileId;
	}

	public void setAttachFileId(long attachFileId) {
		this.attachFileId = attachFileId;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public InputStream getDownloadFileInputStream() {
		return downloadFileInputStream;
	}

	public void setDownloadFileInputStream(InputStream downloadFileInputStream) {
		this.downloadFileInputStream = downloadFileInputStream;
	}

	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	public CoopExSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(CoopExSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<CoopEx> getCoopExPagedList() {
		return coopExPagedList;
	}

	public void setCoopExPagedList(PagedList<CoopEx> coopExPagedList) {
		this.coopExPagedList = coopExPagedList;
	}
	
	
}
