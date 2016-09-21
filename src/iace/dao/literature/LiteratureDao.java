package iace.dao.literature;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.literature.Literature;
import iace.entity.literature.LiteratureSearchModel;

public class LiteratureDao extends BaseIaceDao<Literature> implements ILiteratureDao {

	public LiteratureDao() {
		super(Literature.class);
	}

	@Override
	public Literature getByOid(Long oid) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("oid", oid));
		criterions.add(Restrictions.eq("isValid", BaseEntity.TRUE));
		return super.get(criterions);
	}

	@Override
	public List<Long> listAllOid() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT oid "
					+ "FROM " + Literature.class.getSimpleName() + " l "
					+ "WHERE l.isValid = :isValid";
			Query query = session.createQuery(hql);
			query.setString("isValid", BaseEntity.TRUE);
			@SuppressWarnings("unchecked")
			List<Long> list = query.list();
			return list;			
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public PagedList<Literature> searchBy(LiteratureSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount(arg);
		PagedList<Literature> results = new PagedList<Literature>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Literature.class);
			addCriteriaRestrictionsForSearch(arg, criteria);		
			
			criteria.addOrder(Order.asc("id"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<Literature> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private long queryTotalRecordsCount(LiteratureSearchModel arg){
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Literature.class);
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

	private void addCriteriaRestrictionsForSearch(LiteratureSearchModel arg, Criteria criteria) {	
		// TODO
		
		
		
		criteria.add(Restrictions.eq("category", arg.getCategory()));
		criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
	}
}
