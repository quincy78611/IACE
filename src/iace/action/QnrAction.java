package iace.action;

import java.util.Map;

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
		try {
			findQnrTemplate();
			this.serachConditionSet.setTemplate(this.qnrTemplate);
			for (int i = 0; i < this.serachConditionSet.getConditions().size(); i++) {
				QnrSearchCondition c = this.serachConditionSet.getConditions().get(i);
				QnrTableColumn qtc = this.serachConditionSet.getTemplate().getQuestionByColName(c.getTableColumnName());
				c.setTableColumn(qtc);
				String fieldName = "serachConditionSet.conditions[" + i + "].searchValue";
				String[] strs = (String[]) c.getSearchValue();
				Object data = castDataAndValidate(qtc, fieldName, strs);
				c.setSearchValue(data);
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
		try {
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
		try {
			findQnrTemplate();
			for (int i = 0; i < this.qnrTemplate.getQuestionNum(); i++) {
				QnrTableColumn qtc = this.qnrTemplate.getQuestionList().get(i);
				if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_HIDDEN)) {
					continue;
				}
				String fieldName = "datas['" + qtc.getColName() + "']";
				String[] strs = (String[]) this.datas.get(qtc.getColName());
				Object data = castDataAndValidate(qtc, fieldName, strs);
				this.datas.put(qtc.getColName(), data);

				if (qtc.getNullable() == false) {
					if (data instanceof String) {
						super.validateNotBlank((String) data, fieldName);
					} else {
						super.validateNotNull(data, fieldName);
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
			this.datas = this.qnrService.get(this.qnrTemplate, (long) this.datas.get("ID"));
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

	private Object castDataAndValidate(QnrTableColumn qtc, String fieldName, String[] strs) {
		Object data = null;
		// cast data
		try {
			Class<?> dataType = qtc.getJavaType();
			String str = this.qnrService.mergeStringArrayToString(strs);
			data = this.qnrService.castDataToAppropriateType(str, dataType);
		} catch (NumberFormatException e) {
			log.debug(qtc.getColName() + " 格式錯誤或長度超過限制! ", e);
			super.addFieldError(fieldName, "格式錯誤或長度超過限制");
			return data;
		} catch (Exception e) {
			log.debug(qtc.getColName() + " 格式錯誤! ", e);
			super.addFieldError(fieldName, "格式錯誤");
			return data;
		}

		// validate
		if (data != null) {
			if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_TEXT)) {
				if (qtc.getLength() > 0) {
					super.validateTextMaxLength((String) data, qtc.getLength(), fieldName);
				}
			} else if (qtc.getInputType().equals(QnrTableColumn.INPUT_TYPE_TEXTFIELD_NUM)) {
				super.validateNumberRange(data, qtc.getPrecision(), qtc.getScale(), fieldName);
			}
		}
		return data;
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
