package com.sysvin.practice.xmlValidation.action;

import core.action.BaseAction;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4911625604888219642L;

	private String email;
    private String password;
 
    public String execute() {
        if (email != null && email.equals("admin@codejava.net")) {
            return SUCCESS;        
        } else {
            return INPUT;
        }
    }
    
    public String doLogin() {
        if (email != null && email.equals("eric855104@gmail.com")) {
            return SUCCESS;        
        } else {
            return INPUT;
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
