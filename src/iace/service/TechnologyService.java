package iace.service;

import core.service.BaseService;
import iace.dao.researchPlan.ITechnologyDao;
import iace.entity.Technology;

public class TechnologyService extends BaseService<Technology, Long> {
	private ITechnologyDao rndResultDao; 
	
	public TechnologyService(ITechnologyDao rndResultDao) {
		this.rndResultDao = rndResultDao;
	}
	
	@Override
	public Technology get(Long id) {
		return this.rndResultDao.get(id);
	}

	@Override
	public void create(Technology entity) {
		this.rndResultDao.create(entity);		
	}

	@Override
	public void update(Technology entity) {
		this.rndResultDao.update(entity);		
	}

	@Override
	public void delete(Technology entity) {
		this.rndResultDao.delete(entity);		
	}
	
	public void delete(Long id) {
		this.rndResultDao.delete(id);
	}

}
