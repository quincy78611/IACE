package com.sysvin.practice.dbAccess.action;

import com.sysvin.practice.dbAccess.model.User;
import com.sysvin.practice.dbAccess.service.UserDbService;

import core.action.BaseAction;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2005935888186641986L;

	private String email;
	private String password;
	
	public String login() {
		return SUCCESS;
 	}

	public void validateLogin() {
		UserDbService service = new UserDbService();
		User user = service.hasUser(this.getEmail(), this.getPassword());
		if (user == null) {
			this.addFieldError("email", "email�αK�X���~!");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}






	
	
	
	
	
	
}
