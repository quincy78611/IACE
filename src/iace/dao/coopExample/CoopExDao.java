package iace.dao.coopExample;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.coopExample.CoopEx;

public class CoopExDao extends BaseIaceDao<CoopEx> implements ICoopExDao {

	public CoopExDao() {
		super(CoopEx.class);
	}
	
	@Override
	public CoopEx get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopEx.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			CoopEx entity = (CoopEx) criteria.uniqueResult();
			Hibernate.initialize(entity);
			Hibernate.initialize(entity.getImgs());
			Hibernate.initialize(entity.getVideos());
			Hibernate.initialize(entity.getAttachFiles());
			
			return entity;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public List<CoopEx> listAll() {
		try {	
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(CoopEx.class);
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			@SuppressWarnings("unchecked")
			List<CoopEx> list = criteria.list();
			Hibernate.initialize(list);
			for (CoopEx coopEx : list) {
				Hibernate.initialize(coopEx);
				Hibernate.initialize(coopEx.getImgs());
			}
			
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	
}
