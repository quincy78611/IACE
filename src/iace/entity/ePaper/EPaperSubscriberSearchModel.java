package iace.entity.ePaper;

import org.apache.commons.lang3.StringUtils;

import iace.entity.BaseSearchModel;

public class EPaperSubscriberSearchModel extends BaseSearchModel {

	private String searchText;
	private Boolean isSubscribe;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Boolean getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public String getSubScribeState() {
		if (this.isSubscribe == null) return "";
		else return this.isSubscribe.toString();
	}
	
	public void setSubScribeState(String state) {
		if (StringUtils.isBlank(state)) {
			this.isSubscribe = null;
		} else {
			this.isSubscribe = Boolean.valueOf(state);
		}
	}
}
