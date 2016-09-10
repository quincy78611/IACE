package iace.entity.talentedPeople;

import java.util.ArrayList;
import java.util.List;

import iace.entity.BaseSearchModel;

public class TalentedPeopleSearchModel extends BaseSearchModel {

	private String name;
	private String gender;
	private Integer expYearS;
	private Integer expYearE;
	private String workOrg;
	private String job;
	private String specialty;
	private List<Long> grbDomainIdList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getExpYearS() {
		return expYearS;
	}

	public void setExpYearS(Integer expYearS) {
		this.expYearS = expYearS;
	}

	public Integer getExpYearE() {
		return expYearE;
	}

	public void setExpYearE(Integer expYearE) {
		this.expYearE = expYearE;
	}

	public String getWorkOrg() {
		return workOrg;
	}

	public void setWorkOrg(String workOrg) {
		this.workOrg = workOrg;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public List<Long> getGrbDomainIdList() {
		return grbDomainIdList;
	}

	public void setGrbDomainIdList(List<Long> grbDomainIdList) {
		List<Long> list = new ArrayList<Long>();
		for (Long id : grbDomainIdList) {
			if (id != null && id > 0) {
				list.add(id);
			}
		}
		if (list.size() > 0) {
			this.grbDomainIdList = list;
		}
	}

	

}
