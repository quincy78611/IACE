package iace.dao.industryInfo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.industryInfo.IndustryInfo;
import iace.entity.industryInfo.IndustryInfoSearchModel;

public class IndustryInfoDao extends BaseIaceDao<IndustryInfo> implements IIndustryInfoDao {

	public IndustryInfoDao() {
		super(IndustryInfo.class);
	}

	@Override
	public PagedList<IndustryInfo> searchBy(IndustryInfoSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<IndustryInfo> results = new PagedList<IndustryInfo>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			addCriteriaRestrictionsForSearch(arg, criteria);
			criteria.addOrder(Order.desc("postDate"));
			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<IndustryInfo> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long queryTotalRecordsCount(IndustryInfoSearchModel arg) {
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
	
	private void addCriteriaRestrictionsForSearch(IndustryInfoSearchModel arg, Criteria criteria) {
		if (StringUtils.isNotBlank(arg.getCategory())) {
			criteria.add(Restrictions.eq("category", arg.getCategory()));
		}
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}

	@Override
	public List<IndustryInfo> sampleForHomePage(String category) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property("id"), "id");
			projectionList.add(Projections.property("title"), "title");
			projectionList.add(Projections.property("postDate"), "postDate");
			projectionList.add(Projections.property("link"), "link");
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(Transformers.aliasToBean(super.entityClass));
			
			criteria.add(Restrictions.eq("category", category));
			criteria.addOrder(Order.desc("postDate"));
			criteria.setMaxResults(5);
			
			@SuppressWarnings("unchecked")
			List<IndustryInfo> list = criteria.list();
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	

}
