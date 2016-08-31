package iace.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import core.service.BaseService;
import iace.dao.IBaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysUser;

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
	
	public void create(T entity, SysLog sysLog) throws IOException, SQLException {
		create(entity);
		entity = get(entity.getId());
		sysLog.setAfterEntity(entity);
	}
	
	public void create(T entity, SysUser user) throws IOException, SQLException {
		entity.setCreateUser(user.getAccount());
		create(entity);
	}
	
	public void create(T entity, SysLog sysLog, SysUser user) throws IOException, SQLException {
		entity.setCreateUser(user.getAccount());
		create(entity, sysLog);
	}

	@Override
	public void update(T entity) throws IOException, SQLException {
		this.dao.update(entity);
	}
	
	public void update(T entity, SysLog sysLog) throws IOException, SQLException {
		sysLog.setBeforeEntity(get(entity.getId()));
		update(entity);
		entity = get(entity.getId());
		sysLog.setAfterEntity(entity);
	}
	
	public void update(T entity, SysUser user) throws IOException, SQLException {
		entity.setUpdateUser(user.getAccount());
		update(entity);
	}
	
	public void update(T entity, SysLog sysLog, SysUser user) throws IOException, SQLException {
		entity.setUpdateUser(user.getAccount());
		update(entity, sysLog);
	}

	@Override
	public void delete(T entity) throws IOException, SQLException {
		this.dao.delete(entity);
	}
	
	public void delete(T entity, SysLog sysLog) throws IOException, SQLException {
		sysLog.setBeforeEntity(get(entity.getId()));
		delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		this.dao.delete(id);
	}
	
	public void delete(Long id, SysLog sysLog) throws IOException, SQLException {
		sysLog.setBeforeEntity(get(id));
		delete(id);
	}
	
	public List<T> listAll() {
		return this.dao.listAll();
	}
}
