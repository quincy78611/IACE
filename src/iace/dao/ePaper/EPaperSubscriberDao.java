package iace.dao.ePaper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.ePaper.EPaperSubscriber;
import iace.entity.ePaper.EPaperSubscriberSearchModel;

public class EPaperSubscriberDao extends BaseIaceDao<EPaperSubscriber> implements IEPaperSubscriberDao {

	public EPaperSubscriberDao() {
		super(EPaperSubscriber.class);
	}

	@Override
	public PagedList<EPaperSubscriber> searchBy(EPaperSubscriberSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<EPaperSubscriber> results = new PagedList<EPaperSubscriber>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<EPaperSubscriber> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(EPaperSubscriberSearchModel arg) {
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
	
	private void addCriteriaRestrictionsForSearch(EPaperSubscriberSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("name", arg.getSearchText().trim(), MatchMode.ANYWHERE).ignoreCase());
//			or.add(Restrictions.like("orgName", arg.getSearchText().trim(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("tel", arg.getSearchText().trim(), MatchMode.START).ignoreCase());
			or.add(Restrictions.like("email", arg.getSearchText().trim(), MatchMode.START).ignoreCase());
			criteria.add(or);
		}
		if (arg.getIsSubscribe() != null) {
			criteria.add(Restrictions.eq("isSubscribe", arg.getIsSubscribe()));
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

	@Override
	public EPaperSubscriber getByEmail(String email) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("email", email));
			EPaperSubscriber res = (EPaperSubscriber) criteria.uniqueResult();
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		} 
	}

	
}
