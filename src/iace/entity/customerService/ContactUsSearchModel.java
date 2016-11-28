package iace.entity.customerService;

import iace.entity.BaseSearchModel;

public class ContactUsSearchModel extends BaseSearchModel {

	private String searchText;
	private String beenHandled; //不將型態設為Boolean是因為從頁面上傳來空白或是其他struts無法解析成Boolean型態的值時(包括null)，就會強制將此欄位設為false，如果想要用Boolean就必須要另外寫Converter

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getBeenHandled() {
		return beenHandled;
	}

	public void setBeenHandled(String beenHandled) {
		this.beenHandled = beenHandled;
	}

}
