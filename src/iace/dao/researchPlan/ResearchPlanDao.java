package iace.dao.researchPlan;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.ResearchPlanSearchModel;
import iace.entity.researchPlan.Technology;

public class ResearchPlanDao extends BaseIaceDao<ResearchPlan> implements IResearchPlanDao {

	public ResearchPlanDao() {
		super(ResearchPlan.class);
	}
	
	@Override
	public ResearchPlan get(List<Criterion> criterions) {
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
		criterions.add(Restrictions.eq("isValid", BaseEntity.TRUE));
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
	public ResearchPlan getByPlanNo(String planNo) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("planNo", planNo).ignoreCase());
		criterions.add(Restrictions.eq("isValid", BaseEntity.TRUE));
		return get(criterions);		
	}

	@Override
	public PagedList<ResearchPlan> searchBy(ResearchPlanSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);			
		PagedList<ResearchPlan> results = new PagedList<ResearchPlan>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(ResearchPlan.class, "rp");
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
	
	@Override
	public List<ResearchPlan> listAll(ResearchPlanSearchModel arg) {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(ResearchPlan.class, "rp");
			addCriteriaRestrictionsForSearch(arg, criteria);		
			criteria.addOrder(Order.asc("id"));			
			
			@SuppressWarnings("unchecked")
			List<ResearchPlan> list = criteria.list();
			for (ResearchPlan rp : list) {
				for (Technology t : rp.getTechnologies()) {
					Hibernate.initialize(t.getOptionTrlList());
				}
			}
			
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(ResearchPlanSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(ResearchPlan.class, "rp");			
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
	
	private void addCriteriaRestrictionsForSearch(ResearchPlanSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getPlanNo())) {
			criteria.add(Restrictions.like("planNo", arg.getPlanNo(), MatchMode.START).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getPlanName())) {
			criteria.add(Restrictions.like("name", arg.getPlanName(), MatchMode.ANYWHERE).ignoreCase());
		}			
		if (arg.getYearStart() != null) {
			criteria.add(Restrictions.ge("year", arg.getYearStart()));
		}
		if (arg.getYearEnd() != null) {
			criteria.add(Restrictions.le("year", arg.getYearEnd()));
		}
		if (arg.getGrbDomainId() != null && arg.getGrbDomainId() > 0) {	
			Criterion[] rests = new Criterion[6];
			for (int i=0; i<6; i++) {
				criteria.createAlias("grbDomain"+(i+1), "grbD"+(i+1),  JoinType.LEFT_OUTER_JOIN);
				rests[i] = Restrictions.eq("grbD"+(i+1)+".id", arg.getGrbDomainId());
			}				
			criteria.add(Restrictions.or(rests));
		}
		if (StringUtils.isNotBlank(arg.getManager())) {
			criteria.add(Restrictions.like("manager", arg.getManager(), MatchMode.START).ignoreCase());
		}
		if (StringUtils.isNotBlank(arg.getKeyword())) {
			criteria.add(Restrictions.like("keyword", arg.getKeyword(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (arg.getTrlId() != null && arg.getTrlId() > 0) {
			criteria.createAlias("trl", "trl");
			criteria.add(Restrictions.eq("trl.id", arg.getTrlId()));
		}
		
		boolean tecNameNotBlank = StringUtils.isNotBlank(arg.getTechnologyName());
		boolean tecTrlNotBlank = arg.getTechnologyTrlId() != null && arg.getTechnologyTrlId() > 0;
		if (tecNameNotBlank || tecTrlNotBlank) {
			DetachedCriteria subquery = DetachedCriteria.forClass(Technology.class, "tec");
			if (tecNameNotBlank) {
				subquery.add(Restrictions.like("tec.name", arg.getTechnologyName(), MatchMode.ANYWHERE).ignoreCase());
			}
			if (tecTrlNotBlank) {
				subquery.createAlias("tec.optionTrlList", "trlL");
				subquery.add(Restrictions.eq("trlL.id", arg.getTechnologyTrlId()));
			}
			subquery.setProjection( Projections.property("tec.researchPlan.id") );
			criteria.add(Subqueries.propertyIn("rp.id", subquery));
		}
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // IMPORTANT: without this line, it might return duplicate entities when main entity(ResearchPlan) has more than one child entity(Technology)
	}

	@Override
	public List<Integer> getYearList() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(ResearchPlan.class);
			criteria.setProjection(Projections.property("year"));
			criteria.setProjection(Projections.distinct(Projections.property("year")));
			criteria.addOrder(Order.asc("year"));
			@SuppressWarnings("unchecked")
			List<Integer> yearList = criteria.list();
			return yearList;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	

}
