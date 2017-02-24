package iace.entity.ePaper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import iace.entity.activity.Activity;
import iace.entity.faq.Faq;
import iace.entity.industryInfo.IndustryInfo;
import iace.entity.news.News;
import iace.entity.patent.Patent;
import iace.entity.rdFocus.RdFocus;
import iace.entity.researchPlan.ResearchPlan;

public class EPaperProduceTemplate {
	private String title;
	private Date postDate;
	private int no;

	private Set<Long> newsIds;
	private Set<Long> activityIds;
	private Set<Long> rdFocusIds;
	private Set<Long> researchPlanIds;
	private Set<Long> patentIds;
	private Set<Long> industryInfoIds; // 新聞雷達
	private Set<Long> faqIds;

	private List<News> newsList = new ArrayList<News>();
	private List<Activity> activityList = new ArrayList<Activity>();
	private List<RdFocus> rdFocusList = new ArrayList<RdFocus>();
	private List<ResearchPlan> researchPlanList = new ArrayList<ResearchPlan>();
	private List<Patent> patentList = new ArrayList<Patent>();
	private List<IndustryInfo> industryInfoList = new ArrayList<IndustryInfo>(); // 新聞雷達
	private List<Faq> faqList = new ArrayList<Faq>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Set<Long> getNewsIds() {
		return newsIds;
	}

	public void setNewsIds(Set<Long> newsIds) {
		this.newsIds = newsIds;
	}

	public Set<Long> getActivityIds() {
		return activityIds;
	}

	public void setActivityIds(Set<Long> activityIds) {
		this.activityIds = activityIds;
	}

	public Set<Long> getRdFocusIds() {
		return rdFocusIds;
	}

	public void setRdFocusIds(Set<Long> rdFocusIds) {
		this.rdFocusIds = rdFocusIds;
	}

	public Set<Long> getResearchPlanIds() {
		return researchPlanIds;
	}

	public void setResearchPlanIds(Set<Long> researchPlanIds) {
		this.researchPlanIds = researchPlanIds;
	}

	public Set<Long> getPatentIds() {
		return patentIds;
	}

	public void setPatentIds(Set<Long> patentIds) {
		this.patentIds = patentIds;
	}

	public Set<Long> getIndustryInfoIds() {
		return industryInfoIds;
	}

	public void setIndustryInfoIds(Set<Long> industryInfoIds) {
		this.industryInfoIds = industryInfoIds;
	}

	public Set<Long> getFaqIds() {
		return faqIds;
	}

	public void setFaqIds(Set<Long> faqIds) {
		this.faqIds = faqIds;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public List<Activity> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}

	public List<RdFocus> getRdFocusList() {
		return rdFocusList;
	}

	public void setRdFocusList(List<RdFocus> rdFocusList) {
		this.rdFocusList = rdFocusList;
	}

	public List<ResearchPlan> getResearchPlanList() {
		return researchPlanList;
	}

	public void setResearchPlanList(List<ResearchPlan> researchPlanList) {
		this.researchPlanList = researchPlanList;
	}

	public List<Patent> getPatentList() {
		return patentList;
	}

	public void setPatentList(List<Patent> patentList) {
		this.patentList = patentList;
	}

	public List<IndustryInfo> getIndustryInfoList() {
		return industryInfoList;
	}

	public void setIndustryInfoList(List<IndustryInfo> industryInfoList) {
		this.industryInfoList = industryInfoList;
	}

	public List<Faq> getFaqList() {
		return faqList;
	}

	public void setFaqList(List<Faq> faqList) {
		this.faqList = faqList;
	}

}
