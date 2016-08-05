package iace.dao.qnrCooperateWay;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.qnrCooperateWay.QnrCooperateWay;

public class QnrCooperateWayDao extends BaseIaceDao<QnrCooperateWay> implements IQnrCooperateWayDao {

	public QnrCooperateWayDao() {
		super(QnrCooperateWay.class);
	}

	@Override
	public List<Long> listAllSchoolId() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT school.id "
					+ "FROM " + QnrCooperateWay.class.getSimpleName() + " q "
					+ "WHERE q.isValid = :isValid";
			Query query = session.createQuery(hql);
			query.setString("isValid", BaseEntity.TRUE);
			@SuppressWarnings("unchecked")
			List<Long> res = query.list();
			return res;			
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	
}
