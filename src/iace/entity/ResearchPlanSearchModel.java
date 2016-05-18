package iace.entity;

public class ResearchPlanSearchModel {
	private int pageIndex;
	private int pageSize;
	private String planNo;
	private String planName;
	private Integer year;
	private String grbDomainCode;
	private String manager;
	private String keyword;
	private String trlCode;
	private String rndResultName;

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

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getGrbDomainCode() {
		return grbDomainCode;
	}

	public void setGrbDomainCode(String grbDomainCode) {
		this.grbDomainCode = grbDomainCode;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTrlCode() {
		return trlCode;
	}

	public void setTrlCode(String trlCode) {
		this.trlCode = trlCode;
	}

	public String getRndResultName() {
		return rndResultName;
	}

	public void setRndResultName(String rndResultName) {
		this.rndResultName = rndResultName;
	}

}
