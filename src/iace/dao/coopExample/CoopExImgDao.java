package iace.dao.coopExample;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.coopExample.CoopExImg;

public class CoopExImgDao extends BaseIaceDao<CoopExImg> implements ICoopExImgDao {

	public CoopExImgDao() {
		super(CoopExImg.class);
	}

	@Override
	public CoopExImg get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopExImg.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			Object entity = criteria.uniqueResult();
			
			return (CoopExImg) entity;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
