package iace.entity.enterpriseNeed;

import iace.entity.BaseSearchModel;

public class EnterpriseRequireTechSearchModel extends BaseSearchModel {

	private String searchText;
	private Long optionDomainId;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Long getOptionDomainId() {
		return optionDomainId;
	}

	public void setOptionDomainId(Long optionDomainId) {
		this.optionDomainId = optionDomainId;
	}

}
