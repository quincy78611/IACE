package iace.entity.researchPlan;

import iace.entity.BaseSearchModel;

public class ResearchPlanSearchModel extends BaseSearchModel {

	private String planName;
	private String planNo;
	private Integer year;
	private String keyword;
	private Long trlId;
	private String manager;
	private Long grbDomainId;
	private String technologyName;
	private Long technologyTrlId;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getTrlId() {
		return trlId;
	}

	public void setTrlId(Long trlId) {
		this.trlId = trlId;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Long getGrbDomainId() {
		return grbDomainId;
	}

	public void setGrbDomainId(Long grbDomainId) {
		this.grbDomainId = grbDomainId;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public Long getTechnologyTrlId() {
		return technologyTrlId;
	}

	public void setTechnologyTrlId(Long technologyTrlId) {
		this.technologyTrlId = technologyTrlId;
	}

}
