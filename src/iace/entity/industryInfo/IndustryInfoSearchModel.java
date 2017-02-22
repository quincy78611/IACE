package iace.entity.industryInfo;

import iace.entity.BaseSearchModel;

public class IndustryInfoSearchModel extends BaseSearchModel {
	private String searchText;
	private String category;
	private Integer year;
	
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}


	
}
