package iace.entity.incubationCenter;

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
@Table(name = "INCUBATION_CENTER")
public class IncubationCenter extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = -6291783231741808243L;

	private long id;
	private String seqNo;
	private String schoolCode;
	private String attribute;
	private String schoolNameCh;
	private String schoolNameEn;
	private String orgNameCh;
	private String orgNameEn;
	private String bossName;
	private String bossTitle;
	private String address;
	private String tel;
	private String email;
	private String url;
	private String orgHistory;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INCUBATION_CENTER_ID")
	@SequenceGenerator(name = "SEQ_INCUBATION_CENTER_ID", sequenceName = "SEQ_INCUBATION_CENTER_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "SEQ_NO")
	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	@Column(name = "SCHOOL_CODE")
	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	@Column(name = "ATTRIBUTE")
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Column(name = "SCHOOL_NAME_CH")
	public String getSchoolNameCh() {
		return schoolNameCh;
	}

	public void setSchoolNameCh(String schoolNameCh) {
		this.schoolNameCh = schoolNameCh;
	}

	@Column(name = "SCHOOL_NAME_EN")
	public String getSchoolNameEn() {
		return schoolNameEn;
	}

	public void setSchoolNameEn(String schoolNameEn) {
		this.schoolNameEn = schoolNameEn;
	}

	@Column(name = "ORG_NAME_CH")
	public String getOrgNameCh() {
		return orgNameCh;
	}

	public void setOrgNameCh(String orgNameCh) {
		this.orgNameCh = orgNameCh;
	}

	@Column(name = "ORG_NAME_EN")
	public String getOrgNameEn() {
		return orgNameEn;
	}

	public void setOrgNameEn(String orgNameEn) {
		this.orgNameEn = orgNameEn;
	}

	@Column(name = "BOSS_NAME")
	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	@Column(name = "BOSS_TITLE")
	public String getBossTitle() {
		return bossTitle;
	}

	public void setBossTitle(String bossTitle) {
		this.bossTitle = bossTitle;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ORG_HISTORY")
	public String getOrgHistory() {
		return orgHistory;
	}

	public void setOrgHistory(String orgHistory) {
		this.orgHistory = orgHistory;
	}

	@Override
	public String toLunceneContent() {
		String str = this.schoolNameCh + " " + this.schoolNameEn + " " +
				this.orgNameCh  + " " + this.orgNameEn;
		return str;
	}

}
