package iace.action;

import java.util.List;

import core.action.BaseAction;
import core.util.PagedList;
import iace.entity.option.OptionQnrInputType;
import iace.entity.option.OptionTable;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;
import iace.service.QnrTemplateService;
import iace.service.ServiceFactory;

public class QnrTemplateAction extends BaseAction {

	private static final long serialVersionUID = -8361273818363065589L;

	private QnrTemplateService qnrTemplateService = ServiceFactory.getQnrTemplateService();
	
	private List<OptionQnrInputType> qnrInputTypes;
	private List<OptionTable> optionTables;
	
	private int pageIndex;
	private int pageSize = 10;
	private String searchQnrName;
 	private PagedList<QnrTable> qnrTableList;
 	
	private long id;
	private QnrTable qnrTable;
	
	public QnrTemplateAction() {
		super.setTitle("問卷模板管理");
	}
	
	public String init() {
		return SUCCESS;
	}
	
	public String index() {
		try {
			this.qnrTableList = this.qnrTemplateService.searchBy(
					this.pageIndex, this.pageSize, this.searchQnrName);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}		
	}
	
	public String showDetail() {
		try {
			this.qnrTable = this.qnrTemplateService.get(this.id);
			if (this.qnrTable == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		validateBeforeInsertSubmit();
	}

	public String createSubmit() {
		try {
			this.qnrTemplateService.create(this.qnrTable);
			super.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}		
	}
	
	public String copy() {
		try {
			this.qnrTable = this.qnrTemplateService.get(this.id);
			if (this.qnrTable == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public void validateCopySubmit() {
		validateBeforeInsertSubmit();
	}
	
	public String copySubmit() {
		try {
			this.qnrTemplateService.create(this.qnrTable);
			super.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.qnrTable = this.qnrTemplateService.get(this.id);
			if (this.qnrTable == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public void validateUpdateSubmit() {
		//TODO
	}
	
	public String updateSubmit() {
		try {
			this.qnrTemplateService.update(this.qnrTable);			
			this.addActionMessage("UPDATE SUCCESS!");
			return SUCCESS;			
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String delete() {
		try {
			this.qnrTable = this.qnrTemplateService.get(this.id);
			if (this.qnrTable == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteSubmit() {
		try {
			this.qnrTable = this.qnrTemplateService.get(this.id);
			this.qnrTemplateService.delete(this.id);
			this.addActionMessage("DELETE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	private void validateBeforeInsertSubmit() {
		//TODO
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

	public PagedList<QnrTable> getQnrTableList() {
		return qnrTableList;
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

	public String getSearchQnrName() {
		return searchQnrName;
	}

	public void setSearchQnrName(String searchQnrName) {
		this.searchQnrName = searchQnrName;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
	
	
}
