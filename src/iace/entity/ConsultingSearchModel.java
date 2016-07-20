package iace.entity;

import java.util.Date;

public class ConsultingSearchModel {

	private String searchText;
	private String optionOrganizationTypeCode;
	private String optionConsultCode;
	private String optionIndustryCode;
	private Date consultDateStart;
	private Date consultDateEnd;

	private int pageIndex;
	private int pageSize;

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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
