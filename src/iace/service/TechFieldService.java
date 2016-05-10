package iace.service;

import java.util.List;

import core.service.BaseService;
import iace.dao.techField.ITechFieldDao;
import iace.entity.TechField;

public class TechFieldService extends BaseService<TechField, Long> {
	
	private ITechFieldDao techFieldDao;

	TechFieldService(ITechFieldDao techFieldDao) {
		this.techFieldDao = techFieldDao;
	}
	
	@Override
	public TechField get(Long id) {
		return this.techFieldDao.get(id);
	}

	@Override
	public void create(TechField entity) {
		this.techFieldDao.create(entity);
	}

	@Override
	public void update(TechField entity) {
		this.techFieldDao.update(entity);
	}

	@Override
	public void delete(TechField entity) {
		this.techFieldDao.delete(entity);
	}

	public List<TechField> listAll() {
		return this.techFieldDao.listAll();
	}
}
