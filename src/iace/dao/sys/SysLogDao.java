package iace.dao.sys;

import java.util.List;

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
		if (arg.getOptionSysNamespaceId() != null) {
			criteria.add(Restrictions.eq("optionSysNamespace.id", arg.getOptionSysNamespaceId()));
		}
		if (arg.getOptionSysActionId() != null) {
			criteria.add(Restrictions.eq("optionSysAction.id", arg.getOptionSysActionId()));
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
