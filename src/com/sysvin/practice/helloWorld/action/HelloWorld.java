/**
 * 
 */
package com.sysvin.practice.helloWorld.action;

import com.sysvin.practice.helloWorld.model.MessageStore;

import core.action.BaseAction;

/**
 * @author Eric Liao
 *
 */
public class HelloWorld extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4674656902129407888L;	

	private String loginName;
    private String password;
    
    private MessageStore messageStore;
	
	@Override
	public String execute() {
		log.debug("execute() ======================================================"); 
		log.info(toString());
		messageStore = new MessageStore();
		return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
		/*if (isInvalid(getLoginName()))
            this.addFieldError("loginName", "Login Name was required");*/
	}
	
	@SuppressWarnings("unused")
	private boolean isInvalid(String str){
        return ( str==null || str.length()==0 );
    }

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public MessageStore getMessageStore() {
		return messageStore;
	}

	public void setMessageStore(MessageStore messageStore) {
		this.messageStore = messageStore;
	}

	@Override
	public String toString() {
		return "MyAction [loginName=" + loginName + ", password=" + password + "]";
	}
	

	
}
