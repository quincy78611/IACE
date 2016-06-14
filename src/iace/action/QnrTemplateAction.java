package iace.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.util.PagedList;
import iace.entity.option.OptionQnrInputType;
import iace.entity.option.OptionTable;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;
import iace.service.QnrService;
import iace.service.QnrTemplateService;
import iace.service.ServiceFactory;

public class QnrTemplateAction extends BaseIaceAction {

	private static final long serialVersionUID = -8361273818363065589L;

	private QnrService qnrService = ServiceFactory.getQnrService();
	private QnrTemplateService qnrTemplateService = ServiceFactory.getQnrTemplateService();
	
	private List<OptionQnrInputType> qnrInputTypes;
	private List<OptionTable> optionTables;
	
	private int pageIndex;
	private int pageSize = 10;
	private String searchQnrName;
 	private PagedList<QnrTable> qnrTableList;
 	
	private long id;
	private QnrTable qnrTable;
	
	private Map<String, Object> ajaxResult;
	
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
		this.qnrTable = new QnrTable();
		List<QnrTableColumn> questionList = new ArrayList<QnrTableColumn>();
		questionList.add(new QnrTableColumn());
		this.qnrTable.setQuestionList(questionList);
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		validateBeforeSubmit();
		if (this.qnrTemplateService.isQnrNameExist(this.qnrTable.getName())) {
			super.addFieldError("qnrTable.name", "問卷名稱已存在");
		}
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
		validateBeforeSubmit();
		if (this.qnrTemplateService.isQnrNameExist(this.qnrTable.getName())) {
			super.addFieldError("qnrTable.name", "問卷名稱已存在");
		}
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
			if (this.qnrService.isTableHasData(this.qnrTable)) {
				this.addActionError("此問卷已有資料，不可變更");
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
		validateBeforeSubmit();
		if (this.qnrTemplateService.isQnrNameExistExcept(this.qnrTable.getName(), this.qnrTable.getId())) {
			super.addFieldError("qnrTable.name", "問卷名稱已存在");
		}
	}
	
	public String updateSubmit() {
		try {
			if (this.qnrService.isTableHasData(this.qnrTable)) {
				this.addActionError("此問卷已有資料，不可變更");
				return INPUT;
			}
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
			if (this.qnrService.isTableHasData(this.qnrTable)) {
				this.addActionError("此問卷已有資料，不可刪除");
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
			if (this.qnrTable == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			if (this.qnrService.isTableHasData(this.qnrTable)) {
				this.addActionError("此問卷已有資料，不可刪除");
				return INPUT;
			}
			this.qnrTemplateService.delete(this.id);
			this.addActionMessage("DELETE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	private void validateBeforeSubmit() {
		removeEmptyQuestion();
		super.validateNotBlankNLength(this.qnrTable.getName(), 500, "qnrTable.name");		
		for (int i=0; i<this.qnrTable.getQuestionNum(); i++) {
			QnrTableColumn qtc = this.qnrTable.getQuestionList().get(i);
			super.validateNotBlankNLength(qtc.getQuestion(), 500, "qnrTable.questionList["+i+"].question");
			if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_TEXT)) {
				if (qtc.getLength() < 0) {
					super.addFieldError("qnrTable.questionList["+i+"].length", "不可為負, 0表示不限長");
				}
			} else if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_NUM)) {
				if (qtc.getPrecision() > 38) {
					super.addFieldError("qnrTable.questionList["+i+"].precision", "數字最多38位數");
				}
				if (qtc.getPrecision() <= 0) {
					super.addFieldError("qnrTable.questionList["+i+"].precision", "總位數必須大於0");
				}
				if (qtc.getPrecision() <= 0) {
					super.addFieldError("qnrTable.questionList["+i+"].scale", "小數位數不可為負");
				}
			}
		}
	}
	
	private void removeEmptyQuestion() {
		List<QnrTableColumn> questionList = new ArrayList<QnrTableColumn>();
		for (QnrTableColumn qtc : this.qnrTable.getQuestionList()) {
			if (qtc.isEmptyEntity() == false) {
				//TODO 目前還不清楚為何qnrTabl.questionList數量會和前端網頁不一致，後面會莫名出現一些全空的QnrTableColumn				
				questionList.add(qtc);
			}
		}
		this.qnrTable.setQuestionList(questionList);
	}
	
	public String isQnrHasDataAjax() {
		this.ajaxResult = new HashMap<String, Object>();
		try {
			this.qnrTable = this.qnrTemplateService.get(this.id);
			if (this.qnrTable == null) {
				this.ajaxResult.put("error", "找不到資料紀錄!");
			} else {
				boolean isQnrHasData = this.qnrService.isTableHasData(this.qnrTable);
				this.ajaxResult.put("isQnrHasData", isQnrHasData);
			}			
			
			return SUCCESS;
		}  catch (Exception e) {
			log.error("", e);
			this.ajaxResult.put("error", e.getMessage());
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

	public Map<String, Object> getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(Map<String, Object> ajaxResult) {
		this.ajaxResult = ajaxResult;
	}


	

}
