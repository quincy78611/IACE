package com.sysvin.practice.hibernate.dao;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.sysvin.practice.hibernate.entity.User;

import core.dao.BaseDao;
import core.dao.HibernateSessionFactory;





public class UserDao extends BaseDao<User> implements IUserDao {

	//not using public because only allow DaoFactory create UserDao instance 
	UserDao() {}
	
	@Override
	public User get(String userUuid) {
		return (User) super.get(User.class, userUuid);
	}

	@Override
	public void create(User user) {
		user.create();
		super.create(user);
	}
	
	@Override
	public void delete(User user) {
		user.delete();
		super.update(user);
	}	

	@Override
	public void delete(String userUuid) {
		User user = get(userUuid);
		delete(user);
	}

	@Override
	public void realDelete(User user) {
		super.delete(user);
	}
	
	@Override
	public void realDelete(String userUuid) {
		User user = get(userUuid);
		super.delete(user);
	}

	@Override
	public void update(User user) {
		user.update();
		super.update(user);
	}

	@Override
	public boolean isUserIdExist(String userId) {
		try {
			if (StringUtils.isNotBlank(userId)) {
				Session session = HibernateSessionFactory.getSession();
				//[Users] and [userId] in HQL is relative to entity not database
				String hql = "select count(*) from User u where u.userId = :condition";
				Query query = session.createQuery(hql);
				query.setString("condition", userId);
				Object obj = query.uniqueResult();
				return (long)obj == 1;			
			} else {
				throw new IllegalArgumentException("[userId] can't be null or blank");
			}	
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	
	}

	@Override
	public List<User> searchByPhone(String phone) {
		try {
			if (StringUtils.isNotBlank(phone)) {
				Session session = HibernateSessionFactory.getSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.like("phone", phone, MatchMode.ANYWHERE));
				@SuppressWarnings("unchecked")
				List<User> list = criteria.list();
				return list;
			} else {
				throw new IllegalArgumentException("[phone] can't be null or blank");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public long userCount() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "select count(*) from User u";
			Query query = session.createQuery(hql);
			Object obj = query.uniqueResult();
			return (long)obj;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}	
}
