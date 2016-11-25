package iace.entity.member;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;
import iace.entity.option.BaseOption;
import iace.entity.option.OptionDomain;
import iace.entity.option.OptionIndustry;

@Entity
@Table(name = "MEMBER")
public class Member extends BaseEntity {
	private static final long serialVersionUID = -306912702673980420L;
	private static List<BaseOption> industryList = new ArrayList<BaseOption>();
	private static List<BaseOption> jobTypeList = new ArrayList<BaseOption>();
	static {
		industryList.add(new BaseOption("A", "農林漁牧業"));
		industryList.add(new BaseOption("B", "礦業及土石採取業"));
		industryList.add(new BaseOption("C", "製造業"));
		industryList.add(new BaseOption("D", "電力及燃氣供應業"));
		industryList.add(new BaseOption("E", "用水供應及污染整治業"));
		industryList.add(new BaseOption("F", "營造業"));
		industryList.add(new BaseOption("G1", "批發及零售業"));
		industryList.add(new BaseOption("G2", "金融及保險業"));
		industryList.add(new BaseOption("G3", "資訊及通訊傳播"));
		industryList.add(new BaseOption("G4", "醫療保健及社會工作服務業"));
		industryList.add(new BaseOption("G5", "專業及科學技術服務業"));
		industryList.add(new BaseOption("G9", "其他服務業"));
		industryList.add(new BaseOption("H", "公協會"));
		industryList.add(new BaseOption("I", "學校及學術研究機構"));
		industryList.add(new BaseOption("J", "財團法人研究機構"));
		industryList.add(new BaseOption("K", "政府單位及民意機構"));
		industryList.add(new BaseOption("M", "無"));
		jobTypeList.add(new BaseOption("企畫人員", "企畫人員"));
		jobTypeList.add(new BaseOption("研究人員", "研究人員"));
		jobTypeList.add(new BaseOption("工程人員", "工程人員"));
		jobTypeList.add(new BaseOption("業務銷售人員", "業務銷售人員"));
		jobTypeList.add(new BaseOption("行政人員", "行政人員"));
		jobTypeList.add(new BaseOption("管理階層", "管理階層"));
		jobTypeList.add(new BaseOption("其他", "其他"));
	}
	
	private long id;
	private String account;
	private String password;
	private String email;
	private String name;
	private String org; // 公司/團體/學校
	private String dept; // 部門/系所
	private String industry; // 行業別 
	private List<OptionIndustry> optIndustryList = new ArrayList<OptionIndustry>(); // 產業別
	private String jobType; // 職務別
	private String jobTitle; // 職稱
	private String address;
	private String postalCode; // 郵遞區號
	private String tel;
	private String mobile;
	private OptionDomain optDomain; // 技術需求領域
	private String neededTec; //技術需求項目
	private boolean useStatus; //使用狀態
	
	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MEMBER_ID")
	@SequenceGenerator(name = "SEQ_MEMBER_ID", sequenceName = "SEQ_MEMBER_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORG")
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Column(name = "DEPT")
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Column(name = "INDUSTRY")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Transient
	public String getIndustryText() {
		for (BaseOption opt : industryList) {
			if (opt.getClass().equals(this.industry)) {
				return opt.getName();
			}
		}
		return null;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="MEMBER_INDUSTRY_R", 
    	joinColumns={@JoinColumn(name="MEMBER_ID")}, 
    	inverseJoinColumns={@JoinColumn(name="OPT_INDUSTRY_ID")})
	@Fetch(FetchMode.SUBSELECT)
	public List<OptionIndustry> getOptIndustryList() {
		return optIndustryList;
	}

	public void setOptIndustryList(List<OptionIndustry> optIndustryList) {
		this.optIndustryList = optIndustryList;
	}
	
	@Transient
	public List<Long> getOptIndustryIdList() {
		List<Long> ids = new ArrayList<Long>();
		for (OptionIndustry opt : this.optIndustryList) {
			ids.add(opt.getId());
		}
		return ids;
	}
	
	public void setOptIndustryIdList(List<Long> ids) {
		this.optIndustryList = new ArrayList<OptionIndustry>();
		for (long id : ids) {
			OptionIndustry opt = new OptionIndustry();
			opt.setId(id);
			this.optIndustryList.add(opt);
		}
	}

	@Column(name = "JOB_TYPE")
	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	@Column(name = "JOB_TITLE")
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "POSTAL_CODE")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ManyToOne
	@JoinColumn(name="OPT_DOMAIN_ID", referencedColumnName= "ID")
	public OptionDomain getOptDomain() {
		return optDomain;
	}

	public void setOptDomain(OptionDomain optDomain) {
		this.optDomain = optDomain;
	}

	@Column(name = "NEEDED_TEC")
	public String getNeededTec() {
		return neededTec;
	}

	@Column(name = "USE_STATUS")
	@Type(type="true_false")
	public boolean getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(boolean useStatus) {
		this.useStatus = useStatus;
	}

	public void setNeededTec(String neededTec) {
		this.neededTec = neededTec;
	}

	public static List<BaseOption> getIndustryList() {
		return industryList;
	}

	public static List<BaseOption> getJobTypeList() {
		return jobTypeList;
	}

}
