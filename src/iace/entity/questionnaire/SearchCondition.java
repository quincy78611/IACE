package iace.entity.questionnaire;

public class SearchCondition {

	public static final String OR = "OR";
	public static final String AND = "AND";	

	public static final String SEARCH_TYPE_EQ = "EQUAL";
	public static final String SEARCH_TYPE_NEQ = "NOT_EQUAL";
	public static final String SEARCH_TYPE_SM = "SMALLER_THAN";
	public static final String SEARCH_TYPE_LG = "LARGER_THAN";
	public static final String SEARCH_TYPE_CONTAIN = "STRING_CONTAIN";
	public static final String SEARCH_TYPE_START = "STRING_START_WITH";
	public static final String SEARCH_TYPE_END = "STRING_END_WITH";
	
	
	private String operator = AND;
	private QnrTableColumn tableColumn;
	private Object searchValue;
	private String searchType;

	public String getSqlString() {		
		switch (this.searchType) {
		case SEARCH_TYPE_EQ: {
			return "\"" + this.tableColumn.getColName() + "\" = ?";
		}
		case SEARCH_TYPE_NEQ: {
			return "\"" + this.tableColumn.getColName() + "\" != ?";
		}
		case SEARCH_TYPE_SM: {
			return "\"" + this.tableColumn.getColName() + "\" < ?";
		}
		case SEARCH_TYPE_LG: {
			return "\"" + this.tableColumn.getColName() + "\" > ?";
		}
		case SEARCH_TYPE_CONTAIN: {
			return "\"" + this.tableColumn.getColName() + "\" like ?";
		}
		case SEARCH_TYPE_START: {
			return "\"" + this.tableColumn.getColName() + "\" like ?";
		}
		case SEARCH_TYPE_END: {
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
		this.searchType = SEARCH_TYPE_EQ;
	}
	
	public void setSearchTypeToNEQ() {
		this.searchType = SEARCH_TYPE_NEQ;
	}
	
	public void setSearchTypeToSM() {
		this.searchType = SEARCH_TYPE_SM;
	}

	public void setSearchTypeToLG() {
		this.searchType = SEARCH_TYPE_LG;
	}
	
	public void setSearchTypeToContain() {
		this.searchType = SEARCH_TYPE_CONTAIN;
	}
	
	public void setSearchTypeToStartWith() {
		this.searchType = SEARCH_TYPE_START;
	}
	
	public void setSearchTypeToEndWith() {
		this.searchType = SEARCH_TYPE_END;
	}

}
