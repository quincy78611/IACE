package iace.dao.sys;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.sys.SysRole;

public class SysRoleDao extends BaseIaceDao<SysRole> implements ISysRoleDao {

	public SysRoleDao() {
		super(SysRole.class);
	}

	@Override
	public SysRole get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysRole.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			SysRole role = (SysRole) criteria.uniqueResult();
			Hibernate.initialize(role);
			
			return role;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}	
	}

	
}
