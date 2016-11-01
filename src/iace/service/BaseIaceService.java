package iace.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;

import core.service.BaseService;
import iace.dao.IBaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysUser;
import lucene.integrationSearch.IntegrationIndexer;

public class BaseIaceService<T extends BaseEntity> extends BaseService<T, Long> {
	protected String luceneIndexFolder;
	
	protected IBaseIaceDao<T> dao;
	
	protected BaseIaceService(IBaseIaceDao<T> dao) {
		this.dao = dao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.luceneIndexFolder = prop.getProperty("luceneIndexFolder");
		} catch (IOException e) {
			log.fatal("", e);
		}
	}
	
	@Override
	public T get(Long id) {
		return this.dao.get(id);
	}

	@Override
	public void create(T entity) throws IOException, SQLException {
		this.dao.create(entity);		
	}
	
	@Deprecated
	public void create(T entity, SysLog sysLog) throws IOException, SQLException {
		create(entity);
		entity = get(entity.getId());
		sysLog.setAfterEntity(entity);
	}
	
	public void create(T entity, SysUser user) throws IOException, SQLException {
		entity.setCreateUser(user.getAccount());
		create(entity);
	}
	
	@Deprecated
	public void create(T entity, SysLog sysLog, SysUser user) throws IOException, SQLException {
		entity.setCreateUser(user.getAccount());
		create(entity, sysLog);
	}
	
	public void create(T entity, SysUser user, boolean indexing, SysLog sysLog) throws IOException, SQLException {
		if (user != null) {
			entity.setCreateUser(user.getAccount());
		}
		create(entity);
		if (indexing && entity instanceof IntegrationSearch) {
			addDocToIndex((IntegrationSearch)entity);
		}
		if (sysLog != null) {
			entity = get(entity.getId());
			sysLog.setAfterEntity(entity);
		}
	}
	
	public void addDocToIndex(IntegrationSearch entity) throws IOException {
		synchronized (IntegrationIndexer.lock) {
			Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
			IndexWriter writer = IntegrationIndexer.createIndexWriter(indexDirectory);
			try {
				IntegrationIndexer.addDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			} catch (Exception e) {
				throw e;
			} finally {
				writer.close();
				indexDirectory.close();
			}
		}
	}

	@Override
	public void update(T entity) throws IOException, SQLException {
		this.dao.update(entity);
	}
	
	@Deprecated
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
	
	@Deprecated
	public void update(T entity, SysLog sysLog, SysUser user) throws IOException, SQLException {
		entity.setUpdateUser(user.getAccount());
		update(entity, sysLog);
	}
	
	public void update(T entity, SysUser user, boolean indexing, SysLog sysLog) throws IOException, SQLException, ParseException {
		if (sysLog != null) {
			sysLog.setBeforeEntity(get(entity.getId()));
		}
		
		if (user != null) {
			entity.setUpdateUser(user.getAccount());
		}
		update(entity);
		if (indexing && entity instanceof IntegrationSearch) {
			updateDocToIndex((IntegrationSearch)entity);
		}
		
		if (sysLog != null) {
			entity = get(entity.getId());
			sysLog.setAfterEntity(entity);
		}
	}
	
	public void updateDocToIndex(IntegrationSearch entity) throws IOException, ParseException {
		synchronized (IntegrationIndexer.lock) {
			Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
			IndexWriter writer = IntegrationIndexer.createIndexWriter(indexDirectory);
			try {
				IntegrationIndexer.updateDoc(writer, entity.getId(), entity.getClass(), entity.toLunceneContent());
			} catch (Exception e) {
				throw e;
			} finally {
				writer.close();
				indexDirectory.close();
			}
		}
	}

	@Override
	public void delete(T entity) throws IOException, SQLException {
		this.dao.delete(entity);
	}
	
	@Deprecated
	public void delete(T entity, SysLog sysLog) throws IOException, SQLException {
		sysLog.setBeforeEntity(get(entity.getId()));
		delete(entity);
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		this.dao.delete(id);
	}
	
	@Deprecated
	public void delete(Long id, SysLog sysLog) throws IOException, SQLException {
		sysLog.setBeforeEntity(get(id));
		delete(id);
	}
	
	public void delete(T entity, boolean indexing, SysLog sysLog) throws IOException, ParseException, SQLException {
		if (indexing) {
			deleteDocFromIndex(entity);
		}
		if (sysLog != null) {
			sysLog.setBeforeEntity(entity);
		}
		delete(entity);
	}
	
	public void delete(long id, boolean indexing, SysLog sysLog) throws IOException, ParseException, SQLException {
		T entity = get(id);
		if (indexing) {
			deleteDocFromIndex(entity);
		}
		if (sysLog != null) {
			sysLog.setBeforeEntity(entity);
		}
		delete(id);
	}
	
	public void deleteDocFromIndex(T entity) throws IOException, ParseException {
		Directory indexDirectory = IntegrationIndexer.openDirectory(luceneIndexFolder);
		IndexWriter writer = IntegrationIndexer.createIndexWriter(indexDirectory);
		try {
			IntegrationIndexer.deleteDoc(writer, entity.getId(), entity.getClass());
		} catch (ParseException e) {
			throw e;
		} finally {
			writer.close();
			indexDirectory.close();
		}
	}
	
	public List<T> listAll() {
		return this.dao.listAll();
	}
}
