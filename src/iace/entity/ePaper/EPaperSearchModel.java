package iace.entity.ePaper;

import iace.entity.BaseSearchModel;

public class EPaperSearchModel extends BaseSearchModel {

	private String searchText;
	private Integer year;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
