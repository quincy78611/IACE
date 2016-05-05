package com.sysvin.practice.hibernate.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoFactory {
	protected static Logger log = LogManager.getLogger(DaoFactory.class);

	private static IUserDao userDao;
	
	public static IUserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}
}
