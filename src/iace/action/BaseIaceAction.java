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

}
