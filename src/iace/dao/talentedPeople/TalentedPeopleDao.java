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

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
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
	
	private long queryTotalRecordsCount(TalentedPeopleSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(TalentedPeople.class);
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
		if (arg.getExpYear() != null) {
			criteria.add(Restrictions.eq("expYear", arg.getExpYear()));
		}		
		if (StringUtils.isNotBlank(arg.getTel())) {
			criteria.add(Restrictions.like("tel", arg.getTel(), MatchMode.START));
		}
		if (StringUtils.isNotBlank(arg.getEmail())) {
			criteria.add(Restrictions.like("email", arg.getEmail(), MatchMode.START));
		}		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

	@Override
	public TalentedPeople get(long id) {
		Session session = HibernateSessionFactory.getSession();
		try {
			Criteria criteria = session.createCriteria(TalentedPeople.class);
			criteria.add(Restrictions.eq("id", id));
			TalentedPeople res = (TalentedPeople) criteria.uniqueResult();
			Hibernate.initialize(res.getDomains());
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	
}
