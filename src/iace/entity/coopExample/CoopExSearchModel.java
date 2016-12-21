package iace.entity.coopExample;

import iace.entity.BaseSearchModel;

public class CoopExSearchModel extends BaseSearchModel {

	private String searchText;
	private Integer year;
	private String type;
	private String rdTeam;
	private String assisTeam;

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRdTeam() {
		return rdTeam;
	}

	public void setRdTeam(String rdTeam) {
		this.rdTeam = rdTeam;
	}

	public String getAssisTeam() {
		return assisTeam;
	}

	public void setAssisTeam(String assisTeam) {
		this.assisTeam = assisTeam;
	}

}
