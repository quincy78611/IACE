package iace.action;

import java.util.Map;

import core.util.PagedList;
import iace.entity.questionnaire.QnrSearchConditionSet;
import iace.entity.questionnaire.QnrTable;
import iace.service.QnrService;
import iace.service.QnrTemplateService;
import iace.service.ServiceFactory;

public class QnrAction extends BaseIaceAction {

	private static final long serialVersionUID = 5982636869223414124L;
	
	private QnrTemplateService qnrTemplateService = ServiceFactory.getQnrTemplateService();
	private QnrService qnrService = ServiceFactory.getQnrService();
	
	private long id;
	private long qnrTableId;
	private String qnrTableName;
	private QnrTable qnrTemplate;
	
	private Map<String, Object> datas;
	
	private QnrSearchConditionSet serachConditionSet;
	private PagedList<Map<String, Object>> searchResult;
	
	public String init() {
		findQnrTemplate();
		return SUCCESS;
	}
	
	public String index() {
		try {
			findQnrTemplate();
			this.serachConditionSet.setTemplate(this.qnrTemplate);
			this.searchResult = this.qnrService.search(this.serachConditionSet);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}		
	}
	
	public String showDetail() {
		try{
			findQnrTemplate();
			this.datas = this.qnrService.get(this.qnrTemplate, this.id);
			if (this.datas == null || this.datas.size() == 0) {
				throw new IllegalArgumentException("找不到資料!");
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String create() {
		try {
			findQnrTemplate();
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}		
	}
		
	public void validateCreateSubmit() {
		//TODO
	}
	
	public String createSubmit() {
		try {
			findQnrTemplate();
			this.qnrService.create(this.qnrTemplate, this.datas);
			this.datas = this.qnrService.get(this.qnrTemplate, (long)this.datas.get("ID"));
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	private void findQnrTemplate() {
		this.qnrTemplate = this.qnrTemplateService.get(this.qnrTableId);
		if (this.qnrTemplate == null) {
			throw new IllegalArgumentException("找不到問卷!");
		} else {
			super.setTitle(this.qnrTemplate.getName());
		}
	}

	// =========================================================================
	
	public long getQnrTableId() {
		return qnrTableId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setQnrTableId(long qnrTableId) {
		this.qnrTableId = qnrTableId;
	}

	public String getQnrTableName() {
		return qnrTableName;
	}

	public void setQnrTableName(String qnrTableName) {
		this.qnrTableName = qnrTableName;
	}

	public QnrTable getQnrTemplate() {
		return qnrTemplate;
	}

	public void setQnrTemplate(QnrTable qnrTemplate) {
		this.qnrTemplate = qnrTemplate;
	}

	public Map<String, Object> getDatas() {
		return datas;
	}

	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}

	public QnrSearchConditionSet getSerachConditionSet() {
		return serachConditionSet;
	}

	public void setSerachConditionSet(QnrSearchConditionSet serachConditionSet) {
		this.serachConditionSet = serachConditionSet;
	}

	public PagedList<Map<String, Object>> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(PagedList<Map<String, Object>> searchResult) {
		this.searchResult = searchResult;
	}

	public Object searchResult(int index, String colName) {
		return this.searchResult.getList().get(index).get(colName);
	}
	
}
