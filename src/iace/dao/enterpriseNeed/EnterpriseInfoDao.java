package iace.dao.enterpriseNeed;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
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
	public EnterpriseInfo get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(EnterpriseInfo.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			EnterpriseInfo res = (EnterpriseInfo) criteria.uniqueResult();
			Hibernate.initialize(res.getEnterpriseRequireTech());
			Hibernate.initialize(res.getEnterpriseSituation());
			Hibernate.initialize(res.getEnterpriseAcademiaCoop());
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	protected List<Order> getDefaultOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("companyId"));
		orderList.add(Order.asc("id"));
		return orderList;
	}

	@Override
	public PagedList<EnterpriseInfo> searchBy(EnterpriseNeedSearchModel arg) {
		try {
			long totalItemCount = queryTotalRecordsCount(arg);
			PagedList<EnterpriseInfo> results = new PagedList<EnterpriseInfo>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
			
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(EnterpriseInfo.class);
			addCriteriaRestrictionsForSearch(arg, criteria);
			super.addDefaultOrder(criteria);
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
	
	@Override
	public List<EnterpriseInfo> listAllForExport(EnterpriseNeedSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(EnterpriseInfo.class);
			addCriteriaRestrictionsForSearch(arg, criteria);
			super.addDefaultOrder(criteria);
			criteria.setFetchMode("enterpriseRequireTech", FetchMode.JOIN);
			criteria.setFetchMode("enterpriseSituation", FetchMode.JOIN);
			criteria.setFetchMode("enterpriseAcademiaCoop", FetchMode.JOIN);
			
			@SuppressWarnings("unchecked")
			List<EnterpriseInfo> list = criteria.list();
			return list;
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
		if (arg.getOptionCompanyLocationId() != null) {
			criteria.add(Restrictions.eq("optionCompanyLocation.id", arg.getOptionCompanyLocationId()));
		}
		if (StringUtils.isNotBlank(arg.getPersonInChargeName())) {
			criteria.add(Restrictions.like("personInChargeName", arg.getPersonInChargeName(), MatchMode.START).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getIntervieweeName())) {
			criteria.add(Restrictions.like("intervieweeName", arg.getIntervieweeName(), MatchMode.START).ignoreCase());
		}
		criteria.createAlias("enterpriseRequireTech", "rt");
		if (arg.getInterviewDateS() != null) {
			criteria.add(Restrictions.ge("rt.interviewDate", arg.getInterviewDateS()));
		}
		if (arg.getInterviewDateE() != null) {
			criteria.add(Restrictions.le("rt.interviewDate", arg.getInterviewDateE()));
		}
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

}
