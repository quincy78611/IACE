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
import iace.entity.enterpriseNeed.EnterpriseInfo;
import iace.entity.enterpriseNeed.EnterpriseNeedSearchModel;

public class EnterpriseInfoDao extends BaseIaceDao<EnterpriseInfo> implements IEnterpriseInfoDao {

	public EnterpriseInfoDao() {
		super(EnterpriseInfo.class);
	}

	@Override
	public PagedList<EnterpriseInfo> searchBy(EnterpriseNeedSearchModel arg) {
		try {
			long totalItemCount = queryTotalRecordsCount(arg);
			PagedList<EnterpriseInfo> results = new PagedList<EnterpriseInfo>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
			
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(EnterpriseInfo.class);
			addCriteriaRestrictionsForSearch(arg, criteria);
			criteria.addOrder(Order.asc("companyId"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<EnterpriseInfo> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private long queryTotalRecordsCount(EnterpriseNeedSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(EnterpriseInfo.class);
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

	private void addCriteriaRestrictionsForSearch(EnterpriseNeedSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("name", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("companyId", arg.getSearchText(), MatchMode.START).ignoreCase());			
			criteria.add(or);
		}
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

}
