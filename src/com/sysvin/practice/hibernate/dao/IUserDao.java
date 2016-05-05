package com.sysvin.practice.hibernate.dao;

import java.util.List;

import com.sysvin.practice.hibernate.entity.User;

public interface IUserDao {
	public User get(String userUuid);
	public void create(User user);
	public void delete(User user);
	public void delete(String userUuid);
	public void realDelete(User user);
	public void realDelete(String userUuid);
	public void update(User user);

	public boolean isUserIdExist(String userId);
	public List<User> searchByPhone(String phone);
	public long userCount();
}
