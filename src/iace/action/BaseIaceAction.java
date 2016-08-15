package iace.action;

import java.util.List;

import core.action.BaseAction;
import iace.entity.questionnaire.QnrTable;
import iace.service.ServiceFactory;

public class BaseIaceAction extends BaseAction {

	private static final long serialVersionUID = 1771742807180192593L;
	
	private String title;

	// =========================================================================

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<QnrTable> getQnrTemplateList() {
		return ServiceFactory.getQnrTemplateService().listAll();
	}
	
	/**
	 * 測試文字是否可以轉換為double (testValue == null 會通過, 但空白不會)
	 * @param testValue
	 * @param fieldName
	 * @param errMsg
	 * @return
	 */
	protected boolean validateStringAsDouble(String testValue, String fieldName, String errMsg) {
		try {
			Double.parseDouble(testValue);
			return true;
		} catch (NullPointerException | NumberFormatException e) {
			if (testValue != null && testValue.trim().equals("N/A") == false) {
				this.addFieldError(fieldName, errMsg);
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	/**
	 * 測試文字是否可以轉換為double (testValue == null 會通過, 但空白不會)
	 * @param testValue
	 * @param fieldName
	 * @param errMsg 錯誤訊息預設為 "Not a number!"
	 * @return
	 */
	protected boolean validateStringAsDouble(String testValue, String fieldName) {
		return validateStringAsDouble(testValue, fieldName, "Not a number!");
	}

}
