package iace.service;

import core.service.BaseService;
import iace.dao.researchPlan.IRnDResultDao;
import iace.entity.RnDResult;

public class RnDResultService extends BaseService<RnDResult, Long> {
	private IRnDResultDao rndResultDao; 
	
	public RnDResultService(IRnDResultDao rndResultDao) {
		this.rndResultDao = rndResultDao;
	}
	
	@Override
	public RnDResult get(Long id) {
		return this.rndResultDao.get(id);
	}

	@Override
	public void create(RnDResult entity) {
		this.rndResultDao.create(entity);		
	}

	@Override
	public void update(RnDResult entity) {
		this.rndResultDao.update(entity);		
	}

	@Override
	public void delete(RnDResult entity) {
		this.rndResultDao.delete(entity);		
	}
	
	public void delete(Long id) {
		this.rndResultDao.delete(id);
	}

}
