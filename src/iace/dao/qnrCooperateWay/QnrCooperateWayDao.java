package iace.dao.qnrCooperateWay;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.qnrCooperateWay.QnrCooperateWay;

public class QnrCooperateWayDao extends BaseIaceDao<QnrCooperateWay> implements IQnrCooperateWayDao {

	public QnrCooperateWayDao() {
		super(QnrCooperateWay.class);
	}

	@Override
	public QnrCooperateWay getBySchool(long schoolId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(QnrCooperateWay.class);
			criteria.add(Restrictions.eq("school.id", schoolId));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			
			return (QnrCooperateWay) criteria.uniqueResult();			
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
