package iace.entity.researchPlan;

import java.util.ArrayList;
import java.util.List;

public class ResearchPlanManagerSearchResult implements Comparable<ResearchPlanManagerSearchResult> {
	
	private String manager;
	private int researchPlanCount = 0;
	private List<Long> researchPlanIdList = new ArrayList<Long>();
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public List<Long> getResearchPlanIdList() {
		return researchPlanIdList;
	}

	public void addResearchPlanId(Long id) {
		this.researchPlanIdList.add(id);
		this.researchPlanCount = this.researchPlanIdList.size();
	}
	
	public String getResearchPlanIdListString() {
		StringBuilder sb = new StringBuilder();
		for (long id : this.researchPlanIdList) {
			sb.append(id).append("; ");
		}
		return sb.toString();
	}
	
	public int getResearchPlanCount() {
		return this.researchPlanCount;
	}
	
	@Override
	public int compareTo(ResearchPlanManagerSearchResult o) {
		return o.getResearchPlanCount() - this.getResearchPlanCount();
	}
}
