package iace.action;

import core.util.PagedList;
import iace.entity.about.About;
import iace.entity.about.AboutSearchModel;
import iace.service.ServiceFactory;
import iace.service.about.AboutService;

public class AboutAction extends BaseIaceAction {
	private static final long serialVersionUID = -4902616364110673840L;

	private AboutService aboutService = ServiceFactory.getAboutService();
	
	private AboutSearchModel searchCondition = new AboutSearchModel();
	private PagedList<About> aboutPagedList;
	
	private long id;
	private About about;
	
	public AboutAction() {
		super.setTitle("關於");
	}

	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.aboutPagedList = this.aboutService.searchBy(this.searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			this.about = this.aboutService.get(this.id);
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
			this.aboutService.create(this.about, super.getCurrentSysUser(), false, super.getSysLog());
			this.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.about = this.aboutService.get(id);
			if (this.about == null) {
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
			this.aboutService.update(this.about, super.getCurrentSysUser(), false, super.getSysLog());
			this.addActionMessage("UPDATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String delete() {
		try {
			this.about = this.aboutService.get(id);
			if (this.about == null) {
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
			this.aboutService.delete(this.id, false, super.getSysLog());
			super.addActionMessage("DELETE SUCCESS");
			index();
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	private void validateBeforeSubmit() {
		super.validateNotBlankNLength(this.about.getTitle(), 200, "about.title");
		super.validateTextMaxLength(this.about.getMetaTitle(), 200, "about.metaTitle");
	}

	public AboutSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(AboutSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<About> getAboutPagedList() {
		return aboutPagedList;
	}

	public void setAboutPagedList(PagedList<About> aboutPagedList) {
		this.aboutPagedList = aboutPagedList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}
	
	
}
