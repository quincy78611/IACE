package iace.dao.coopExample;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
import iace.entity.coopExample.CoopEx;
import iace.entity.coopExample.CoopExSearchModel;

public class CoopExDao extends BaseIaceDao<CoopEx> implements ICoopExDao {

	public CoopExDao() {
		super(CoopEx.class);
	}
	
	@Override
	public CoopEx get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopEx.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			CoopEx entity = (CoopEx) criteria.uniqueResult();
			Hibernate.initialize(entity);
			Hibernate.initialize(entity.getImgs());
			Hibernate.initialize(entity.getVideos());
			Hibernate.initialize(entity.getAttachFiles());
			
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<CoopEx> listAll() {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopEx.class);
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.addOrder(Order.desc("year"));
			criteria.addOrder(Order.asc("projName"));
			
			@SuppressWarnings("unchecked")
			List<CoopEx> list = criteria.list();
			Hibernate.initialize(list);
			for (CoopEx coopEx : list) {
				Hibernate.initialize(coopEx);
				Hibernate.initialize(coopEx.getImgs());
			}
			
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public boolean isProjNameExist(long currentId, String projName) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT count(*) " 
					+ "FROM " + CoopEx.class.getSimpleName() + " ce "
					+ "WHERE ce.id != :currentId "
					+ "AND ce.projName = :projName "
					+ "AND ce.isValid = :isValid ";
			Query query = session.createQuery(hql);
			query.setLong("currentId", currentId);
			query.setString("projName", projName);
			query.setString("isValid", BaseEntity.TRUE);
			Object obj = query.uniqueResult();
			return (long)obj > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public PagedList<CoopEx> searchBy(CoopExSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);			
		PagedList<CoopEx> results = new PagedList<CoopEx>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopEx.class);
			addCriteriaRestrictionsForSearch(arg, criteria);		
			
			criteria.addOrder(Order.asc("id"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<CoopEx> list = criteria.list();
			for (CoopEx c : list) {
				Hibernate.initialize(c);
				Hibernate.initialize(c.getImgs());
			}
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public long queryTotalRecordsCount(CoopExSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopEx.class);			
			addCriteriaRestrictionsForSearch(arg, criteria);
			
			criteria.setProjection(Projections.countDistinct("id")); // when using rowCount() and there are more than one child entities, then it will return the number of child entities instead of only count main entity
			
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	private void addCriteriaRestrictionsForSearch(CoopExSearchModel arg, Criteria criteria) {
		if (arg.getYear() != null) {
			criteria.add(Restrictions.eq("year", arg.getYear()));
		}
		if (StringUtils.isNotBlank(arg.getType())) {
			criteria.add(Restrictions.eq("type", arg.getType()));
		}
		if (StringUtils.isNotBlank(arg.getProjName())) {
			criteria.add(Restrictions.like("projName", arg.getProjName(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getRdTeam())) {
			criteria.add(Restrictions.like("rdTeam", arg.getRdTeam(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getAssisTeam())) {
			criteria.add(Restrictions.like("assisTeam", arg.getAssisTeam(), MatchMode.ANYWHERE).ignoreCase());
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // IMPORTANT: without this line, it might return duplicate entities when main entity(ResearchPlan) has more than one child entity(Technology)
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoopEx> sampleForHomePage() {
		try {
			List<CoopEx> resList = new ArrayList<CoopEx>();
			Session session = HibernateSessionFactory.getSession();
			
			{
				Criteria criteria = session.createCriteria(CoopEx.class);
				criteria.add(Restrictions.eq("type", "商品化"));
				criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
				criteria.addOrder(Order.asc("id"));
				criteria.setMaxResults(2);
				List<CoopEx> list = criteria.list();
				resList.addAll(list);
			}

			{
				Criteria criteria = session.createCriteria(CoopEx.class);
				criteria.add(Restrictions.eq("type", "專利推廣"));
				criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
				criteria.addOrder(Order.asc("id"));
				criteria.setMaxResults(2);
				List<CoopEx> list = criteria.list();
				resList.addAll(list);
			}
			
			{
				Criteria criteria = session.createCriteria(CoopEx.class);
				criteria.add(Restrictions.eq("type", "新創事業"));
				criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
				criteria.addOrder(Order.asc("id"));
				criteria.setMaxResults(1);
				List<CoopEx> list = criteria.list();
				resList.addAll(list);
			}
			
			for (CoopEx c : resList) {
				Hibernate.initialize(c);
				Hibernate.initialize(c.getImgs());
//				Hibernate.initialize(c.getVideos());
//				Hibernate.initialize(c.getAttachFiles());
			}
			
			return resList;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	
}
