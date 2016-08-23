package iace.action;

import core.util.PagedList;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;
import iace.service.ServiceFactory;
import iace.service.talentedPeople.TalentedPeopleService;

public class TalentedPeopleAction extends BaseIaceAction {

	private static final long serialVersionUID = 570851154374907844L;

	private TalentedPeopleService talentedPeopleService = ServiceFactory.getTalentedPeopleService();
	
	private TalentedPeopleSearchModel searchCondition = new TalentedPeopleSearchModel();
	private PagedList<TalentedPeople> talentedPeoplePagedList;
	
	private long id;
	private TalentedPeople talentedPeople;
	
	public TalentedPeopleAction() {
		super.setTitle("產學合作人才資料庫訪談內容");
	}

	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.talentedPeoplePagedList = this.talentedPeopleService.searchBy(this.searchCondition);
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			this.talentedPeople = this.talentedPeopleService.get(this.id);
			if (this.talentedPeople == null) {
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
	
	public String createSubmit() {
		try {
			this.talentedPeopleService.create(this.talentedPeople);
			
			super.addActionMessage("CREATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.talentedPeople = this.talentedPeopleService.get(this.id);
			if (this.talentedPeople == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String updateSubmit() {
		try {
			this.talentedPeopleService.update(this.talentedPeople);
			
			super.addActionMessage("UPDATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String delete() {
		try {
			this.talentedPeople = this.talentedPeopleService.get(this.id);
			if (this.talentedPeople == null) {
				super.addActionError("找不到資料!");
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
			this.talentedPeopleService.delete(this.id);
			
			super.addActionMessage("DELETE SUCCESS");
			return index();
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	
	// =========================================================================

	public TalentedPeopleSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(TalentedPeopleSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<TalentedPeople> getTalentedPeoplePagedList() {
		return talentedPeoplePagedList;
	}

	public void setTalentedPeoplePagedList(PagedList<TalentedPeople> talentedPeoplePagedList) {
		this.talentedPeoplePagedList = talentedPeoplePagedList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TalentedPeople getTalentedPeople() {
		return talentedPeople;
	}

	public void setTalentedPeople(TalentedPeople talentedPeople) {
		this.talentedPeople = talentedPeople;
	}
	
	
}
