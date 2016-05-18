package iace.service;

import java.io.IOException;

import core.service.BaseService;
import core.util.PagedList;
import iace.dao.option.IOptionDao;
import iace.dao.researchPlan.IResearchPlanDao;
import iace.entity.ResearchPlan;
import iace.entity.ResearchPlanSearchModel;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionTrl;

public class ResearchPlanService extends BaseService<ResearchPlan, Long> {

	private IResearchPlanDao researchPlanDao;
	private IOptionDao<OptionGrbDomain> optionGrbDomainDao;
	private IOptionDao<OptionTrl> optionTrlDao;
	
	ResearchPlanService(IResearchPlanDao researchPlanDao, 
			IOptionDao<OptionGrbDomain> optionGrbDomainDao, 
			IOptionDao<OptionTrl> optionTrlDao) {
		this.researchPlanDao = researchPlanDao;
		this.optionGrbDomainDao = optionGrbDomainDao;
		this.optionTrlDao = optionTrlDao;
	}
	
	public PagedList<ResearchPlan> searchBy(ResearchPlanSearchModel arg) {
		PagedList<ResearchPlan> res = this.researchPlanDao.searchBy(arg);
		return res;
	}
	
	@Override
	public ResearchPlan get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(ResearchPlan entity) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ResearchPlan entity) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ResearchPlan entity) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
