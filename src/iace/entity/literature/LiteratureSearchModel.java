package iace.entity.literature;

import iace.entity.BaseSearchModel;

public class LiteratureSearchModel extends BaseSearchModel {

	private String category;

	private String searchText;
	private String language;
	private Long publishYear;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category.trim();
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText.trim();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language.trim();
	}

	public Long getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Long publishYear) {
		this.publishYear = publishYear;
	}

}
