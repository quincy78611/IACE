package iace.dao.consulting;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.Consulting;

public class ConsultingDao extends BaseIaceDao<Consulting> implements IConsultingDao {

	public ConsultingDao() {
		super(Consulting.class);
	}

	@Override
	public PagedList<Consulting> searchBy(int pageIndex, int pageSize, String name, String organization) {
		long totalItemCount = queryTotalRecordsCount(name, organization);
		PagedList<Consulting> results = new PagedList<Consulting>(totalItemCount, pageSize, pageIndex);
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Consulting.class);
			addCriteriaRestrictionsForSearch(name, organization, criteria);		
			
			criteria.addOrder(Order.asc("id"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(pageSize);
			
			@SuppressWarnings("unchecked")
			List<Consulting> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private long queryTotalRecordsCount(String name, String organization) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Consulting.class);			
			addCriteriaRestrictionsForSearch(name, organization, criteria);
			criteria.setProjection(Projections.rowCount());
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private void addCriteriaRestrictionsForSearch(String name, String organization, Criteria criteria) {
		if (StringUtils.isNotBlank(name)) {
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(organization)) {
			criteria.add(Restrictions.like("organization", organization, MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

}
