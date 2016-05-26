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
import iace.entity.Technology;
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
					validateTechnology(rp.getTechnologies(), trls);
					ResearchPlan rpOrigin = this.researchPlanDao.getByPlanNo(rp.getPlanNo());
					rpOrigin.addTechnology(rp.getTechnologies());
					rp.getTechnologies().forEach(v -> v.setResearchPlan(rpOrigin));
					this.researchPlanDao.update(rpOrigin);
				} else {
					validateResearchPlan(rp, grbDomains, trls);
					rp.getTechnologies().forEach(v -> v.setResearchPlan(rp));
					this.researchPlanDao.create(rp);
				}				
			} catch (Exception e) {
				log.warn("", e);
				errMsgs.add(e.getMessage());
			}
		}
		return errMsgs;
	}
	
	private void validateResearchPlan(ResearchPlan rp, Map<String, OptionGrbDomain> grbDomains, Map<String, OptionTrl> trls) {
		//TODO validate some field
		
		if (rp.getGrbDomainCodes() != null) {
			for (String code : rp.getGrbDomainCodes()) {
				if (grbDomains.containsKey(code) == false) {
					String msg = "GrbDomain: ["+code+"] isn't a legal code!";
					throw new IllegalArgumentException(msg);								
				}				
			}
		}
		if (rp.getTrl() != null && trls.containsKey(rp.getTrl().getCode()) == false) {
			String msg = "trlCode: ["+rp.getTrl().getCode()+"] doesn't exit!";
			throw new IllegalArgumentException(msg);
		}
		validateTechnology(rp.getTechnologies(), trls);
	}
	
	private void validateTechnology(List<Technology> technologies, Map<String, OptionTrl> trls) {
		if (technologies != null) {
			for (Technology tech:technologies) {
				//TODO validate some field
				
				
				if (tech.getOptionTrlCodes() != null) {
					for (String code : tech.getOptionTrlCodes()) {
						if (trls.containsKey(code) == false) {
							String msg = "trlCode: ["+code+"] doesn't exit!";
							throw new IllegalArgumentException(msg);
						}
					}
				}

			}			
		}
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
