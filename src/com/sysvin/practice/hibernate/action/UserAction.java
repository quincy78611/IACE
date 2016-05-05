package com.sysvin.practice.hibernate.action;

import java.util.List;

import com.sysvin.practice.hibernate.entity.User;
import com.sysvin.practice.hibernate.service.ServiceFactory;
import com.sysvin.practice.hibernate.service.UserService;

import core.action.BaseAction;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = 450096809077892850L;

	private UserService userService = ServiceFactory.getUserService();
	private User user;
	private List<User> userList;
	
	public String registerInput() {
		return INPUT;
	}
	
	public void validateRegister() {
		try {
			if (super.validateNotBlank(user.getUserId(), "user.userId")) {
				boolean isUserIdExist = userService.isUserIdExist(user.getUserId());
				if (isUserIdExist) {
					this.addFieldError("user.userId", "使用者代號已存在");
				}
			}
			if (super.validateNotBlank(user.getPassword(), "user.password")) {
				super.validateTextLength(user.getPassword(), 8, 25, "user.password");
			}
			if (super.validateNotBlank(user.getName(), "user.name")) {
				super.validateTextLength(user.getName(), 2, 100, "user.name");
			}
			super.validateEmail(user.getEmail(), "user.email");
			

		} catch (Exception e) {
			log.warn("", e);
			this.addActionError(e.getMessage());
		}
	}
	
	public String register() {
		try {
			userService.create(user);
			return SUCCESS;
		} catch (Exception e) {
			log.warn("", e);
			this.addActionError(e.getMessage());
		}	
		return INPUT;
	}
	
	public String findUserByPhoneInput() {
		return INPUT;
	}
	
	public String findUserByPhone() {
		try {
			userList = userService.searchByPhone(user.getPhone());
			if (userList != null) {
				return SUCCESS;
			}
		} catch (Exception e) {
			log.warn("", e);
			this.addActionError(e.getMessage());
		}
		return INPUT;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
	

}
