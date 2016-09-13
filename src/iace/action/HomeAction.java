package iace.action;

import java.util.List;

import iace.entity.coopExample.CoopEx;
import iace.entity.researchPlan.Technology;
import iace.service.ServiceFactory;
import iace.service.coopExample.CoopExService;
import iace.service.researchPlan.TechnologyService;

public class HomeAction extends BaseIaceAction {

	private static final long serialVersionUID = -8420589647403574999L;

	private TechnologyService technologyService = ServiceFactory.getTechnologyService();
	private CoopExService coopExService = ServiceFactory.getCoopExService();
	
	private List<Technology> technologyList;
	private List<CoopEx> coopExList;
	
	public HomeAction() {
		super.setTitle("首頁");
	}
	
	public String init() {
		try {
			this.technologyList = this.technologyService.sampleForHomePage();
			this.coopExList = this.coopExService.sampleForHomePage();
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	//==========================================================================

	public List<CoopEx> getCoopExList() {
		return coopExList;
	}

	public List<Technology> getTechnologyList() {
		return technologyList;
	}

	
	
}
