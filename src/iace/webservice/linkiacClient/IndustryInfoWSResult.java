package iace.webservice.linkiacClient;

import java.util.ArrayList;
import java.util.List;

import iace.entity.industryInfo.IndustryInfo;

public class IndustryInfoWSResult {
	private boolean status;
	private int totalCount;
	private List<IndustryInfo> industryInfoList = new ArrayList<IndustryInfo>();

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<IndustryInfo> getIndustryInfoList() {
		return industryInfoList;
	}

	public void setIndustryInfoList(List<IndustryInfo> industryInfoList) {
		this.industryInfoList = industryInfoList;
	}
	
	public void addIndustryInfoToList(IndustryInfo industryInfo) {
		this.industryInfoList.add(industryInfo);
	}

}
