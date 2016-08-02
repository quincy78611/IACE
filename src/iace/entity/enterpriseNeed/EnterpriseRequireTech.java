package iace.entity.enterpriseNeed;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;
import iace.entity.option.OptionIndustryForEnterprise;

@Entity
@Table(name = "ENTERPRISE_REQUIRE_TECH")
public class EnterpriseRequireTech extends BaseEntity {

	private static final long serialVersionUID = 2554039737097763482L;

	private long id;
	private EnterpriseInfo enterpriseInfo;
	private OptionIndustryForEnterprise phase1;
	private String phase2;
	private String phase3;
	private String inquiredOrg;
	private Date interviewDate;
	private String recordBy;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_ENTERPRISE_ID")
	@SequenceGenerator(name = "SEQUENCE_ENTERPRISE_ID", sequenceName = "SEQUENCE_ENTERPRISE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ENTERPRISE_INFO_ID", referencedColumnName= "ID")
	public EnterpriseInfo getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}

	@ManyToOne
	@JoinColumn(name="PHASE1_INDUSTRY_ID", referencedColumnName= "ID")	
	public OptionIndustryForEnterprise getPhase1() {
		return phase1;
	}

	public void setPhase1(OptionIndustryForEnterprise phase1) {
		this.phase1 = phase1;
	}

	@Column(name = "PHASE2", length = 1000)
	public String getPhase2() {
		return phase2;
	}

	public void setPhase2(String phase2) {
		this.phase2 = phase2;
	}

	@Column(name = "PHASE3", length = 1000)
	public String getPhase3() {
		return phase3;
	}

	public void setPhase3(String phase3) {
		this.phase3 = phase3;
	}

	@Column(name = "INQUIRED_ORG", length = 1000)
	public String getInquiredOrg() {
		return inquiredOrg;
	}

	public void setInquiredOrg(String inquiredOrg) {
		this.inquiredOrg = inquiredOrg;
	}

	@Column(name = "INTERVIEW_DATE")
	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	@Column(name = "RECORD_BY", length = 100)
	public String getRecordBy() {
		return recordBy;
	}

	public void setRecordBy(String recordBy) {
		this.recordBy = recordBy;
	}

}