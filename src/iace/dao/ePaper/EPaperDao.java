package iace.dao.ePaper;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.ePaper.EPaper;
import iace.entity.ePaper.EPaperSearchModel;

public class EPaperDao extends BaseIaceDao<EPaper> implements IEPaperDao {

	public EPaperDao() {
		super(EPaper.class);
	}
	
	@Override
	protected List<Order> getDefaultOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("no"));
		orderList.add(Order.desc("postDate"));
		return orderList;
	}

	@Override
	public PagedList<EPaper> searchBy(EPaperSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<EPaper> results = new PagedList<EPaper>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);
			super.addDefaultOrder(criteria);
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<EPaper> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(EPaperSearchModel arg) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
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
	
	private void addCriteriaRestrictionsForSearch(EPaperSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getSearchText())) {
			criteria.add(Restrictions.like("title", arg.getSearchText(), MatchMode.ANYWHERE).ignoreCase());
		}
		if (arg.getYear() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			try {
				Date dateS = new Date( sdf.parse(arg.getYear().toString()).getTime() );
				Date dateE = new Date( sdf.parse(String.valueOf(arg.getYear()+1)).getTime() );
				criteria.add(Restrictions.ge("postDate", dateS));
				criteria.add(Restrictions.lt("postDate", dateE));
			} catch (ParseException e) {
				log.warn("", e);
			}
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

}
