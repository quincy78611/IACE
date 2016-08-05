package iace.service;

import java.util.ArrayList;
import java.util.List;

import core.service.BaseService;
import iace.dao.option.IOptionDao;
import iace.entity.option.BaseOption;

public abstract class BaseOptionService<OptionEntity extends BaseOption> extends BaseService<OptionEntity, Long> {
	protected IOptionDao<OptionEntity> dao;

	protected BaseOptionService(IOptionDao<OptionEntity> dao) {
		this.dao = dao;
	}
	
	public List<OptionEntity> listAll() {
		return dao.listAll();
	}
	
	public List<OptionEntity> listNotIn(List<String> codes) {
		return dao.listNotIn(codes);
	}
	
	@Override
	public OptionEntity get(Long id) {
		return dao.get(id);
	}
	
	public OptionEntity getByCode(String code) {
		return dao.getByCode(code);
	}

	@Override
	public void create(OptionEntity entity) {
		entity.setCode(entity.getCode().trim());
		entity.setName(entity.getName().trim());
		dao.create(entity);
	}
	
	public void createAll(List<OptionEntity> entities) {
		List<OptionEntity> entitiesWithoutExist = new ArrayList<OptionEntity>();
		List<String> codeList = this.dao.listAllCode();
		for (OptionEntity opt : entities) {
			opt.setCode(opt.getCode().trim());
			opt.setName(opt.getName().trim());
			if (codeList.contains(opt.getCode()) == false) {
				entitiesWithoutExist.add(opt);
			}
		}		
		
		dao.createAll(entitiesWithoutExist);
	}

	@Override
	public void update(OptionEntity entity) {
		entity.setCode(entity.getCode().trim());
		entity.setName(entity.getName().trim());
		dao.update(entity);
	}

	@Override
	public void delete(OptionEntity entity) {
		if (hasBeenUsed(entity)) {
			String msg = "Can't be delete because it had been used!";
			throw new IllegalArgumentException(msg);
		}
		dao.delete(entity);		
	}
	
	public void delete(Long id) {
		if (hasBeenUsed(id)) {
			String msg = "Can't be delete because it had been used!";
			throw new IllegalArgumentException(msg);
		}
		dao.delete(id);
	}
	
	public boolean isCodeExist(String code) {
		return dao.isCodeExist(code);
	}
	
	public boolean hasBeenUsed(OptionEntity entity) {
		return dao.hasBeenUsed(entity);
	}
	
	public boolean hasBeenUsed(Long id) {
		return dao.hasBeenUsed(id);
	}
}
