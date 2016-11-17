package iace.entity.faq;

import iace.entity.BaseSearchModel;

public class FaqSearchModel extends BaseSearchModel {

	private String searchText;
	private String category;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
