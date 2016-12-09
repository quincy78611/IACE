package iace.dao.patent;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.patent.Patent;
import iace.entity.patent.PatentSearchModel;

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
			query.setString("isValid", BaseEntity.TRUE);
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
	public List<Patent> listAll(PatentSearchModel model) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Patent.class);
			addCriteriaRestrictionsForSearch(model, criteria);
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

	@Override
	public PagedList<Patent> searchBy(PatentSearchModel model) {
		long totalItemCount = queryTotalRecordsCount(model);
		PagedList<Patent> results = new PagedList<Patent>(totalItemCount, model.getPageSize(), model.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Patent.class);
			addCriteriaRestrictionsForSearch(model, criteria);
			criteria.addOrder(Order.asc("id"));
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(model.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<Patent> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public long queryTotalRecordsCount(PatentSearchModel model) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Patent.class);
			addCriteriaRestrictionsForSearch(model, criteria);

			criteria.setProjection(Projections.rowCount());
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private void addCriteriaRestrictionsForSearch(PatentSearchModel model, Criteria criteria) {
		if (StringUtils.isNotBlank(model.getName())) {
			criteria.add(Restrictions.like("name", model.getName(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(model.getAssignee())) {
			criteria.add(Restrictions.like("assignee", model.getAssignee(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(model.getAppliactionNo())) {
			criteria.add(Restrictions.like("appliactionNo", model.getAppliactionNo(), MatchMode.START).ignoreCase());
		}
		if (model.getApplicationDateS() != null) {
			criteria.add(Restrictions.ge("applicationDate", model.getApplicationDateS()));
		}
		if (model.getApplicationDateE() != null) {
			criteria.add(Restrictions.le("applicationDate", model.getApplicationDateE()));
		}
		if (StringUtils.isNotBlank(model.getCountryCode())) {
			criteria.add(Restrictions.eq("country.code", model.getCountryCode()));
		}
		if (model.getTechFieldId() > 0) {
			criteria.add(Restrictions.eq("techField.id", model.getTechFieldId()));
		}		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getUKs() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT appliactionNo, techField.id "
					+ "FROM " + Patent.class.getSimpleName() + " p "
					+ "WHERE p.isValid = :isValid ";
			Query query = session.createQuery(hql);
			query.setString("isValid", BaseEntity.TRUE);
			List<Object[]> objs = query.list();
			Set<String> ukSet = new LinkedHashSet<String>();
			for (Object[] obj : objs) {				 
				String uk = String.format("%s-%d", obj[0], obj[1]);
				ukSet.add(uk);
			}
			
			return ukSet;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Patent> sampleForHomePage() {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Patent.class);
			criteria.add(Restrictions.isNotNull("openDate"));
			criteria.addOrder(Order.desc("openDate"));
			criteria.setMaxResults(3);
			
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
