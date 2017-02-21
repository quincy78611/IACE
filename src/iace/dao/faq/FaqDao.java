package iace.dao.faq;

import java.util.ArrayList;
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
import iace.entity.faq.Faq;
import iace.entity.faq.FaqSearchModel;

public class FaqDao extends BaseIaceDao<Faq> implements IFaqDao {

	public FaqDao() {
		super(Faq.class);
	}
	
	@Override
	protected List<Order> getDefaultOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("sort"));
		orderList.add(Order.asc("id"));
		return orderList;
	}

	@Override
	public PagedList<Faq> searchBy(FaqSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<Faq> results = new PagedList<Faq>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Faq.class);
			addCriteriaRestrictionsForSearch(arg, criteria);
			super.addDefaultOrder(criteria);
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<Faq> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(FaqSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Faq.class);
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

	private void addCriteriaRestrictionsForSearch(FaqSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			criteria.add(Restrictions.like("title", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getCategory())) {
			criteria.add(Restrictions.eq("category", arg.getCategory()));
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}
}
