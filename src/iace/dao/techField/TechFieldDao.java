package iace.dao.techField;

import org.hibernate.Query;
import org.hibernate.Session;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.patent.TechField;

public class TechFieldDao extends BaseIaceDao<TechField> implements ITechFieldDao {
	public TechFieldDao() {
		super(TechField.class);
	}

	@Override
	public boolean isNameExist(String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT count(*) "
					+ "FROM " + TechField.class.getSimpleName() +" o "
					+ "WHERE o.name = :name AND o.isValid = :isValid";
			Query query = session.createQuery(hql);
			query.setString("name", name);
			query.setString("isValid", BaseEntity.TRUE);
			Object obj = query.uniqueResult();
			return (long)obj >= 1;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public TechField getByName(String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "FROM " + TechField.class.getSimpleName() +" o "
					+ "WHERE o.name = :name AND o.isValid = :isValid";
			Query query = session.createQuery(hql);
			query.setString("name", name);
			query.setString("isValid", BaseEntity.TRUE);
			Object obj = query.uniqueResult();
			return (TechField)obj;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	
}
