package iace.entity.qnrCooperateWay;

import iace.entity.option.OptionSchool;

public class QnrCooperateWayLinkModel {
	private OptionSchool school;
//	private String part0To3Link;
//	private String part4Link;
	
	private String encryptSchoolId;

	public OptionSchool getSchool() {
		return school;
	}

	public void setSchool(OptionSchool school) {
		this.school = school;
	}

	public String getEncryptSchoolId() {
		return encryptSchoolId;
	}

	public void setEncryptSchoolId(String encryptSchoolId) {
		this.encryptSchoolId = encryptSchoolId;
	}

	
	
}
