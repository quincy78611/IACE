package iace.entity.questionnaire;

import java.util.ArrayList;
import java.util.List;

public class QnrSearchConditionSet {

	private int pageIndex;
	private int pageSize;
	private QnrTable template;
	private List<QnrSearchCondition> conditions = new ArrayList<QnrSearchCondition>();

	public String getSqlString() {
		if (this.conditions.size() > 0) {
			String sql = this.conditions.get(0).getSqlString();
			for (int i=1; i< this.conditions.size(); i++) {
				QnrSearchCondition e = this.conditions.get(i);
				sql = String.format("(%s %s %s)", 
						sql, e.getOperator(), e.getSqlString());
			}
			return " WHERE " + sql;
		}
		
		return "";
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

	public QnrTable getTemplate() {
		return template;
	}

	public void setTemplate(QnrTable template) {
		this.template = template;
	}

	public List<QnrSearchCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<QnrSearchCondition> conditions) {
		this.conditions = conditions;
	}

	public QnrSearchCondition getCondition(int i) {
		return this.conditions.get(i);
	}
	
	public void addCondition(QnrSearchCondition c) {
		this.conditions.add(c);
	}
}

