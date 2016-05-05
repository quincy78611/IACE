package iace.service;

import java.util.List;

import core.service.BaseService;
import iace.dao.IOptionIndustryDao;
import iace.entity.OptionIndustry;

public class OptionIndustryService extends BaseService<OptionIndustry, Long> {
	private IOptionIndustryDao dao;
	
	OptionIndustryService(IOptionIndustryDao dao) {
		this.dao = dao;
	}
	
	public List<OptionIndustry> listAll() {
		return dao.listAll();
	}
	
	@Override
	public OptionIndustry get(Long id) {
		return dao.get(id);
	}

	@Override
	public void create(OptionIndustry entity) {
		dao.create(entity);
	}

	@Override
	public void update(OptionIndustry entity) {
		dao.update(entity);
	}

	@Override
	public void delete(OptionIndustry entity) {
		if (hasBeenUsed(entity)) {
			String msg = "Can't be delete because it had been used!";
			throw new IllegalArgumentException(msg);
		}
		dao.delete(entity);		
	}
	
	public void delete(long id) {
		if (hasBeenUsed(id)) {
			String msg = "Can't be delete because it had been used!";
			throw new IllegalArgumentException(msg);
		}
		dao.delete(id);
	}
	
	public boolean isCodeExist(String code) {
		return dao.isCodeExist(code);
	}
	
	public boolean hasBeenUsed(OptionIndustry entity) {
		//TODO
		return false;
	}
	
	public boolean hasBeenUsed(Long id) {
		//TODO
		return false;
	}

}
