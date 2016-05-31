package iace.action;

import java.util.ArrayList;
import java.util.List;

import core.action.BaseAction;
import core.util.PagedList;
import iace.entity.Consulting;
import iace.entity.option.OptionConsult;
import iace.entity.option.OptionIndustry;
import iace.entity.option.OptionOrganizationType;
import iace.service.ConsultingService;
import iace.service.OptionConsultService;
import iace.service.OptionIndustryService;
import iace.service.OptionOrganizationTypeService;
import iace.service.ServiceFactory;

public class ConsultingAction extends BaseAction {

	private static final long serialVersionUID = 7983348708278484405L;
	
	private ConsultingService consultingService = ServiceFactory.getConsultingService();
	private OptionOrganizationTypeService optionOrgTypeService = ServiceFactory.getOptionOrganizationTypeService();
	private OptionConsultService optionConsultService = ServiceFactory.getOptionConsultService();
	private OptionIndustryService optionIndustryService = ServiceFactory.getOptionIndustryService();
	
	private String searchName;
	private String searchOrganization;
	
	private PagedList<Consulting> consultingPagedList;
	private int pageIndex;
	private int pageSize = 5;

	
	private List<OptionOrganizationType> optionOrganizationTypeList;
	private List<OptionConsult> optionConsultList;
	private List<OptionIndustry> optionIndustryList;
	
	private long id;
	private Consulting consulting;	

	public ConsultingAction() {
		super.setTitle("諮詢服務表");
	}
	
	public String init() {
		return SUCCESS;
	}
	
	public String index() {
		try {
			this.consultingPagedList = this.consultingService.searchBy(
					this.pageIndex, this.pageSize, 
					this.searchName, this.searchOrganization);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String showDetail() {
		try {
			this.consulting = this.consultingService.get(this.id);
			if (this.consulting == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;			
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
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
			this.consultingService.create(this.consulting);
						this.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String update() {
		try {
			this.consulting = this.consultingService.get(this.id);
			if(this.consulting == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			
			return SUCCESS;
		} catch(Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public void validateUpdateSubmit() {
		validateBeforeSubmit();
	}
	
	public String updateSubmit() {
		try {
			this.consultingService.update(this.consulting);
			this.addActionMessage("UPDATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String delete() {
		try {
			this.consulting = this.consultingService.get(this.id);
			if (this.consulting == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteSubmit() {
		try {
			this.consultingService.delete(this.id);
			this.addActionMessage("DELETE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	private void validateBeforeSubmit() {
		//TODO
	}
	
	//==========================================================================
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchOrganization() {
		return searchOrganization;
	}

	public void setSearchOrganization(String searchOrganization) {
		this.searchOrganization = searchOrganization;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Consulting getConsulting() {
		return consulting;
	}

	public void setConsulting(Consulting consulting) {
		this.consulting = consulting;
	}

	public PagedList<Consulting> getConsultingPagedList() {
		return consultingPagedList;
	}

	public List<OptionOrganizationType> getOptionOrganizationTypeList() {
		if (optionOrganizationTypeList == null) {
			optionOrganizationTypeList = this.optionOrgTypeService.listAll(); 
		}
		return optionOrganizationTypeList;
	}

	public List<OptionConsult> getOptionConsultList() {
		if(optionConsultList == null) {
			optionConsultList = this.optionConsultService.listAll();
		}
		return optionConsultList;
	}

	public List<OptionIndustry> getOptionIndustryList() {
		if (optionIndustryList == null) {
			optionIndustryList = this.optionIndustryService.listAll();
		}
		return optionIndustryList;
	}
	
	
	

}
