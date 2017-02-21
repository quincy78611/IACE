package iace.dao;

import java.util.List;
import java.util.Set;

import iace.entity.BaseEntity;

public interface IBaseIaceDao <Entity extends BaseEntity>{
	public List<Entity> listAll();

	public Entity get(long id);
	
	public List<Entity> getAll(Set<Long> ids);
	
	public void create(Entity entity);
	
	public void createAll(List<Entity> entities);

	public void update(Entity entity);
	
	public void updateAll(List<Entity> entities);

	public void delete(Entity entity);

	public void delete(long id);
}
