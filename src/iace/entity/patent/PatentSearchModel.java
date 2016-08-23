package iace.entity.patent;

import java.sql.Date;

import iace.entity.BaseSearchModel;

public class PatentSearchModel extends BaseSearchModel {

	private String name;
	private String assignee;
	private String appliactionNo;
	private Date applicationDateS;
	private Date applicationDateE;
	private String countryCode;
	private long techFieldId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAppliactionNo() {
		return appliactionNo;
	}

	public void setAppliactionNo(String appliactionNo) {
		this.appliactionNo = appliactionNo;
	}

	public Date getApplicationDateS() {
		return applicationDateS;
	}

	public void setApplicationDateS(Date applicationDateS) {
		this.applicationDateS = applicationDateS;
	}

	public Date getApplicationDateE() {
		return applicationDateE;
	}

	public void setApplicationDateE(Date applicationDateE) {
		this.applicationDateE = applicationDateE;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public long getTechFieldId() {
		return techFieldId;
	}

	public void setTechFieldId(long techFieldId) {
		this.techFieldId = techFieldId;
	}

}
