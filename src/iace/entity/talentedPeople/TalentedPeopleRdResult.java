package iace.entity.talentedPeople;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.BaseOption;
import iace.entity.option.OptionCountry;

@Entity
@Table(name = "TALENTED_PEOPLE_RD_RESULT")
public class TalentedPeopleRdResult extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = 7404017170622750603L;
	
	private static List<BaseOption> typeList = new ArrayList<BaseOption>();
	static {
		typeList.add(new BaseOption("專利獲准", "專利獲准(請填5~8)"));
		typeList.add(new BaseOption("專利申請中", "專利申請中(請填5,7)"));
		typeList.add(new BaseOption("技術/KNOW-HOW ", "技術/KNOW-HOW "));
		typeList.add(new BaseOption("積體電路布局", "積體電路布局"));
		typeList.add(new BaseOption("軟體", "軟體"));
		typeList.add(new BaseOption("其他", "其他"));
	}

	private long id;
	private TalentedPeople talentedPeople;
	private String name;
	private String type;
	private String inventer;
	private String owner;
//	private String applicationNo;
	private String patentNo;
	private OptionCountry optionCountry;
	private Date patentPeriodStart;
	private Date patentPeriodEnd;
	private String patentAbstract;
	private String usage;
	private Date updateDate;
	private Float priority;
	

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TALENTED_PEOPLE_ID", nullable = false, updatable = false)
	public TalentedPeople getTalentedPeople() {
		return talentedPeople;
	}

	public void setTalentedPeople(TalentedPeople talentedPeople) {
		this.talentedPeople = talentedPeople;
	}

	@Column(name = "NAME", length = 500)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "INVENTER", length = 100)
	public String getInventer() {
		return inventer;
	}

	public void setInventer(String inventer) {
		this.inventer = inventer;
	}

	@Column(name = "OWNER", length = 100)
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

//	@Column(name = "APPLICATION_NO", length = 100)
//	public String getApplicationNo() {
//		return applicationNo;
//	}
//
//	public void setApplicationNo(String applicationNo) {
//		this.applicationNo = applicationNo;
//	}

	@Column(name = "PATENT_NO", length = 100)
	public String getPatentNo() {
		return patentNo;
	}

	public void setPatentNo(String patentNo) {
		this.patentNo = patentNo;
	}

	@ManyToOne
	@JoinColumn(name = "OPT_COUNTRY_ID", referencedColumnName = "ID")
	public OptionCountry getOptionCountry() {
		return optionCountry;
	}

	public void setOptionCountry(OptionCountry optionCountry) {
		this.optionCountry = optionCountry;
	}

	@Column(name = "PATENT_PERIOD_S")
	public Date getPatentPeriodStart() {
		return patentPeriodStart;
	}

	public void setPatentPeriodStart(Date patentPeriodStart) {
		this.patentPeriodStart = patentPeriodStart;
	}

	@Column(name = "PATENT_PERIOD_E")
	public Date getPatentPeriodEnd() {
		return patentPeriodEnd;
	}

	public void setPatentPeriodEnd(Date patentPeriodEnd) {
		this.patentPeriodEnd = patentPeriodEnd;
	}

	@Column(name = "ABSTRACT", length = 2000)
	public String getPatentAbstract() {
		return patentAbstract;
	}

	public void setPatentAbstract(String patentAbstract) {
		this.patentAbstract = patentAbstract;
	}

	@Column(name = "USAGE", length = 2000)
	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "PRIORITY")
	public Float getPriority() {
		return priority;
	}

	public void setPriority(Float priority) {
		this.priority = priority;
	}

	public static List<BaseOption> getTypeList() {
		return typeList;
	}

	@Override
	public String toLunceneContent() {
		String content = 
				this.name + " " + 
				this.inventer + " " + 
				this.owner + " " +
				this.patentAbstract + " " +
				this.usage;
		return content;
	}

	
}
