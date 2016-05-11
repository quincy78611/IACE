package iace.dao.patent;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.Patent;
import iace.entity.TechField;

public class PatentDao extends BaseIaceDao<Patent> implements IPatentDao {
	
	public PatentDao() {
		super(Patent.class);
	}

	@Override
	public boolean checkUK(Patent entity) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT count(*) " 
					+ "FROM " + Patent.class.getSimpleName() + " p "
					+ "WHERE p.appliactionNo = :appliactionNo "
					+ "AND p.techField.name = :techField "
					+ "AND p.isValid = :isValid "
					+ "AND p.id != :id ";
			Query query = session.createQuery(hql);
			query.setString("appliactionNo", entity.getAppliactionNo());
			query.setString("techField", entity.getTechField().getName());
			query.setString("isValid", BaseEntity.valid);
			query.setLong("id", entity.getId());
			Object obj = query.uniqueResult();
			return (long)obj == 0;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Patent> searchBy(String name, String appNo, String country, TechField techField) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Patent.class);
			criteria.add(Restrictions.eq("isValid", BaseEntity.valid));
			if (StringUtils.isNotBlank(name)) {
				criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
			}
			if (StringUtils.isNotBlank(appNo)) {
				criteria.add(Restrictions.like("appliactionNo", appNo, MatchMode.START).ignoreCase());
			}
			if (StringUtils.isNotBlank(country)) {
				criteria.add(Restrictions.eq("country", country));
			}
			if (techField != null) {
				criteria.add(Restrictions.eq("techField", techField));
			}
			criteria.addOrder(Order.asc("id"));
			@SuppressWarnings("unchecked")
			List<Patent> list = criteria.list();
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	

}