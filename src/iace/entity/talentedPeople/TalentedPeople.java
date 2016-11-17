package iace.entity.talentedPeople;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import core.util.AESEncrypter;
import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.OptionGrbDomain;
import iace.entity.sys.SysUser;

@Entity
@Table(name = "TALENTED_PEOPLE")
public class TalentedPeople extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = -769195556580032302L;

	private long id;
	private String nameCh;
	private String nameEn;
	private String gender;
	private Integer expYear;
	private String tel;
	private String email;
	private String workOrg;
	private String job;
	private String url;
	private String specialty;
	private List<OptionGrbDomain> domains = new ArrayList<OptionGrbDomain>();

	private byte[] headShot;
	private transient File uploadheadShot;
	private transient String uploadheadShotContentType;
	private transient String uploadheadShotFileName;
	
	private List<TalentedPeopleRdResult> rdResults = new ArrayList<TalentedPeopleRdResult>();
	private List<TalentedPeopleTransferCase> transferCases = new ArrayList<TalentedPeopleTransferCase>();
	private List<TalentedPeopleMainProject> mainProjects = new ArrayList<TalentedPeopleMainProject>();
	
	private String rewardHistory;
	private String otherExperience;
	
	private SysUser sysUser;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TALENTED_PEOPLE_ID")
	@SequenceGenerator(name = "SEQUENCE_TALENTED_PEOPLE_ID", sequenceName = "SEQUENCE_TALENTED_PEOPLE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME_CH")
	public String getNameCh() {
		return nameCh;
	}

	public void setNameCh(String nameCh) {
		this.nameCh = nameCh;
	}

	@Column(name = "NAME_EN")
	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "EXP_YEAR")
	public Integer getExpYear() {
		return expYear;
	}

	public void setExpYear(Integer expYear) {
		this.expYear = expYear;
	}

	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Transient
	public String getDecryptedTel() {
		return AESEncrypter.decrypt(AESEncrypter.KEY, this.tel);
	}

	public void setDecryptedTel(String decryptedTel) {
		this.tel = AESEncrypter.encrypt(AESEncrypter.KEY, decryptedTel);
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Transient
	public String getDecryptedEmail() {
		return AESEncrypter.decrypt(AESEncrypter.KEY, this.email);
	}

	public void setDecryptedEmail(String decryptedEmail) {
		this.email = AESEncrypter.encrypt(AESEncrypter.KEY, decryptedEmail);
	}

	@Column(name = "WORK_ORG")
	public String getWorkOrg() {
		return workOrg;
	}

	public void setWorkOrg(String workOrg) {
		this.workOrg = workOrg;
	}

	@Column(name = "JOB")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "SPECIALTY", length = 1000)
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="TALENTED_PEOPLE_DOMAIN_R", 
    	joinColumns={@JoinColumn(name="TALENTED_PEOPLE_ID")}, 
    	inverseJoinColumns={@JoinColumn(name="OPT_GRB_DOMAIN_ID")})
	@Fetch(FetchMode.SUBSELECT)
	public List<OptionGrbDomain> getDomains() {
		return domains;
	}

	public void setDomains(List<OptionGrbDomain> domains) {
		this.domains = domains;
	}
	
	public void addDomain(OptionGrbDomain domain) {
		if (this.domains == null) {
			this.domains = new ArrayList<OptionGrbDomain>();
		}
		this.domains.add(domain);
	}
	
	@Transient
	public List<Long> getDomainsId() {
		List<Long> ids = new ArrayList<Long>();
		for (OptionGrbDomain domain : this.domains) {
			ids.add(domain.getId());
		}
		return ids;
	}
	
	public void setDomainsId(List<Long> ids) {
		this.domains = new ArrayList<OptionGrbDomain>();
		for (long id : ids) {
			OptionGrbDomain domain = new OptionGrbDomain();
			domain.setId(id);
			this.domains.add(domain);
		}
	}
	
	@Transient
	public String getMainDomainCodeString() {
		StringBuilder sb = new StringBuilder();
		for (OptionGrbDomain grb : this.domains) {
			try {
				sb.append(grb.getMainDomain().getCode()).append(";");
			} catch(NullPointerException e) {
				sb.append("null").append(";");
			}
		}
		return sb.toString();
	}
	
	@Transient
	public String getSubDomainCodeString() {
		StringBuilder sb = new StringBuilder();
		for (OptionGrbDomain grb : this.domains) {
			sb.append(grb.getCode()).append(";");
		}		
		return sb.toString();
	}
	
	@Transient
	public String getDomainsNameForSysLog() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<this.domains.size(); i++) {
			sb.append(this.domains.get(i).toSysLog());
			if (i < this.domains.size() - 1) {
				sb.append("; ");
			}
		}
		return sb.toString();
	}

	@Column(name = "HEAD_SHOT")
	public byte[] getHeadShot() {
		return headShot;
	}

	public void setHeadShot(byte[] headShot) {
		this.headShot = headShot;
	}
	
	@Transient
	public String getBase64HeadShot() {
		if (this.headShot != null) {
			return Base64.encode(this.headShot);
		}
		return null;
	}
	
	public void setBase64HeadShot(String s) {
		if (StringUtils.isNotBlank(s)) {
			this.headShot = Base64.decode(s);
		}
	}

	@Transient
	public File getUploadheadShot() {
		return uploadheadShot;
	}

	public void setUploadheadShot(File uploadheadShot) {
		this.uploadheadShot = uploadheadShot;
	}

	@Transient
	public String getUploadheadShotContentType() {
		return uploadheadShotContentType;
	}

	public void setUploadheadShotContentType(String uploadheadShotContentType) {
		this.uploadheadShotContentType = uploadheadShotContentType;
	}

	@Transient
	public String getUploadheadShotFileName() {
		return uploadheadShotFileName;
	}

	public void setUploadheadShotFileName(String uploadheadShotFileName) {
		this.uploadheadShotFileName = uploadheadShotFileName;
	}

	@Transient 
	public boolean hasUploadFile() {
		return this.uploadheadShot != null && this.uploadheadShotContentType != null && this.uploadheadShotFileName != null;
	}
	
	
	@OneToMany(mappedBy = "talentedPeople", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("PRIORITY ASC, ID ASC")
	public List<TalentedPeopleRdResult> getRdResults() {
		return rdResults;
	}

	public void setRdResults(List<TalentedPeopleRdResult> rdResults) {
		this.rdResults = rdResults;
	}

	@OneToMany(mappedBy = "talentedPeople", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("PRIORITY ASC, ID ASC")
	public List<TalentedPeopleTransferCase> getTransferCases() {
		return transferCases;
	}

	public void setTransferCases(List<TalentedPeopleTransferCase> transferCases) {
		this.transferCases = transferCases;
	}

	@OneToMany(mappedBy = "talentedPeople", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("PRIORITY ASC, ID ASC")
	public List<TalentedPeopleMainProject> getMainProjects() {
		return mainProjects;
	}

	public void setMainProjects(List<TalentedPeopleMainProject> mainProjects) {
		this.mainProjects = mainProjects;
	}
	
	@Column(name = "REWARD_HISTORY")
	public String getRewardHistory() {
		return rewardHistory;
	}

	public void setRewardHistory(String rewardHistory) {
		this.rewardHistory = rewardHistory;
	}

	@Column(name = "OTHER_EXP")
	public String getOtherExperience() {
		return otherExperience;
	}

	public void setOtherExperience(String otherExperience) {
		this.otherExperience = otherExperience;
	}
	
	@ManyToOne
	@JoinColumn(name="SYS_USER_ID", referencedColumnName= "ID")
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Override
	public String toSysLog() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID: {"+this.id+"}, \r\n");
		sb.append("姓名(中): {"+this.nameCh+"}, \r\n");
		sb.append("姓名(英): {"+this.nameEn+"}, \r\n");
		sb.append("性別: {"+this.gender+"}, \r\n");
		sb.append("產學經驗(年): {"+this.expYear+"}, \r\n");
		sb.append("連絡電話: {"+this.tel+"}, \r\n");
		sb.append("e-mail: {"+this.email+"}, \r\n");
		sb.append("現職單位: {"+this.workOrg+"}, \r\n");
		sb.append("現職職位: {"+this.job+"}, \r\n");
		sb.append("網站連結: {"+this.url+"}, \r\n");
		sb.append("領域: {"+this.getDomainsNameForSysLog()+"}, \r\n");
		sb.append("合作專長: {"+this.specialty+"}, \r\n");
		
		return sb.toString();
	}

	@Override
	public String toLunceneContent() {
		String content = this.nameCh + " " + this.nameEn  + " " + this.specialty;
		return content;
	}
	
	
}
