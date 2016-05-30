package iace.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.action.BaseAction;
import core.util.PagedList;
import iace.entity.ResearchPlan;
import iace.entity.ResearchPlanSearchModel;
import iace.entity.Technology;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionTrl;
import iace.service.OptionGrbDomainService;
import iace.service.OptionTrlService;
import iace.service.ResearchPlanService;
import iace.service.TechnologyService;
import iace.service.ServiceFactory;

public class ResearchPlanAction extends BaseAction {

	private static final long serialVersionUID = 1798627207055671857L;
	
	private ResearchPlanService researchPlanService = ServiceFactory.getResearchPlanService();
	private TechnologyService technologyService = ServiceFactory.getTechnologyService();
	private OptionGrbDomainService optionGrbDomainService = ServiceFactory.getOptionGrbDomainService();
	private OptionTrlService optionTrlService = ServiceFactory.getOptionTrlService();
	
	private ResearchPlanSearchModel searchCondition;
	private List<OptionGrbDomain> optionGrbDomainList;
	private List<OptionTrl> optionTrlList;	
	private PagedList<ResearchPlan> researchPlanPagedList;
	
	private long id;
	private ResearchPlan researchPlan;	
	
	private long technologyId;
	private Technology technology;
	
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
			return INPUT;
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

			this.optionGrbDomainList = this.optionGrbDomainService.listNotIn(this.researchPlan.getGrbDomainCodes());
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public void validateUpdateSubmit() {
		//TODO
	}
	
	public String updateSubmit() {
		try {
			this.id = this.researchPlan.getId();
			ResearchPlan origin = this.researchPlanService.get(this.id);
			this.researchPlan.setTechnologies(origin.getTechnologies());

			this.researchPlanService.update(this.researchPlan);
			
			this.researchPlan = this.researchPlanService.get(this.id);
			List<ResearchPlan> plans = new ArrayList<ResearchPlan>();
			plans.add(this.researchPlan);
			this.researchPlanPagedList = new PagedList<ResearchPlan>(plans, 1, 5, 0);
			
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
	
	public String showTechnologyDetail() {
		try {
			this.technology = this.technologyService.get(this.technologyId);
			if (this.technology == null) {
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
	
	public String createTechnology() {
		return SUCCESS;
	}
	
	public void validateCreateTechnology() {
		//TODO
	}
	
	public String createTechnologySubmit() {
		try {
			this.researchPlan = this.researchPlanService.get(this.id);
			this.technology.setResearchPlan(this.researchPlan);
			this.technologyService.create(this.technology);
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
	
	public String updateTechnology() {
		try {
			this.technology = this.technologyService.get(this.technologyId);
			if (this.technology == null) {
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

	public void validateUpdateTechnology() {
		//TODO
	}
	
	public String updateTechnologySubmit() {
		try {
			this.researchPlan = this.researchPlanService.get(this.id);
			this.technology.setResearchPlan(this.researchPlan);
			this.technologyService.update(this.technology);
			// 成功後重新抓取研究計畫資料
			this.researchPlan = this.researchPlanService.get(this.id);
			this.addActionMessage("UPDATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String deleteTechnology() {
		try {
			this.technology = this.technologyService.get(this.technologyId);
			if (this.technology == null) {
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
	
	public String deleteTechnologySubmit() {
		try {
			this.technologyService.delete(this.technologyId);
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
		if (this.optionGrbDomainList == null) {
			this.optionGrbDomainList = this.optionGrbDomainService.listAll();
		}
		return this.optionGrbDomainList;
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

	public long getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(long technologyId) {
		this.technologyId = technologyId;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}
	
}
