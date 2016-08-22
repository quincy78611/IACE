package iace.service.option;

import java.util.ArrayList;
import java.util.List;

import core.service.BaseService;
import core.util.PagedList;
import iace.dao.option.IOptionDao;
import iace.entity.option.BaseOption;
import iace.entity.option.BaseOptionSearchModel;

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
	
	public PagedList<OptionEntity> searchBy(BaseOptionSearchModel args) {
		return this.dao.searchBy(args);
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
	
	public int createAll(List<OptionEntity> entities) {
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
		return entitiesWithoutExist.size();
	}

	@Override
	public void update(OptionEntity entity) {
		if (hasBeenUsed(entity)) {
			String msg = "無法編輯已被使用的代碼!";
			throw new IllegalArgumentException(msg);
		}
		entity.setCode(entity.getCode().trim());
		entity.setName(entity.getName().trim());
		dao.update(entity);
	}

	@Override
	public void delete(OptionEntity entity) {
		if (hasBeenUsed(entity)) {
			String msg = "無法刪除已被使用的代碼!";
			throw new IllegalArgumentException(msg);
		}
		dao.delete(entity);		
	}
	
	public void delete(Long id) {
		if (hasBeenUsed(id)) {
			String msg = "無法刪除已被使用的代碼!";
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
