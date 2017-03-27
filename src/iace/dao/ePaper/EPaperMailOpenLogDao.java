package iace.dao.ePaper;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.ePaper.EPaperMailOpenLog;

public class EPaperMailOpenLogDao extends BaseIaceDao<EPaperMailOpenLog> implements IEPaperMailOpenLogDao {

	public EPaperMailOpenLogDao() {
		super(EPaperMailOpenLog.class);
	}

	@Override
	public long openCount(long epaperId, String fromEmail) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("epaperId", epaperId));
			criteria.add(Restrictions.eq("email", fromEmail));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			return count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	@Override
	public long totalOpenCount(long epaperId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("epaperId", epaperId));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			return count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long distinctPeopleOpenCount(long epaperId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			criteria.add(Restrictions.eq("epaperId", epaperId));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setProjection(Projections.distinct(Projections.property("email")));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			return count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	

}
