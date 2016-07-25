package iace.dao.qnrCooperateWay;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;

public class QnrCooperateWayMeritDao extends BaseIaceDao<QnrCooperateWayMerit> implements IQnrCooperateWayMeritDao {

	public QnrCooperateWayMeritDao() {
		super(QnrCooperateWayMerit.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QnrCooperateWayMerit> getBySchool(long schoolId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(QnrCooperateWayMerit.class);
			criteria.add(Restrictions.eq("school.id", schoolId));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			
			return (List<QnrCooperateWayMerit>) criteria.list();			
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public QnrCooperateWayMerit getBySchool(long schoolId, int year) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(QnrCooperateWayMerit.class);
			criteria.add(Restrictions.eq("school.id", schoolId));
			criteria.add(Restrictions.eq("year", year));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			
			return (QnrCooperateWayMerit) criteria.uniqueResult();			
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}

