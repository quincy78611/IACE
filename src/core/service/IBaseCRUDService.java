package core.service;

import java.io.Serializable;


public interface IBaseCRUDService<T, ID extends Serializable> {	
	public T get(ID id); 
	public void create(T entity);
	public void update(T entity);
	public void delete(T entity);
}
