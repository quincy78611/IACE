package iace.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import core.service.BaseService;
import iace.dao.IBaseIaceDao;
import iace.entity.BaseEntity;

public class BaseIaceService<T extends BaseEntity> extends BaseService<T, Long> {

	protected IBaseIaceDao<T> dao;
	
	protected BaseIaceService(IBaseIaceDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public T get(Long id) {
		return this.dao.get(id);
	}

	@Override
	public void create(T entity) throws IOException, SQLException {
		this.dao.create(entity);		
	}

	@Override
	public void update(T entity) throws IOException, SQLException {
		this.dao.update(entity);
	}

	@Override
	public void delete(T entity) throws IOException, SQLException {
		this.dao.delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		this.dao.delete(id);
	}
	
	public List<T> listAll() {
		return this.dao.listAll();
	}
}
