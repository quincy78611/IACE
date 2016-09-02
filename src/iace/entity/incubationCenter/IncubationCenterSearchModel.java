package iace.entity.incubationCenter;

import iace.entity.BaseSearchModel;

public class IncubationCenterSearchModel extends BaseSearchModel {

	private String attribute;
	private String name; // Use to search "schoolNameCh", "orgNameCh"

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
