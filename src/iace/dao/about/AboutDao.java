package iace.dao.about;

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
import iace.entity.about.About;
import iace.entity.about.AboutSearchModel;

public class AboutDao extends BaseIaceDao<About> implements IAboutDao {

	public AboutDao() {
		super(About.class);
	}
	
	@Override
	protected List<Order> getDefaultOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("sort"));
		orderList.add(Order.asc("id"));
		return orderList;
	}

	@Override
	public PagedList<About> searchBy(AboutSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<About> results = new PagedList<About>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(About.class);
			addCriteriaRestrictionsForSearch(arg, criteria);
			super.addDefaultOrder(criteria);
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<About> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(AboutSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(About.class);
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

	private void addCriteriaRestrictionsForSearch(AboutSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			criteria.add(Restrictions.like("title", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}
}
