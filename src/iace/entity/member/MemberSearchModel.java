package iace.entity.member;

import iace.entity.BaseSearchModel;

public class MemberSearchModel extends BaseSearchModel {

	private String searchText;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}
