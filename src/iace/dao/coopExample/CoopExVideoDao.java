package iace.dao.coopExample;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.coopExample.CoopExVideo;

public class CoopExVideoDao extends BaseIaceDao<CoopExVideo> implements ICoopExVideoDao {

	public CoopExVideoDao() {
		super(CoopExVideo.class);
	}

	@Override
	public CoopExVideo get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopExVideo.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			Object entity = criteria.uniqueResult();
			
			return (CoopExVideo) entity;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	
}
