package iace.action;

import java.util.ArrayList;
import java.util.List;

import core.util.PagedList;
import iace.entity.option.BaseOption;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionDomain;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;
import iace.service.ServiceFactory;
import iace.service.option.OptionCountryService;
import iace.service.option.OptionDomainService;
import iace.service.talentedPeople.TalentedPeopleService;

public class TalentedPeopleAction extends BaseIaceAction {

	private static final long serialVersionUID = 570851154374907844L;

	private TalentedPeopleService talentedPeopleService = ServiceFactory.getTalentedPeopleService();
	private OptionDomainService optionDomainService = ServiceFactory.getOptionDomainService();
	private OptionCountryService optionCountryService = ServiceFactory.getOptionCountryService();
	
	private TalentedPeopleSearchModel searchCondition = new TalentedPeopleSearchModel();
	private PagedList<TalentedPeople> talentedPeoplePagedList;
	
	private long id;
	private TalentedPeople talentedPeople;
	
	private List<OptionDomain> mainDomainList;
	private List<OptionCountry> countryList;
	private List<BaseOption> rdResultTypeList;
	private List<BaseOption> yearList;
	private List<BaseOption> monthList;
	
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
	
	public void validateCreateSubmit() {
		validateBeforeSubmit();
	}
	
	public String createSubmit() {
		try {
			this.talentedPeopleService.create(this.talentedPeople, super.getSysLog(), super.getCurrentSysUser());
			
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
	
	public void validateUpdateSubmit() {
		validateBeforeSubmit();
	}
	
	public String updateSubmit() {
		try {
			this.talentedPeopleService.update(this.talentedPeople, super.getSysLog(), super.getCurrentSysUser());
			
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
			this.talentedPeopleService.delete(this.id, super.getSysLog());
			
			super.addActionMessage("DELETE SUCCESS");
			return index();
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public void validateBeforeSubmit() {
		super.validateNotBlankNLength(this.talentedPeople.getNameCh(), 100, "talentedPeople.nameCh");
		super.validateNotBlankNLength(this.talentedPeople.getNameEn(), 100, "talentedPeople.nameEn");
		super.validateNotBlankNLength(this.talentedPeople.getTel(), 20, "talentedPeople.tel");
		super.validateEmail(this.talentedPeople.getEmail(), "talentedPeople.email");
		super.validateTextMaxLength(this.talentedPeople.getWorkOrg(), 100, "talentedPeople.workOrg");
		super.validateTextMaxLength(this.talentedPeople.getJob(), 100, "talentedPeople.job");
		super.validateTextMaxLength(this.talentedPeople.getUrl(), 1000, "talentedPeople.url");
		super.validateTextMaxLength(this.talentedPeople.getSpecialty(), 1000, "talentedPeople.specialty");
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

	public List<OptionDomain> getMainDomainList() {
		if (mainDomainList == null) {
			mainDomainList = this.optionDomainService.listAll();
		}
		return mainDomainList;
	}

	public List<OptionCountry> getCountryList() {
		if (countryList == null) {
			countryList = this.optionCountryService.listAll();
		}
		return countryList;
	}

	public List<BaseOption> getRdResultTypeList() {
		if (rdResultTypeList == null) {
			rdResultTypeList = new ArrayList<BaseOption>();
			rdResultTypeList.add(new BaseOption("專利獲准", "專利獲准(請填5~8)"));
			rdResultTypeList.add(new BaseOption("專利申請中", "專利申請中(請填5,7)"));
			rdResultTypeList.add(new BaseOption("技術/KNOW-HOW ", "技術/KNOW-HOW "));
			rdResultTypeList.add(new BaseOption("積體電路布局", "積體電路布局"));
			rdResultTypeList.add(new BaseOption("軟體", "軟體"));
			rdResultTypeList.add(new BaseOption("其他", "其他"));
		}
		return rdResultTypeList;
	}

	public List<BaseOption> getYearList() {
		if (yearList == null) {
			yearList = new ArrayList<BaseOption>();
			yearList.add(new BaseOption("2010", "2010年"));
			yearList.add(new BaseOption("2011", "2011年"));
			yearList.add(new BaseOption("2012", "2012年"));
			yearList.add(new BaseOption("2013", "2013年"));
			yearList.add(new BaseOption("2014", "2014年"));
			yearList.add(new BaseOption("2015", "2015年"));
			yearList.add(new BaseOption("2016", "2016年"));
			yearList.add(new BaseOption("2017", "2017年"));
			yearList.add(new BaseOption("2018", "2018年"));
		}		
		return yearList;
	}

	public List<BaseOption> getMonthList() {
		if (monthList == null) {
			monthList = new ArrayList<BaseOption>();
			monthList.add(new BaseOption("1", "1月"));
			monthList.add(new BaseOption("2", "2月"));
			monthList.add(new BaseOption("3", "3月"));
			monthList.add(new BaseOption("4", "4月"));
			monthList.add(new BaseOption("5", "5月"));
			monthList.add(new BaseOption("6", "6月"));
			monthList.add(new BaseOption("7", "7月"));
			monthList.add(new BaseOption("8", "8月"));
			monthList.add(new BaseOption("9", "9月"));
			monthList.add(new BaseOption("10", "10月"));
			monthList.add(new BaseOption("11", "11月"));
			monthList.add(new BaseOption("12", "12月"));
		}
		return monthList;
	}
	
	
	
}
