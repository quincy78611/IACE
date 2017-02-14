package iace.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import core.dao.BaseDao;
import core.dao.HibernateSessionFactory;

public class ClickNumCounterDao {
	protected static Logger log = LogManager.getLogger(BaseDao.class);
	
	public int increaseClickNum(long id, Class<?> cls) {	
		Transaction tran = null;
		try {
			Session session = HibernateSessionFactory.getSession();
			tran = session.beginTransaction();
			String hql = "UPDATE " + cls.getSimpleName()
					+ " SET clickNum = (clickNum + 1) " 
					+ " WHERE id = :id";
			Query query = session.createQuery(hql);
			query.setLong("id", id);
			int result = query.executeUpdate();
			tran.commit();
			return result;
		} catch (Exception e) {
			log.warn("", e);
			if (tran != null) {
				tran.rollback();
			}
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
