package iace.entity.patent;

import iace.entity.BaseSearchModel;

public class PatentSearchModel extends BaseSearchModel {

	private String name;
	private String appNo;
	private String countryCode;
	private long techFieldId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
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
