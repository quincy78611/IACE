package iace.entity.patent;

public class PatentSearchModel {

	private String name;
	private String appNo;
	private String countryCode;
	// private TechField techField;
	private long techFieldId;

	private int pageIndex;
	private int pageSize;

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
