package lucene.integrationSearch;

import iace.entity.activity.Activity;
import iace.entity.coopExample.CoopEx;
import iace.entity.incubationCenter.IncubationCenter;
import iace.entity.industryInfo.IndustryInfo;
import iace.entity.literature.Literature;
import iace.entity.news.News;
import iace.entity.patent.Patent;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.Technology;
import iace.entity.talentedPeople.TalentedPeople;

public class IntegrationSearchResult {

	private String type;

	private ResearchPlan researchPlan;
	private Technology technology;
	private Patent patent;
	private TalentedPeople talentedPeople;
	private CoopEx coopEx;
	private Literature literature;
	private IncubationCenter incubationCenter;
	
	private Activity activity;
	private IndustryInfo industryInfo;
	private News news;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ResearchPlan getResearchPlan() {
		return researchPlan;
	}

	public void setResearchPlan(ResearchPlan researchPlan) {
		this.researchPlan = researchPlan;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public Patent getPatent() {
		return patent;
	}

	public void setPatent(Patent patent) {
		this.patent = patent;
	}

	public TalentedPeople getTalentedPeople() {
		return talentedPeople;
	}

	public void setTalentedPeople(TalentedPeople talentedPeople) {
		this.talentedPeople = talentedPeople;
	}

	public CoopEx getCoopEx() {
		return coopEx;
	}

	public void setCoopEx(CoopEx coopEx) {
		this.coopEx = coopEx;
	}

	public Literature getLiterature() {
		return literature;
	}

	public void setLiterature(Literature literature) {
		this.literature = literature;
	}

	public IncubationCenter getIncubationCenter() {
		return incubationCenter;
	}

	public void setIncubationCenter(IncubationCenter incubationCenter) {
		this.incubationCenter = incubationCenter;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public IndustryInfo getIndustryInfo() {
		return industryInfo;
	}

	public void setIndustryInfo(IndustryInfo industryInfo) {
		this.industryInfo = industryInfo;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}
