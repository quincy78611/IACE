package iace.entity.sys;



import java.sql.Timestamp;
import java.text.ParseException;

import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

import iace.entity.BaseSearchModel;

public class SysLogSearchModel extends BaseSearchModel {

	private Long optionSysNamespaceId;
	private Long optionSysActionId;
	private Long sysUserId;
	private Timestamp timeS;
	private Timestamp timeE;


	public Long getOptionSysNamespaceId() {
		return optionSysNamespaceId;
	}

	public void setOptionSysNamespaceId(Long optionSysNamespaceId) {
		this.optionSysNamespaceId = optionSysNamespaceId;
	}

	public Long getOptionSysActionId() {
		return optionSysActionId;
	}

	public void setOptionSysActionId(Long optionSysActionId) {
		this.optionSysActionId = optionSysActionId;
	}

	public Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	@TypeConversion(converter="iace.converter.StringToTimestampConverter")
	public Timestamp getTimeS() {
		return timeS;
	}

	public void setTimeS(Timestamp timeS) throws ParseException {
		this.timeS = timeS;
	}

	@TypeConversion(converter="iace.converter.StringToTimestampConverter")
	public Timestamp getTimeE() {
		return timeE;
	}

	public void setTimeE(Timestamp timeE) throws ParseException {
		this.timeE =  timeE;
	}



	
}
