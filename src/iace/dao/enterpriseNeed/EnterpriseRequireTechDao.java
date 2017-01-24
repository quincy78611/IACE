package iace.dao.enterpriseNeed;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.enterpriseNeed.EnterpriseRequireTech;
import iace.entity.enterpriseNeed.EnterpriseRequireTechSearchModel;

public class EnterpriseRequireTechDao extends BaseIaceDao<EnterpriseRequireTech> implements IEnterpriseRequireTechDao {

	public EnterpriseRequireTechDao() {
		super(EnterpriseRequireTech.class);
	}

	@Override
	public PagedList<EnterpriseRequireTech> searchBy(EnterpriseRequireTechSearchModel arg) {
		try {
			long totalItemCount = queryTotalRecordsCount(arg);
			PagedList<EnterpriseRequireTech> results = new PagedList<EnterpriseRequireTech>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
			
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(EnterpriseRequireTech.class);
			addCriteriaRestrictionsForSearch(arg, criteria);
			criteria.addOrder(Order.asc("id"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<EnterpriseRequireTech> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(EnterpriseRequireTechSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);

			criteria.setProjection(Projections.rowCount());
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private void addCriteriaRestrictionsForSearch(EnterpriseRequireTechSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("phase2", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("phase3", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());			
			criteria.add(or);
		}
		if (arg.getOptionDomainId() != null) {
			criteria.add(Restrictions.eq("phase1.id", arg.getOptionDomainId()));
		}
		
		criteria.add(Restrictions.isNotNull("phase1"));
		criteria.add(Restrictions.isNotNull("phase2"));
		criteria.add(Restrictions.isNotNull("phase3"));
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}
}
