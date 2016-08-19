package iace.dao.coopExample;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.coopExample.CoopExAttachFile;

public class CoopExAttachFileDao extends BaseIaceDao<CoopExAttachFile> implements ICoopExAttachFileDao {

	public CoopExAttachFileDao() {
		super(CoopExAttachFile.class);
	}

	@Override
	public CoopExAttachFile get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopExAttachFile.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			Object entity = criteria.uniqueResult();
			
			return (CoopExAttachFile) entity;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	
}
