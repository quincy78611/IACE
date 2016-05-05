package iace.dao;

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
import iace.entity.OptionIndustry;

public class OptionIndustryDao extends BaseDao<OptionIndustry> implements IOptionIndustryDao {
	
	private static boolean realDelete = true;
	
	@SuppressWarnings("unchecked")
	public List<OptionIndustry> listAll() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("isValid", BaseEntity.valid));		
		return (List<OptionIndustry>) super.listAll(OptionIndustry.class, Order.asc("code"), criterionList);
	}
	
	public OptionIndustry get(long id) {
		return (OptionIndustry) super.get(OptionIndustry.class, id);
	}
	
	@Override
	public void create(OptionIndustry entity) {
		entity.create();
		super.create(entity);
	}

	@Override
	public void update(OptionIndustry entity) {
		entity.update();
		super.update(entity);
	}
	
	@Override
	public void delete(OptionIndustry entity) {
		if (realDelete) {			
			super.delete(entity);	
		} else {
			entity.delete();
			super.update(entity);
		}
	}
	
	@Override
	public void delete(long id) {
		OptionIndustry entity = get(id);
		delete(entity);		
	}
	
	public boolean isCodeExist(String code) {
		try {
			if (StringUtils.isNotBlank(code)) {
				Session session = HibernateSessionFactory.getSession();
				String hql = "select count(*) from OptionIndustry o where o.code = :code and o.isValid = :isValid";
				Query query = session.createQuery(hql);
				query.setString("code", code);
				query.setString("isValid", BaseEntity.valid);
				Object obj = query.uniqueResult();
				return (long)obj == 1;			
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
