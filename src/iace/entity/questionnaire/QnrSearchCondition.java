package iace.entity.questionnaire;

import iace.entity.option.OptionQnrSearchType;

public class QnrSearchCondition {

	public static final String OR = "OR";
	public static final String AND = "AND";	

	private String operator = AND;
	private String tableColumnName;
	private QnrTableColumn tableColumn;
	private Object searchValue;
	private String searchType;

	public String getSqlString() {
		if (this.searchValue == null) {
			return "1=1";
		}			
		switch (this.searchType) {
		case OptionQnrSearchType.SEARCH_TYPE_EQ: {
			return "\"" + this.tableColumn.getColName() + "\" = ?";
		}
		case OptionQnrSearchType.SEARCH_TYPE_NEQ: {
			return "\"" + this.tableColumn.getColName() + "\" != ?";
		}
		case OptionQnrSearchType.SEARCH_TYPE_SM: {
			return "\"" + this.tableColumn.getColName() + "\" < ?";
		}
		case OptionQnrSearchType.SEARCH_TYPE_LG: {
			return "\"" + this.tableColumn.getColName() + "\" > ?";
		}
		case OptionQnrSearchType.SEARCH_TYPE_CONTAIN: {
			return "\"" + this.tableColumn.getColName() + "\" like ?";
		}
		case OptionQnrSearchType.SEARCH_TYPE_START: {
			return "\"" + this.tableColumn.getColName() + "\" like ?";
		}
		case OptionQnrSearchType.SEARCH_TYPE_END: {
			return "\"" + this.tableColumn.getColName() + "\" like ?";
		}
		default:
			throw new IllegalArgumentException("Unsupporrted type");
		}
	}
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getTableColumnName() {
		return tableColumnName;
	}

	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}

	public QnrTableColumn getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(QnrTableColumn tableColumn) {
		this.tableColumn = tableColumn;
	}

	public Object getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(Object searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		// TODO check type
		this.searchType = searchType;
	}
	
	public void setSearchTypeToEQ() {
		this.searchType = OptionQnrSearchType.SEARCH_TYPE_EQ;
	}
	
	public void setSearchTypeToNEQ() {
		this.searchType = OptionQnrSearchType.SEARCH_TYPE_NEQ;
	}
	
	public void setSearchTypeToSM() {
		this.searchType = OptionQnrSearchType.SEARCH_TYPE_SM;
	}

	public void setSearchTypeToLG() {
		this.searchType = OptionQnrSearchType.SEARCH_TYPE_LG;
	}
	
	public void setSearchTypeToContain() {
		this.searchType = OptionQnrSearchType.SEARCH_TYPE_CONTAIN;
	}
	
	public void setSearchTypeToStartWith() {
		this.searchType = OptionQnrSearchType.SEARCH_TYPE_START;
	}
	
	public void setSearchTypeToEndWith() {
		this.searchType = OptionQnrSearchType.SEARCH_TYPE_END;
	}

}
