package iace.dao.researchPlan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
import iace.entity.Technology;

public class ResearchPlanDao extends BaseIaceDao<ResearchPlan> implements IResearchPlanDao {

	public ResearchPlanDao() {
		super(ResearchPlan.class);
	}
	
	private ResearchPlan get(List<Criterion> criterions) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(ResearchPlan.class);
			criterions.forEach(v -> criteria.add(v));
			ResearchPlan rp = (ResearchPlan) criteria.uniqueResult();
			
			//這裡這麼做是因為Technology.optionTrlList 設定為FetchType.LAZY
			//靠存取該欄位才會實際從資料庫載入
			if (rp.getTechnologies() != null && rp.getTechnologies().size() > 0) {
				for (Technology t: rp.getTechnologies()) {
					t.getOptionTrlCodesString();
				}
			}
			
			return rp;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}		
	}
	
	@Override
	public ResearchPlan get(long id) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("id", id));
		criterions.add(Restrictions.eq("isValid", BaseEntity.valid));
		return get(criterions);
	}

	/**
	 * 查詢計畫編號是否存在
	 */
	@Override
	public boolean planNoExist(String planNo) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT count(*) " 
					+ "FROM " + ResearchPlan.class.getSimpleName() + " rp "
					+ "WHERE rp.planNo = :planNo "
					+ "AND rp.isValid = :isValid ";
			Query query = session.createQuery(hql);
			query.setString("planNo", planNo);
			query.setString("isValid", BaseEntity.valid);
			Object obj = query.uniqueResult();
			return (long)obj > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public ResearchPlan getByPlanNo(String planNo) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("planNo", planNo).ignoreCase());
		criterions.add(Restrictions.eq("isValid", BaseEntity.valid));
		return get(criterions);		
	}

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
			Criteria rCrit = criteria.createCriteria("technologies");
			rCrit.add(Restrictions.like("name", arg.getRndResultName(), MatchMode.ANYWHERE).ignoreCase());
		}		
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.valid));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}



}
