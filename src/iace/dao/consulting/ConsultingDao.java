package iace.dao.consulting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import iace.entity.consulting.Consulting;
import iace.entity.consulting.ConsultingSearchModel;

public class ConsultingDao extends BaseIaceDao<Consulting> implements IConsultingDao {

	public ConsultingDao() {
		super(Consulting.class);
	}
	
	@Override
	protected List<Order> getDefaultOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("createTime"));
		return orderList;
	}

	public PagedList<Consulting> searchBy(ConsultingSearchModel model) {
		long totalItemCount = queryTotalRecordsCount(model);
		PagedList<Consulting> results = new PagedList<Consulting>(totalItemCount, model.getPageSize(), model.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Consulting.class);
			addCriteriaRestrictionsForSearch(model, criteria);
			super.addDefaultOrder(criteria);
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(model.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<Consulting> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public List<Consulting> listAll(ConsultingSearchModel model) {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Consulting.class);
			addCriteriaRestrictionsForSearch(model, criteria);
			super.addDefaultOrder(criteria);
			
			@SuppressWarnings("unchecked")
			List<Consulting> list = criteria.list();
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private long queryTotalRecordsCount(ConsultingSearchModel model){
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Consulting.class);
			addCriteriaRestrictionsForSearch(model, criteria);
			criteria.setProjection(Projections.rowCount());
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private void addCriteriaRestrictionsForSearch(ConsultingSearchModel model, Criteria criteria) {		
		if (StringUtils.isNotBlank(model.getSearchText())) {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.like("name", model.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
			or.add(Restrictions.like("organization", model.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
			criteria.add(or);
		}
		if (StringUtils.isNotBlank(model.getOptionOrganizationTypeCode())) {
			criteria.add(Restrictions.eq("optionOrganizationType.code", model.getOptionOrganizationTypeCode()));
		}
		if (StringUtils.isNotBlank(model.getOptionConsultCode())) {
			criteria.add(Restrictions.eq("optionConsult.code", model.getOptionConsultCode()));
		}
		if (StringUtils.isNotBlank(model.getOptionIndustryCode())) {
			criteria.add(Restrictions.eq("optionIndustry.code", model.getOptionIndustryCode()));
		}	
		if (model.getConsultDateStart() != null) {
			criteria.add(Restrictions.ge("consultDate", model.getConsultDateStart()));
		}
		if (model.getConsultDateEnd() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(model.getConsultDateEnd()); 
//			c.add(Calendar.DATE, 1);
			Date endDate = c.getTime();
			criteria.add(Restrictions.le("consultDate", endDate));
		}
		
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}


}
