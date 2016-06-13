package iace.entity.option;

import java.util.ArrayList;
import java.util.List;

public class OptionQnrSearchType {
	
	public static final String SEARCH_TYPE_EQ = "EQUAL";
	public static final String SEARCH_TYPE_NEQ = "NOT_EQUAL";
	public static final String SEARCH_TYPE_SM = "SMALLER_THAN";
	public static final String SEARCH_TYPE_LG = "LARGER_THAN";
	public static final String SEARCH_TYPE_CONTAIN = "STRING_CONTAIN";
	public static final String SEARCH_TYPE_START = "STRING_START_WITH";
	public static final String SEARCH_TYPE_END = "STRING_END_WITH";

	public static final OptionQnrSearchType EQ = new OptionQnrSearchType(SEARCH_TYPE_EQ, "等於");
	public static final OptionQnrSearchType NEQ = new OptionQnrSearchType(SEARCH_TYPE_NEQ, "不等於");
	public static final OptionQnrSearchType SM = new OptionQnrSearchType(SEARCH_TYPE_SM, "小於");
	public static final OptionQnrSearchType LG = new OptionQnrSearchType(SEARCH_TYPE_LG, "大於");
	public static final OptionQnrSearchType CONTAIN = new OptionQnrSearchType(SEARCH_TYPE_CONTAIN, "含有");
	public static final OptionQnrSearchType START = new OptionQnrSearchType(SEARCH_TYPE_START, "開頭為");
	public static final OptionQnrSearchType END = new OptionQnrSearchType(SEARCH_TYPE_END, "結尾為");
	
	private String code;
	private String name;
	
	public OptionQnrSearchType() {}
	
	public OptionQnrSearchType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static List<OptionQnrSearchType> listAll() {
		List<OptionQnrSearchType> list = new ArrayList<OptionQnrSearchType>();
		list.add(EQ);
		list.add(NEQ);
		list.add(SM);
		list.add(LG);
		list.add(CONTAIN);
		list.add(START);
		list.add(END);
		return list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
