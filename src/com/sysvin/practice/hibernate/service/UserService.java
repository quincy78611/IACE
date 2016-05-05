package com.sysvin.practice.hibernate.service;

import java.util.List;

import com.sysvin.practice.hibernate.dao.IUserDao;
import com.sysvin.practice.hibernate.entity.User;

import core.service.BaseService;

public class UserService extends BaseService<User, String> {
	private IUserDao userDao;
	
	//not using public because only allow ServiceFactory create UserService instance
	UserService(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User get(String id) {
		return userDao.get(id);
	}

	@Override
	public void create(User user) {
		//TODO 做一些檢查		
		userDao.create(user);		
	}

	@Override
	public void delete(User user) {
		//TODO 做一些檢查
		userDao.delete(user);		
	}

	@Override
	public void update(User user) {
		//TODO 做一些檢查
		userDao.update(user);
	}
	
	public boolean isUserIdExist(String id) {
		return userDao.isUserIdExist(id);
	}
	
	public List<User> searchByPhone(String phone) {
		return userDao.searchByPhone(phone);
	}
}
