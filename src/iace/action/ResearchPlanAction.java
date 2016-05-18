package iace.action;

import java.util.List;

import core.action.BaseAction;
import core.util.PagedList;
import iace.entity.ResearchPlan;
import iace.entity.ResearchPlanSearchModel;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionTrl;
import iace.service.OptionGrbDomainService;
import iace.service.OptionTrlService;
import iace.service.ResearchPlanService;
import iace.service.ServiceFactory;

public class ResearchPlanAction extends BaseAction {

	private static final long serialVersionUID = 1798627207055671857L;
	
	private ResearchPlanService researchPlanService = ServiceFactory.getResearchPlanService();
	private OptionGrbDomainService optionGrbDomainService = ServiceFactory.getOptionGrbDomainService();
	private OptionTrlService optionTrlService = ServiceFactory.getOptionTrlService();
	
	private ResearchPlanSearchModel searchCondition;
	private List<OptionGrbDomain> optionGrbDomainList;
	private List<OptionTrl> optionTrlList;
	
	private PagedList<ResearchPlan> researchPlanPagedList;
	
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
	
	
}
