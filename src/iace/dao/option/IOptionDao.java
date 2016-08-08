package iace.dao.option;

import java.util.List;
import java.util.Map;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.option.BaseOption;
import iace.entity.option.BaseOptionSearchModel;

public interface IOptionDao<OptionEntity extends BaseOption> extends IBaseIaceDao<OptionEntity> {
	
	public OptionEntity getByCode(String code);
	
	public List<OptionEntity> listNotIn(List<String> codes);
	
	public List<String> listAllCode();
	
	public Map<String, OptionEntity> mapAll();

	public PagedList<OptionEntity> searchBy(BaseOptionSearchModel args);
	
	public boolean isCodeExist(String code);
	
	public boolean isNameExist(String name);
	
	public boolean hasBeenUsed(OptionEntity entity);
	
	public boolean hasBeenUsed(Long id);
}
