package iace.action;

import java.util.List;

import core.action.BaseAction;
import iace.entity.option.OptionQnrDataType;
import iace.entity.option.OptionQnrInputType;
import iace.entity.option.OptionTable;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;
import iace.service.QnrTemplateService;
import iace.service.ServiceFactory;

public class QnrTemplateAction extends BaseAction {

	private static final long serialVersionUID = -8361273818363065589L;

	private QnrTemplateService qnrTemplateService = ServiceFactory.getQnrTemplateService();
	
	private List<OptionQnrDataType> qnrDataTypes;
	private List<OptionQnrInputType> qnrInputTypes;
	private List<OptionTable> optionTables;
 	private List<QnrTable> qnrTableList;
 	
	private long id;
	private QnrTable qnrTable;
	
	public QnrTemplateAction() {
		super.setTitle("問卷模板管理");
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		//TODO
	}

	public String createSubmit() {
		try {
			this.qnrTemplateService.create(this.qnrTable);
			this.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}		
	}	
	
	//==========================================================================

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public QnrTable getQnrTable() {
		return qnrTable;
	}

	public void setQnrTable(QnrTable qnrTable) {
		this.qnrTable = qnrTable;
	}

	public List<QnrTable> getQnrTableList() {
		return qnrTableList;
	}

	public List<OptionQnrDataType> getQnrDataTypes() {
		if (qnrDataTypes == null) {
			qnrDataTypes = QnrTableColumn.getQnrDataTypes();
		}		
		return qnrDataTypes;
	}

	public List<OptionQnrInputType> getQnrInputTypes() {
		if (qnrInputTypes == null) {
			qnrInputTypes = QnrTableColumn.getQnrInputTypes();
		}		
		return qnrInputTypes;
	}

	public List<OptionTable> getOptionTables() {
		if (optionTables == null) {
			optionTables = OptionTable.listAll();
		}		
		return optionTables;
	}

	
	
	
}
