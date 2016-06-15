package iace.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import core.util.PagedList;
import iace.entity.questionnaire.QnrSearchCondition;
import iace.entity.questionnaire.QnrSearchConditionSet;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;
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
	
	public void validateIndex() {
		try{
			findQnrTemplate();
			this.serachConditionSet.setTemplate(this.qnrTemplate);
			for (int i=0;i<this.serachConditionSet.getConditions().size();i++) {
				QnrSearchCondition c = this.serachConditionSet.getConditions().get(i);
				QnrTableColumn qtc = this.serachConditionSet.getTemplate().getQuestionByColName(c.getTableColumnName());
				c.setTableColumn(qtc);
				String fieldName = "serachConditionSet.conditions["+i+"].searchValue";
				
				// cast data
				try {
					String data = this.qnrService.mergeStringArrayToString((String[]) c.getSearchValue());
					if (StringUtils.isBlank(data)) {
						c.setSearchValue(null);
					} else {
						Class<?> dataType = c.getTableColumn().getJavaType();
						c.setSearchValue(this.qnrService.castDataToAppropriateType(data, dataType));				
					}
				} catch (Exception e) {
					log.warn(qtc.getColName() + " 格式錯誤! ", e);
					super.addFieldError(fieldName, "格式錯誤");
					continue;
				}
				//validate
				Object data = c.getSearchValue();
				if (data != null) {
					if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_TEXT)) {
						if (qtc.getLength() > 0) {
							super.validateTextMaxLength((String) data, qtc.getLength(), fieldName);
						}
					} else if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_NUM)) {
						super.validateNumberRange(data, qtc.getPrecision(), qtc.getScale(), fieldName);
					}
				}
				
			}
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
		}		
	}
	
	public String index() {
		try {
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
		try{
			findQnrTemplate();
			for (int i=0; i<this.qnrTemplate.getQuestionNum(); i++) {
				QnrTableColumn qtc = this.qnrTemplate.getQuestionList().get(i);
				if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_HIDDEN)) {
					continue;
				}
				
				String fieldName = "datas['"+qtc.getColName()+"']";
				// cast data
				try {
					String[] strs = (String[]) this.datas.get(qtc.getColName());
					Class<?> dataType = qtc.getJavaType();
					String str = this.qnrService.mergeStringArrayToString(strs);
					Object data = this.qnrService.castDataToAppropriateType(str, dataType);
					this.datas.put(qtc.getColName(), data);
				} catch (Exception e) {
					log.warn(qtc.getColName()+" 格式錯誤! " + e.getMessage());
					super.addFieldError(fieldName, "格式錯誤");
					continue;
				}
				//validate
				Object data = this.datas.get(qtc.getColName());
				if (data == null) {
					if (qtc.getNullable() == false) {
						super.addFieldError(fieldName, "不可為空");
					}
				} else {
					if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_TEXT)) {
						if (qtc.getLength() > 0) {
							super.validateTextMaxLength((String) data, qtc.getLength(), fieldName);
						}
					} else if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_NUM)) {
						super.validateNumberRange(data, qtc.getPrecision(), qtc.getScale(), fieldName);
					}
				}
			}			
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
		}
	}
	
	public String createSubmit() {
		try {
			this.qnrService.create(this.qnrTemplate, this.datas);
			this.datas = this.qnrService.get(this.qnrTemplate, (long)this.datas.get("ID"));
			this.addActionMessage("CREATE SUCCESS!");
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
