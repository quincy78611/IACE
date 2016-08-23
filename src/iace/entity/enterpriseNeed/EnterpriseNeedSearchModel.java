package iace.entity.enterpriseNeed;

import java.sql.Date;

import iace.entity.BaseSearchModel;

public class EnterpriseNeedSearchModel extends BaseSearchModel {

	private String searchText;
	private Long optionCompanyLocationId;
	private String personInChargeName;
	private String intervieweeName;
	private Date interviewDateS;
	private Date interviewDateE;
	
	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Long getOptionCompanyLocationId() {
		return optionCompanyLocationId;
	}

	public void setOptionCompanyLocationId(Long optionCompanyLocationId) {
		this.optionCompanyLocationId = optionCompanyLocationId;
	}

	public String getPersonInChargeName() {
		return personInChargeName;
	}

	public void setPersonInChargeName(String personInChargeName) {
		this.personInChargeName = personInChargeName;
	}

	public String getIntervieweeName() {
		return intervieweeName;
	}

	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	public Date getInterviewDateS() {
		return interviewDateS;
	}

	public void setInterviewDateS(Date interviewDateS) {
		this.interviewDateS = interviewDateS;
	}

	public Date getInterviewDateE() {
		return interviewDateE;
	}

	public void setInterviewDateE(Date interviewDateE) {
		this.interviewDateE = interviewDateE;
	}
	
	
}
