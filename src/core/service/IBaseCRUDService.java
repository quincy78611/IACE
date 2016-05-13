package core.service;

import java.io.IOException;
import java.io.Serializable;


public interface IBaseCRUDService<T, ID extends Serializable> {	
	public T get(ID id); 
	public void create(T entity) throws IOException;
	public void update(T entity) throws IOException;
	public void delete(T entity) throws IOException;
}
