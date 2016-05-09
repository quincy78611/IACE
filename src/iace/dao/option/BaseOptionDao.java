package iace.dao.option;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import core.dao.BaseDao;
import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;
import iace.entity.option.BaseOption;


public abstract class BaseOptionDao<OptionEntity extends BaseOption> extends BaseDao<OptionEntity> implements IOptionDao<OptionEntity> {
	private static boolean realDelete = true;
	
	protected Class<OptionEntity> optionEntityClass;
	
	protected BaseOptionDao(Class<OptionEntity> optionEntityClass) {
		this.optionEntityClass = optionEntityClass;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionEntity> listAll() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("isValid", BaseEntity.valid));		
		return (List<OptionEntity>) super.listAll(optionEntityClass, Order.asc("code"), criterionList);
	}
	
	@SuppressWarnings("unchecked")
	public OptionEntity get(long id) {
		return (OptionEntity) super.get(optionEntityClass, id);
	}
	
	@Override
	public void create(OptionEntity entity) {
		entity.create();
		super.create(entity);
	}
	
	@Override
	public void update(OptionEntity entity) {
		entity.update();
		super.update(entity);
	}
	
	@Override
	public void delete(OptionEntity entity) {
		if (realDelete) {			
			super.delete(entity);	
		} else {
			entity.delete();
			super.update(entity);
		}
	}
	
	@Override
	public void delete(long id) {
		OptionEntity entity = get(id);
		delete(entity);		
	}
	
	public boolean isCodeExist(String code) {
		try {
			if (StringUtils.isNotBlank(code)) {
				Session session = HibernateSessionFactory.getSession();
				String hql = "SELECT count(*) "
						+ "FROM " + this.optionEntityClass.getSimpleName() + " o "
						+ "WHERE o.code = :code AND o.isValid = :isValid";
				Query query = session.createQuery(hql);
				query.setString("code", code);
				query.setString("isValid", BaseEntity.valid);
				Object obj = query.uniqueResult();
				return (long)obj >= 1;			
			} else {
				throw new IllegalArgumentException("[code] can't be null or blank");
			}	
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
