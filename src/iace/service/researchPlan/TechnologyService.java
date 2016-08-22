package iace.service.researchPlan;

import iace.dao.researchPlan.ITechnologyDao;
import iace.entity.researchPlan.Technology;
import iace.service.BaseIaceService;

public class TechnologyService extends BaseIaceService<Technology> {
	
	public TechnologyService(ITechnologyDao technologyDao) {
		super(technologyDao);
	}
}
