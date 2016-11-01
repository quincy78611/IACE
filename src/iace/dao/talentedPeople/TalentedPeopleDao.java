package iace.dao.talentedPeople;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.sys.SysUser;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;

public class TalentedPeopleDao extends BaseIaceDao<TalentedPeople> implements ITalentedPeopleDao {

	public TalentedPeopleDao() {
		super(TalentedPeople.class);
	}

	@Override
	public PagedList<TalentedPeople> searchBy(TalentedPeopleSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<TalentedPeople> results = new PagedList<TalentedPeople>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(TalentedPeople.class);
			addCriteriaRestrictionsForSearch(arg, criteria);		
			
			criteria.addOrder(Order.asc("id"));
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<TalentedPeople> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public List<TalentedPeople> listAll(TalentedPeopleSearchModel arg) {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(TalentedPeople.class);
			addCriteriaRestrictionsForSearch(arg, criteria);		
			
			criteria.addOrder(Order.asc("id"));
			
			@SuppressWarnings("unchecked")
			List<TalentedPeople> list = criteria.list();
			for (TalentedPeople tp : list) {
				Hibernate.initialize(tp.getDomains());
			}
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public long queryTotalRecordsCount(TalentedPeopleSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(TalentedPeople.class);
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
		
	private void addCriteriaRestrictionsForSearch(TalentedPeopleSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getName())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("nameCh", arg.getName(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("nameEn", arg.getName(), MatchMode.ANYWHERE).ignoreCase());
			criteria.add(or);
		}
		if (StringUtils.isNotBlank(arg.getGender())) {
			criteria.add(Restrictions.eq("gender", arg.getGender()));
		}
		if (arg.getExpYearS() != null) {
			criteria.add(Restrictions.ge("expYear", arg.getExpYearS()));
		}
		if (arg.getExpYearE() != null) {
			criteria.add(Restrictions.le("expYear", arg.getExpYearE()));
		}
		if (StringUtils.isNotBlank(arg.getWorkOrg())) {
			criteria.add(Restrictions.like("workOrg", arg.getWorkOrg(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(arg.getJob())) {
			criteria.add(Restrictions.like("job", arg.getJob(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotBlank(arg.getSpecialty())) {
			criteria.add(Restrictions.like("specialty", arg.getSpecialty(), MatchMode.ANYWHERE));
		}
		
		if (arg.getGrbDomainIdList() != null && arg.getGrbDomainIdList().size() > 0) {
			Criteria subCriteria = criteria.createCriteria("domains", JoinType.INNER_JOIN);
			if (arg.getGrbDomainIdList() != null) {
				subCriteria.add(Restrictions.in("id", arg.getGrbDomainIdList()));
			}
		}
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // IMPORTANT: without this line, it might return duplicate entities when main entity(ResearchPlan) has more than one child entity(Technology)
	}

	@Override
	public TalentedPeople get(long id) {
		Session session = HibernateSessionFactory.getSession();
		try {
			Criteria criteria = session.createCriteria(TalentedPeople.class);
			criteria.add(Restrictions.eq("id", id));
			TalentedPeople res = (TalentedPeople) criteria.uniqueResult();
			Hibernate.initialize(res.getDomains());
			Hibernate.initialize(res.getRdResults());
			Hibernate.initialize(res.getTransferCases());
			Hibernate.initialize(res.getMainProjects());
			
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public TalentedPeople get(SysUser user) {
		return get(user.getId());
	}

	
}
