package iace.dao.sys;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.sys.SysUser;

public class SysUserDao extends BaseIaceDao<SysUser> implements ISysUserDao {

	public SysUserDao() {
		super(SysUser.class);
	}

	@Override
	public SysUser get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysUser.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			SysUser user = (SysUser) criteria.uniqueResult();
			Hibernate.initialize(user);
			Hibernate.initialize(user.getSysRole());
			
			return user;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}	
	}
	
	

	@Override
	public SysUser getBy(String account, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysUser.class);
			criteria.add(Restrictions.eq("account", account));
			criteria.add(Restrictions.eq("password", password));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			SysUser user = (SysUser) criteria.uniqueResult();
			if (user != null) {
				Hibernate.initialize(user);
				Hibernate.initialize(user.getSysRole());
			}
			
			return user;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}	
	}

	@Override
	public boolean isExist(String account, String password) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysUser.class);
			criteria.add(Restrictions.eq("account", account));
			criteria.add(Restrictions.eq("password", password));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			criteria.setProjection(Projections.rowCount());
			Long count = (Long) criteria.uniqueResult();
			return count == 1;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}	
	}
	
	

}
