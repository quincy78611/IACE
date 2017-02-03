package iace.action;

import core.util.PagedList;
import iace.entity.ePaper.EPaper;
import iace.entity.ePaper.EPaperSearchModel;
import iace.service.ServiceFactory;
import iace.service.ePaper.EPaperService;

public class EPaperAction extends BaseIaceAction {
	private static final long serialVersionUID = 248576610418563326L;
	
	private EPaperService epaperService = ServiceFactory.getEpaperService();
	
	private EPaperSearchModel searchCondition = new EPaperSearchModel();
	private PagedList<EPaper> epaperPagedList;
	
	private long id;
	private EPaper epaper;
	
	private String epaperUrl;
	
	public EPaperAction() {
		super.setTitle("電子報");
	}
	
	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.epaperPagedList = this.epaperService.searchBy(this.searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			this.epaper = this.epaperService.get(this.id);
			if (this.epaper == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch(Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String update() {
		return showDetail();
	}
	
	public void validateUpdateSubmit() {
		super.validateNotBlankNLength(this.epaper.getTitle(), 200, "epaper.title");
		super.validateNotNull(this.epaper.getPostDate(), "epaper.postDate");
	}
	
	public String updateSubmit() {
		try {
			this.epaperService.update(this.epaper, super.getCurrentSysUser(), false, super.getSysLog());
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String delete() {
		return showDetail();
	}
	
	public String deleteSubmit() {
		try {
			this.epaperService.delete(this.id, false, super.getSysLog());
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String read() {
		try {
//			this.epaperUrl = "/ePapers/20160706163719.jsp";
//			this.epaperUrl = "/ePapers/20160909144004.jsp";
//			this.epaperUrl = "/ePapers/20170105185502.jsp";
			
			showDetail();
			this.epaperUrl = this.epaper.getUrl();
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String publish() {
		try {
			this.epaperService.publish(this.id, super.getCurrentSysUser(), this.getSysLog());
			index();
			super.addActionMessage("發佈成功");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	// =========================================================================

	public String getEpaperUrl() {
		return epaperUrl;
	}

	public void setEpaperUrl(String epaperUrl) {
		this.epaperUrl = epaperUrl;
	}

	public EPaperService getEpaperService() {
		return epaperService;
	}

	public void setEpaperService(EPaperService epaperService) {
		this.epaperService = epaperService;
	}

	public EPaperSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(EPaperSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<EPaper> getEpaperPagedList() {
		return epaperPagedList;
	}

	public void setEpaperPagedList(PagedList<EPaper> epaperPagedList) {
		this.epaperPagedList = epaperPagedList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EPaper getEpaper() {
		return epaper;
	}

	public void setEpaper(EPaper epaper) {
		this.epaper = epaper;
	}

	
}
