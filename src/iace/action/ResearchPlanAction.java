package iace.action;

import java.util.List;

import core.action.BaseAction;
import core.util.PagedList;
import iace.entity.ResearchPlan;
import iace.entity.ResearchPlanSearchModel;
import iace.entity.RnDResult;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionTrl;
import iace.service.OptionGrbDomainService;
import iace.service.OptionTrlService;
import iace.service.ResearchPlanService;
import iace.service.RnDResultService;
import iace.service.ServiceFactory;

public class ResearchPlanAction extends BaseAction {

	private static final long serialVersionUID = 1798627207055671857L;
	
	private ResearchPlanService researchPlanService = ServiceFactory.getResearchPlanService();
	private RnDResultService rndResultService = ServiceFactory.getRnDResultService();
	private OptionGrbDomainService optionGrbDomainService = ServiceFactory.getOptionGrbDomainService();
	private OptionTrlService optionTrlService = ServiceFactory.getOptionTrlService();
	
	private ResearchPlanSearchModel searchCondition;
	private List<OptionGrbDomain> optionGrbDomainList;
	private List<OptionTrl> optionTrlList;	
	private PagedList<ResearchPlan> researchPlanPagedList;
	
	private long id;
	private ResearchPlan researchPlan;
	
	private long rndResultId;
	private RnDResult rndResult;
	
	public ResearchPlanAction() {
		super.setTitle("研究計畫資料");
	}

	public String init() {
		return SUCCESS;
	}
	
	public String index() {
		try {
			this.researchPlanPagedList = this.researchPlanService.searchBy(searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String showDetail() {
		try {
			this.researchPlan = this.researchPlanService.get(this.id);
			if (this.researchPlan == null) {
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
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		//TODO
	}
	
	public String createSubmit() {
		try {
			this.researchPlanService.create(this.researchPlan);
			this.id = this.researchPlan.getId();
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
			this.researchPlan = this.researchPlanService.get(this.id);
			if (this.researchPlan == null) {
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
	
	public void validateUpdateSubmit() {
		//TODO
	}
	
	public String updateSubmit() {
		try {
			this.researchPlanService.update(this.researchPlan);
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
			this.researchPlan = this.researchPlanService.get(this.id);
			if (this.researchPlan == null) {
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
			this.researchPlanService.delete(this.id);
			this.addActionMessage("DELETE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String showRndResultDetail() {
		try {
			this.rndResult = this.rndResultService.get(this.rndResultId);
			if (this.rndResult == null) {
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
	
	public String createRndResult() {
		return SUCCESS;
	}
	
	public void validateCreateRndResult() {
		//TODO
	}
	
	public String createRndResultSubmit() {
		try {
			this.researchPlan = this.researchPlanService.get(this.id);
			this.rndResult.setResearchPlan(this.researchPlan);
			this.rndResultService.create(this.rndResult);
			// 成功後重新抓取研究計畫資料
			this.researchPlan = this.researchPlanService.get(this.id);
			this.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String deleteRndResult() {
		try {
			this.rndResult = this.rndResultService.get(this.rndResultId);
			if (this.rndResult == null) {
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
	
	public String deleteRndResultSubmit() {
		try {
			this.rndResultService.delete(this.rndResultId);
			// 成功後重新抓取研究計畫資料
			this.researchPlan = this.researchPlanService.get(this.id);
			this.addActionMessage("DELETE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	//==========================================================================

	public ResearchPlanSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(ResearchPlanSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<ResearchPlan> getResearchPlanPagedList() {
		return researchPlanPagedList;
	}

	public List<OptionGrbDomain> getOptionGrbDomainList() {
		this.optionGrbDomainList = this.optionGrbDomainService.listAll();
		return optionGrbDomainList;
	}

	public List<OptionTrl> getOptionTrlList() {
		this.optionTrlList = this.optionTrlService.listAll();
		return optionTrlList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ResearchPlan getResearchPlan() {
		return researchPlan;
	}

	public void setResearchPlan(ResearchPlan researchPlan) {
		this.researchPlan = researchPlan;
	}

	public long getRndResultId() {
		return rndResultId;
	}

	public void setRndResultId(long rndResultId) {
		this.rndResultId = rndResultId;
	}

	public RnDResult getRndResult() {
		return rndResult;
	}

	public void setRndResult(RnDResult rndResult) {
		this.rndResult = rndResult;
	}
	
	
}
