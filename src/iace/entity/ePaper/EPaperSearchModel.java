package iace.entity.ePaper;

import iace.entity.BaseSearchModel;

public class EPaperSearchModel extends BaseSearchModel {

	private String searchText;
	private Integer year;
	private Boolean publishState;

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

	public Boolean getPublishState() {
		return publishState;
	}

	public void setPublishState(Boolean publishState) {
		this.publishState = publishState;
	}
}
