package iace.entity.qnrCooperateWay;

import iace.entity.option.School;

public class QnrCooperateWayLinkModel {
	private School school;
//	private String part0To3Link;
//	private String part4Link;
	
	private String encryptSchoolId;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getEncryptSchoolId() {
		return encryptSchoolId;
	}

	public void setEncryptSchoolId(String encryptSchoolId) {
		this.encryptSchoolId = encryptSchoolId;
	}

	
	
}
