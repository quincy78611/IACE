package core.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715574015720566257L;

	protected static Logger log = LogManager.getLogger(BaseAction.class);
	


	protected boolean validateNotNull(Object testValue, String fieldName, String errMsg) {
		if (testValue == null) {
			this.addFieldError(fieldName, errMsg);
			return false;
		}
		return true;
	}
	
	protected boolean validateNotNull(Object testValue, String fieldName) {
		return validateNotNull(testValue, fieldName, "不可為空");
	}
	
	protected boolean validateNotBlank(CharSequence testValue, String fieldName, String errMsg) {
		if (StringUtils.isBlank(testValue)) {
			this.addFieldError(fieldName, errMsg);
			return false;
		}
		return true;
	}

	protected boolean validateNotBlank(CharSequence testValue, String fieldName) {
		return validateNotBlank(testValue, fieldName, "不可為空白");
	}

	protected boolean validateTextMinLength(CharSequence testValue, int len, String fieldName, String errMsg) {
		if (StringUtils.length(testValue) < len) {
			this.addFieldError(fieldName, errMsg);
			return false;
		}
		return true;
	}

	protected boolean validateTextMinLength(CharSequence testValue, int len, String fieldName) {
		return validateTextMinLength(testValue, len, fieldName, "長度最少要" + len);
	}
	
	protected boolean validateTextMaxLength(CharSequence testValue, int maxLen, String fieldName, String errMsg) {
		if (StringUtils.length(testValue) > maxLen) {
			this.addFieldError(fieldName, errMsg);
			return false;
		}
		return true;
	}
	
	protected boolean validateTextMaxLength(CharSequence testValue, int maxLen, String fieldName) {
		return validateTextMaxLength(testValue, maxLen, fieldName, "長度不可超過" + maxLen);
	}
	
	protected boolean validateTextLength(CharSequence testValue, int minLen, int maxLen, String fieldName, String errMsg) {
		if (StringUtils.length(testValue) < minLen || StringUtils.length(testValue) > maxLen) {
			this.addFieldError(fieldName, errMsg);
			return false;
		}
		return true;
	}
	
	protected boolean validateTextLength(CharSequence testValue, int minLen, int maxLen, String fieldName) {
		String errMsg= "長度必須介於"+minLen+"~"+maxLen;
		return validateTextLength(testValue, minLen, maxLen, fieldName, errMsg);
	}

	protected boolean validateNotBlankNLength(CharSequence testValue, int maxLen, String fieldName) {
		if (validateNotBlank(testValue, fieldName)) {
			return validateTextMaxLength(testValue, maxLen, fieldName);
		}
		return false;
	}
	
	protected boolean validateEmail(CharSequence testValue, String fieldName, String errMsg) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(testValue);
		boolean isValid = m.matches();
		if (isValid == false) {
			this.addFieldError(fieldName, errMsg);
		}
		return isValid;
	}

	protected boolean validateEmail(CharSequence testValue, String fieldName) {
		return validateEmail(testValue, fieldName, "必須是email格式");
	}
	

	
	
}
