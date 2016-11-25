package iace.dao.member;

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
import iace.entity.member.Member;
import iace.entity.member.MemberSearchModel;

public class MemberDao extends BaseIaceDao<Member> implements IMemberDao {

	public MemberDao() {
		super(Member.class);
	}

	@Override
	public boolean isAccountExist(String account) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("account", account));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			return count > 0;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	

	@Override
	public Member get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("id", id));
			Member member = (Member) criteria.uniqueResult();
			Hibernate.initialize(member.getOptIndustryList());
			
			return member;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Member getBy(String account, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("account", account));
			criteria.add(Restrictions.eq("password", password));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			Member member = (Member) criteria.uniqueResult();
			
			return member;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public PagedList<Member> searchBy(MemberSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<Member> results = new PagedList<Member>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);
			criteria.addOrder(Order.asc("account"));
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<Member> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(MemberSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);

			criteria.setProjection(Projections.countDistinct("id"));
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private void addCriteriaRestrictionsForSearch(MemberSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("account", arg.getSearchText(), MatchMode.START).ignoreCase());
			or.add(Restrictions.like("email", arg.getSearchText(), MatchMode.START).ignoreCase());
			or.add(Restrictions.like("name", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("tel", arg.getSearchText(), MatchMode.START).ignoreCase());
			or.add(Restrictions.like("mobile", arg.getSearchText(), MatchMode.START).ignoreCase());
			criteria.add(or);
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

}
