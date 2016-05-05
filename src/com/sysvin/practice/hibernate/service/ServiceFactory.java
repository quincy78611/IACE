package com.sysvin.practice.hibernate.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sysvin.practice.hibernate.dao.DaoFactory;

public class ServiceFactory {
	
	protected static Logger log = LogManager.getLogger(ServiceFactory.class);
	
	private static UserService userService;
	
	public static UserService getUserService() {
		if (userService == null) {
			userService = new UserService(DaoFactory.getUserDao());
		}
		return userService;
	}

}
