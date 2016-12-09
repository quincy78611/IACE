package iace.dao.researchPlan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.BaseSearchModel;
import iace.entity.researchPlan.Technology;

public class TechnologyDao extends BaseIaceDao<Technology> implements ITechnologyDao {

	public TechnologyDao() {
		super(Technology.class);
	}

	@Override
	public Technology get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Technology.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			Technology t = (Technology) criteria.uniqueResult();
			
			//這裡這麼做是因為Technology.optionTrlList 設定為FetchType.LAZY
			//靠存取該欄位才會實際從資料庫載入
			t.getOptionTrlCodesString();
			
			return t;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<Long> getAllId(Long researchPlanGrbId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Technology.class);
			criteria.createAlias("researchPlan", "rp");
			Criterion[] rests = new Criterion[6];
			for (int i=0; i<6; i++) {
				criteria.createAlias("rp.grbDomain"+(i+1), "grbD"+(i+1),  JoinType.LEFT_OUTER_JOIN);
				rests[i] = Restrictions.eq("grbD"+(i+1)+".id", researchPlanGrbId);
			}				
			criteria.add(Restrictions.or(rests));
			criteria.setProjection( Projections.property("id") );

			@SuppressWarnings("unchecked")
			List<Long> res = criteria.list();
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public PagedList<Technology> searchBy(BaseSearchModel arg) {
		long totalItemCount = queryTotalRecordsCount();	
		PagedList<Technology> results = new PagedList<Technology>(totalItemCount, arg.getPageSize(), arg.getPageIndex());
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Technology.class);
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));	
			
			criteria.addOrder(Order.asc("id"));			
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(arg.getPageSize());
			
			@SuppressWarnings("unchecked")
			List<Technology> list = criteria.list();
			results.setList(list);
			
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public long queryTotalRecordsCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Technology.class);			
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			
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

	@Override
	public List<Technology> sampleForHomePage() {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Technology.class);
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.createAlias("researchPlan", "rp");
			
			criteria.addOrder(Order.desc("rp.year"));
			criteria.setMaxResults(2);
			
			@SuppressWarnings("unchecked")
			List<Technology> list = criteria.list();
			
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
