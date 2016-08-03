package iace.entity.consulting;

import java.util.Date;

import iace.entity.BaseSearchModel;

public class ConsultingSearchModel extends BaseSearchModel {

	private String searchText;
	private String optionOrganizationTypeCode;
	private String optionConsultCode;
	private String optionIndustryCode;
	private Date consultDateStart;
	private Date consultDateEnd;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getOptionOrganizationTypeCode() {
		return optionOrganizationTypeCode;
	}

	public void setOptionOrganizationTypeCode(String optionOrganizationTypeCode) {
		this.optionOrganizationTypeCode = optionOrganizationTypeCode;
	}

	public String getOptionConsultCode() {
		return optionConsultCode;
	}

	public void setOptionConsultCode(String optionConsultCode) {
		this.optionConsultCode = optionConsultCode;
	}

	public String getOptionIndustryCode() {
		return optionIndustryCode;
	}

	public void setOptionIndustryCode(String optionIndustryCode) {
		this.optionIndustryCode = optionIndustryCode;
	}

	public Date getConsultDateStart() {
		return consultDateStart;
	}

	public void setConsultDateStart(Date consultDateStart) {
		this.consultDateStart = consultDateStart;
	}

	public Date getConsultDateEnd() {
		return consultDateEnd;
	}

	public void setConsultDateEnd(Date consultDateEnd) {
		this.consultDateEnd = consultDateEnd;
	}

}
