package iace.dao.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysLogSearchModel;

public class SysLogDao extends BaseIaceDao<SysLog> implements ISysLogDao {

	public SysLogDao() {
		super(SysLog.class);
	}
	
	@Override
	public List<String> getNamespaceList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysLog.class);
			criteria.setProjection(Projections.distinct(Projections.property("namespace")));
			@SuppressWarnings("unchecked")
			List<String> results = criteria.list();
			
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<String> getActionNameList(String namespace) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysLog.class);
			if (StringUtils.isNotBlank(namespace)) {
				criteria.add(Restrictions.eq("namespace", namespace));
			}
			criteria.setProjection(Projections.distinct(Projections.property("actionName")));
			@SuppressWarnings("unchecked")
			List<String> results = criteria.list();
			
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public PagedList<SysLog> searchBy(SysLogSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);			
		PagedList<SysLog> results = new PagedList<SysLog>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysLog.class);
			addCriteriaRestrictionsForSearch(arg, criteria);		
			
			criteria.addOrder(Order.desc("createTime"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<SysLog> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	
	private long queryTotalRecordsCount(SysLogSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysLog.class);			
			addCriteriaRestrictionsForSearch(arg, criteria);
			
//			criteria.setProjection(Projections.rowCount());
			criteria.setProjection(Projections.countDistinct("id")); // when using rowCount() and there are more than one child entities, then it will return the number of child entities instead of only count main entity
			
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	private void addCriteriaRestrictionsForSearch(SysLogSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getNamespace())) {
			criteria.add(Restrictions.eq("namespace", arg.getNamespace()));
		}
		if (StringUtils.isNotBlank(arg.getActionName())) {
			criteria.add(Restrictions.eq("actionName", arg.getActionName()));
		}
		if (arg.getSysUserId() != null) {
			criteria.add(Restrictions.eq("sysUser.id", arg.getSysUserId()));
		}
		if (arg.getTimeS() != null) {
			criteria.add(Restrictions.ge("createTime", arg.getTimeS()));
		}
		if (arg.getTimeE() != null) {
			criteria.add(Restrictions.le("createTime", arg.getTimeE()));
		}		
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}
}
