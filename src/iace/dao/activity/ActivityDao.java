package iace.dao.activity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.activity.Activity;
import iace.entity.activity.ActivitySearchModel;

public class ActivityDao extends BaseIaceDao<Activity> implements IActivityDao {

	public ActivityDao() {
		super(Activity.class);
	}
	
	@Override
	public Activity get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			Activity entity = (Activity) criteria.uniqueResult();
			Hibernate.initialize(entity);
			Hibernate.initialize(entity.getAttachList());
			Hibernate.initialize(entity.getVideoList());
			
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public PagedList<Activity> searchBy(ActivitySearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<Activity> results = new PagedList<Activity>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);
			criteria.addOrder(Order.desc("sort"));
			criteria.addOrder(Order.asc("id"));
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<Activity> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(ActivitySearchModel arg) {
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

	private void addCriteriaRestrictionsForSearch(ActivitySearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			criteria.add(Restrictions.like("title", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getCategory())) {
			criteria.add(Restrictions.eq("category", arg.getCategory()));
		}
		if (arg.getYear() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			try {
				Date dateS = new Date( sdf.parse(arg.getYear().toString()).getTime() );
				Date dateE = new Date( sdf.parse(String.valueOf(arg.getYear()+1)).getTime() );
				criteria.add(Restrictions.ge("createTime", dateS));
				criteria.add(Restrictions.lt("createTime", dateE));
			} catch (ParseException e) {
				log.warn("", e);
			}
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

	@Override
	public List<Activity> sampleForHomePage() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.addOrder(Order.desc("sort"));
			criteria.setMaxResults(5);
			
			@SuppressWarnings("unchecked")
			List<Activity> list = criteria.list();
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	
}