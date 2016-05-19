package iace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		return this.researchPlanDao.get(id);
	}

	@Override
	public void create(ResearchPlan entity) {
		this.researchPlanDao.create(entity);
	}
	
	public List<String> createAll(List<ResearchPlan> entities) {
		List<String> errMsgs = new ArrayList<String>();
		Map<String, OptionGrbDomain> grbDomains = this.optionGrbDomainDao.mapAll();
		Map<String, OptionTrl> trls = this.optionTrlDao.mapAll();
		for (ResearchPlan rp : entities) {
			try {
				boolean planExist = this.researchPlanDao.planNoExist(rp.getPlanNo());
				if (planExist) {
					//TODO validate
					ResearchPlan rpOrigin = this.researchPlanDao.getByPlanNo(rp.getPlanNo());
					rpOrigin.addRndResults(rp.getRndResults());
					rp.getRndResults().forEach(v -> v.setResearchPlan(rpOrigin));
					this.researchPlanDao.update(rpOrigin);
				} else {
					//TODO validate
					rp.getRndResults().forEach(v -> v.setResearchPlan(rp));
					this.researchPlanDao.create(rp);
				}				
			} catch (Exception e) {
				log.warn("", e);
				errMsgs.add(e.getMessage());
			}
		}
		return errMsgs;
	}

	@Override
	public void update(ResearchPlan entity) {		
		this.researchPlanDao.update(entity);
	}

	@Override
	public void delete(ResearchPlan entity) {
		this.researchPlanDao.delete(entity);
	}
	
	public void delete(Long id) {
		this.researchPlanDao.delete(id);
	}

}
