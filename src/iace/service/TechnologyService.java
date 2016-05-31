package iace.service;

import iace.dao.researchPlan.ITechnologyDao;
import iace.entity.Technology;

public class TechnologyService extends BaseIaceService<Technology> {
	
	TechnologyService(ITechnologyDao technologyDao) {
		super(technologyDao);
	}
}
