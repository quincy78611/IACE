package iace.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import core.dao.BaseDao;
import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;

public abstract class BaseIaceDao<T extends BaseEntity> extends BaseDao<T> implements IBaseIaceDao<T> {
	protected static boolean realDelete = true;
	
	protected Class<T> entityClass;
	
	protected BaseIaceDao(Class<T> class1) {
		this.entityClass = class1;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("isValid", BaseEntity.TRUE));		
		return (List<T>) super.listAll(entityClass, Order.asc("id"), criterionList);
	}

	@SuppressWarnings("unchecked")
	public T get(long id) {
		return (T) super.get(entityClass, id);
	}
	
	public T get(List<Criterion> criterions) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(entityClass);
			criterions.forEach(v -> criteria.add(v));
			@SuppressWarnings("unchecked")
			T entity =  (T) criteria.uniqueResult();
			
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void create(T entity) {
		entity.create();
		super.create(entity);
	}
	
	@Override
	public void createAll(List<T> entities) {
		Transaction tran = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tran = session.beginTransaction();
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).create();
				session.save(entities.get(i));
				if (i % 100 == 0) {
					session.flush();
				}
			}
			tran.commit();			
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void update(T entity) {
		entity.update();
		super.update(entity);
	}
	
	@Override
	public void updateAll(List<T> entities) {
		Transaction tran = null;
		try{
			Session session = HibernateSessionFactory.getSession();
			tran = session.beginTransaction();
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).update();
				session.update(entities.get(i));
				if (i % 100 == 0) {
					session.flush();
				}
			}
			tran.commit();
		} catch (Exception e) {
			if (tran != null) {
				tran.rollback();
			}
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void delete(T entity) {
		if (realDelete) {			
			super.delete(entity);	
		} else {
			entity.delete();
			super.update(entity);
		}	
	}

	public void delete(long id) {
		T entity = get(id);
		delete(entity);
	}
}
