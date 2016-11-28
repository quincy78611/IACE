package iace.dao.customerService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
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
import iace.entity.customerService.ContactUs;
import iace.entity.customerService.ContactUsSearchModel;

public class ContactUsDao extends BaseIaceDao<ContactUs> implements IContactUsDao {

	public ContactUsDao() {
		super(ContactUs.class);
	}

	@Override
	public PagedList<ContactUs> searchBy(ContactUsSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<ContactUs> results = new PagedList<ContactUs>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);
			criteria.addOrder(Order.desc("id"));
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<ContactUs> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(ContactUsSearchModel arg) {
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

	private void addCriteriaRestrictionsForSearch(ContactUsSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("name", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("email", arg.getSearchText(), MatchMode.START).ignoreCase());
			criteria.add(or);
		}
		if (StringUtils.isNotBlank(arg.getBeenHandled())) {
			boolean beenHandled = Boolean.valueOf(arg.getBeenHandled());
			criteria.add(Restrictions.eq("beenHandled", beenHandled));
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}
}
