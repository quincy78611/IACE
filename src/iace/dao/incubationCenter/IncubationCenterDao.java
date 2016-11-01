package iace.dao.incubationCenter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.incubationCenter.IncubationCenter;
import iace.entity.incubationCenter.IncubationCenterSearchModel;

public class IncubationCenterDao extends BaseIaceDao<IncubationCenter> implements IIncubationCenterDao {

	public IncubationCenterDao() {
		super(IncubationCenter.class);
	}

	@Override
	public PagedList<IncubationCenter> searchBy(IncubationCenterSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<IncubationCenter> results = new PagedList<IncubationCenter>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(IncubationCenter.class);
			addCriteriaRestrictionsForSearch(arg, criteria);		
			
			criteria.addOrder(Order.asc("id"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			// select only specific columns
			ProjectionList projs = Projections.projectionList()
				      .add(Projections.property("id"), "id")
				      .add(Projections.property("attribute"), "attribute")
				      .add(Projections.property("schoolNameCh"), "schoolNameCh")
				      .add(Projections.property("orgNameCh"), "orgNameCh");
			criteria.setProjection(projs);
			criteria.setResultTransformer(Transformers.aliasToBean(IncubationCenter.class));;
			
			@SuppressWarnings("unchecked")
			List<IncubationCenter> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public long queryTotalRecordsCount(IncubationCenterSearchModel arg){
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(IncubationCenter.class);
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

	private void addCriteriaRestrictionsForSearch(IncubationCenterSearchModel arg, Criteria criteria) {		
		if (StringUtils.isNotBlank(arg.getAttribute())) {
			criteria.add(Restrictions.eq("attribute", arg.getAttribute()));
		}
		if (StringUtils.isNotBlank(arg.getName())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("schoolNameCh", arg.getName(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("orgNameCh", arg.getName(), MatchMode.ANYWHERE).ignoreCase());
			criteria.add(or);
		}
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}
}
