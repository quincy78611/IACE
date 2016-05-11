package iace.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import core.dao.BaseDao;
import iace.entity.BaseEntity;

public class BaseIaceDao<T extends BaseEntity> extends BaseDao<T> implements IBaseIaceDao<T> {
	protected static boolean realDelete = false;
	
	protected Class<T> entityClass;
	
	protected BaseIaceDao(Class<T> class1) {
		this.entityClass = class1;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("isValid", BaseEntity.valid));		
		return (List<T>) super.listAll(entityClass, Order.asc("id"), criterionList);
	}

	@SuppressWarnings("unchecked")
	public T get(long id) {
		return (T) super.get(entityClass, id);
	}

	@Override
	public void create(T entity) {
		entity.create();
		super.create(entity);
	}

	@Override
	public void update(T entity) {
		entity.update();
		super.update(entity);
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
