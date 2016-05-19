package iace.dao.option;

import java.util.List;
import java.util.Map;

import iace.entity.option.BaseOption;

public interface IOptionDao<OptionEntity extends BaseOption> {
	
	public List<OptionEntity> listAll();
	
	public Map<String, OptionEntity> mapAll();

	public OptionEntity get(long id);

	public void create(OptionEntity entity);

	public void update(OptionEntity entity);

	public void delete(OptionEntity entity);

	public void delete(long id);

	public boolean isCodeExist(String code);
	
	public boolean isNameExist(String name);
	
	public boolean hasBeenUsed(OptionEntity entity);
	
	public boolean hasBeenUsed(Long id);
}
