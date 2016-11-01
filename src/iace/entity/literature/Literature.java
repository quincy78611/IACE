package iace.entity.literature;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;

@Entity
@Table(name = "LITERATURE")
public class Literature extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = -1303534577833074323L;

	private long id;
	private long oid; // 批次匯入ID
	private String titleC; // 中文題名
	private String titleF; // 外文題名
	private String authorC; // 作者(中文)
	private String authorF; // 作者(外文)
	private String org; // 作者服務機構
	private String keywordC; // 中文關鍵詞
	private String keywordF; // 外文關鍵詞
	private String language; // 語文
	private String summary; // 原始中文摘要
	private String summaryF; // 原始外文摘要
	private String linkUrl; // 連結網址
	private String pagination; // 卷期頁碼(頁數)
	private Long publishYear; // 出版年
	private String advisor; // 指導教授
	private String publicationDate; // 論文出版年月
	private String degree; // 學位
	private String department; // 系所
	private String graduateSchoolC; // 畢業學校中文校名
	private String journalName; // 期刊名稱
	private String source; // 資料來源
	private String codeIndustryId; // 領域分類
	private String country; // 探討國別
	private String category; // 資料分類(文獻/法規政策)

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LITERATURE_ID")
	@SequenceGenerator(name = "SEQ_LITERATURE_ID", sequenceName = "SEQ_LITERATURE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "OID", length = 19, unique = true, nullable = false, updatable = false)
	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	@Column(name = "TITLE_C", length = 500)
	public String getTitleC() {
		return titleC;
	}

	public void setTitleC(String titleC) {
		this.titleC = titleC;
	}

	@Column(name = "TITLE_F", length = 500)
	public String getTitleF() {
		return titleF;
	}

	public void setTitleF(String titleF) {
		this.titleF = titleF;
	}

	@Column(name = "AUTHOR_C", length = 500)
	public String getAuthorC() {
		return authorC;
	}

	public void setAuthorC(String authorC) {
		this.authorC = authorC;
	}

	@Column(name = "AUTHOR_F", length = 500)
	public String getAuthorF() {
		return authorF;
	}

	public void setAuthorF(String authorF) {
		this.authorF = authorF;
	}

	@Column(name = "ORG", length = 500)
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Column(name = "KEYWORD_C", length = 1000)
	public String getKeywordC() {
		return keywordC;
	}

	public void setKeywordC(String keywordC) {
		this.keywordC = keywordC;
	}

	@Column(name = "KEYWORD_F", length = 1000)
	public String getKeywordF() {
		return keywordF;
	}

	public void setKeywordF(String keywordF) {
		this.keywordF = keywordF;
	}

	@Column(name = "LANGUAGE", length = 500)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "SUMMARY")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "SUMMARY_F")
	public String getSummaryF() {
		return summaryF;
	}

	public void setSummaryF(String summaryF) {
		this.summaryF = summaryF;
	}

	@Column(name = "LINK_URL", length = 500)
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "PAGINATION", length = 500)
	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	@Column(name = "PUBLISH_YEAR", length = 4)
	public Long getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Long publishYear) {
		this.publishYear = publishYear;
	}

	@Column(name = "ADVISOR", length = 100)
	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	@Column(name = "PUBLICATION_DATE", length = 100)
	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Column(name = "DEGREE", length = 100)
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	@Column(name = "DEPARTMENT", length = 100)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "GRADUATE_SCHOOL_C", length = 100)
	public String getGraduateSchoolC() {
		return graduateSchoolC;
	}

	public void setGraduateSchoolC(String graduateSchoolC) {
		this.graduateSchoolC = graduateSchoolC;
	}

	@Column(name = "JOURNAL_NAME", length = 100)
	public String getJournalName() {
		return journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	@Column(name = "SOURCE", length = 500)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "CODE_INDUSTRY_ID", length = 100)
	public String getCodeIndustryId() {
		return codeIndustryId;
	}

	public void setCodeIndustryId(String codeIndustryId) {
		this.codeIndustryId = codeIndustryId;
	}

	@Column(name = "COUNTRY", length = 100)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "CATEGORY", length = 100)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toLunceneContent() {
		String str = this.titleC + " " + this.titleF + " " + this.authorC + " " + 
				this.authorF  + " " + this.keywordC  + " " + this.keywordF  + " " +
				this.summary  + " " + this.summaryF  + " " + this.journalName;
		return str;
	}

}
