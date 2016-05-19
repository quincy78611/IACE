package iace.dao.researchPlan;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.ResearchPlan;
import iace.entity.ResearchPlanSearchModel;

public class ResearchPlanDao extends BaseIaceDao<ResearchPlan> implements IResearchPlanDao {

	public ResearchPlanDao() {
		super(ResearchPlan.class);
	}
	
//	@Override
//	public ResearchPlan get(long id) {
//		try {
//			Session session = HibernateSessionFactory.getSession();
//			Criteria criteria = session.createCriteria(ResearchPlan.class);			
//			criteria.add(Restrictions.eq("id", id));
//			criteria.add(Restrictions.eq("isValid", BaseEntity.valid));
//			Criteria rCrit = criteria.createCriteria("rndResults");
//			rCrit.add(Restrictions.eq("isValid", BaseEntity.valid));
//			ResearchPlan entity = (ResearchPlan) criteria.uniqueResult();
//			return entity;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}



	@Override
	public PagedList<ResearchPlan> searchBy(ResearchPlanSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);			
		PagedList<ResearchPlan> results = new PagedList<ResearchPlan>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(ResearchPlan.class);
			addCriteriaRestrictionsForSearch(arg, criteria);		
			
			criteria.addOrder(Order.asc("id"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<ResearchPlan> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	private long queryTotalRecordsCount(ResearchPlanSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(ResearchPlan.class);			
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
	
	private void addCriteriaRestrictionsForSearch(ResearchPlanSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getPlanNo())) {
			criteria.add(Restrictions.like("planNo", arg.getPlanNo(), MatchMode.START).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getPlanName())) {
			criteria.add(Restrictions.like("name", arg.getPlanName(), MatchMode.ANYWHERE).ignoreCase());
		}			
		if (arg.getYear() != null) {
			criteria.add(Restrictions.eq("year", arg.getYear()));
		}
		if (StringUtils.isNotBlank(arg.getGrbDomainCode())) {				
			Criterion[] rests = new Criterion[6];
			for (int i=0; i<6; i++) {
//				String propertyName = "grbDomainCode"+(i+1);
				String propertyName = "grbDomain"+(i+1)+".code";
				rests[i] = Restrictions.eq(propertyName, arg.getGrbDomainCode());
			}				
			criteria.add(Restrictions.or(rests));
		}
		if (StringUtils.isNotBlank(arg.getManager())) {
			criteria.add(Restrictions.like("manager", arg.getManager(), MatchMode.START).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getKeyword())) {
			criteria.add(Restrictions.like("keyword", arg.getKeyword(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getTrlCode())) {
			criteria.add(Restrictions.eq("trl.code", arg.getTrlCode()));
		}
		if (StringUtils.isNotBlank(arg.getRndResultName())) {
			Criteria rCrit = criteria.createCriteria("rndResults");
			rCrit.add(Restrictions.like("name", arg.getRndResultName(), MatchMode.ANYWHERE).ignoreCase());
		}		
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.valid));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}


}
