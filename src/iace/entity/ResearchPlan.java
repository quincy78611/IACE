package iace.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionTrl;

@Entity
@Table(name = "RESEARCH_PLAN", schema = "IACE_ADMIN")
public class ResearchPlan extends BaseEntity {

	private static final long serialVersionUID = 6137186068641120935L;

	private long id;
	private int year;
	private String planNo;
	private String name;
	private String manager;
	private String grbDomainCode1;
	private String grbDomainCode2;
	private String grbDomainCode3;
	private String grbDomainCode4;
	private String grbDomainCode5;
	private String grbDomainCode6;
	private String keyword;
	private String trlCode;
	private String projkey;
	private String grb05Id;
	
	private List<OptionGrbDomain> grbDomains;
	private OptionTrl trl;
	private List<RnDResult> rndResults;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_RESEARCH_PLAN_ID")
	@SequenceGenerator(name = "SEQUENCE_RESEARCH_PLAN_ID", sequenceName = "SEQUENCE_RESEARCH_PLAN_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "YEAR", length = 4)
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "PLAN_NO", length = 100)
	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	@Column(name = "NAME", length = 2000)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MANAGER", length = 100)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Column(name = "GRB_DOMAIN_CODE1", length = 10)
	public String getGrbDomainCode1() {
		return grbDomainCode1;
	}

	public void setGrbDomainCode1(String grbDomainCode1) {
		this.grbDomainCode1 = grbDomainCode1;
	}

	@Column(name = "GRB_DOMAIN_CODE2", length = 10)
	public String getGrbDomainCode2() {
		return grbDomainCode2;
	}

	public void setGrbDomainCode2(String grbDomainCode2) {
		this.grbDomainCode2 = grbDomainCode2;
	}

	@Column(name = "GRB_DOMAIN_CODE3", length = 10)
	public String getGrbDomainCode3() {
		return grbDomainCode3;
	}

	public void setGrbDomainCode3(String grbDomainCode3) {
		this.grbDomainCode3 = grbDomainCode3;
	}

	@Column(name = "GRB_DOMAIN_CODE4", length = 10)
	public String getGrbDomainCode4() {
		return grbDomainCode4;
	}

	public void setGrbDomainCode4(String grbDomainCode4) {
		this.grbDomainCode4 = grbDomainCode4;
	}

	@Column(name = "GRB_DOMAIN_CODE5", length = 10)
	public String getGrbDomainCode5() {
		return grbDomainCode5;
	}

	public void setGrbDomainCode5(String grbDomainCode5) {
		this.grbDomainCode5 = grbDomainCode5;
	}

	@Column(name = "GRB_DOMAIN_CODE6", length = 10)
	public String getGrbDomainCode6() {
		return grbDomainCode6;
	}

	public void setGrbDomainCode6(String grbDomainCode6) {
		this.grbDomainCode6 = grbDomainCode6;
	}

	@Transient
	public String getGrbDomainCode(int index) {
		switch (index) {
		case 1:
			return this.getGrbDomainCode1();			
		case 2:
			return this.getGrbDomainCode2();
		case 3:
			return this.getGrbDomainCode3();
		case 4:
			return this.getGrbDomainCode4();
		case 5:
			return this.getGrbDomainCode5();
		case 6:
			return this.getGrbDomainCode6();
		default:
			throw new IllegalArgumentException("index out of bound");
		}			
	}
	
	public void setGrbDomainCode(int index, String grbDomainCode) {
		switch (index) {
		case 1:
			this.setGrbDomainCode1(grbDomainCode);
			break;
		case 2:
			this.setGrbDomainCode2(grbDomainCode);
			break;
		case 3:
			this.setGrbDomainCode3(grbDomainCode);
			break;
		case 4:
			this.setGrbDomainCode4(grbDomainCode);
			break;
		case 5:
			this.setGrbDomainCode5(grbDomainCode);
			break;
		case 6:
			this.setGrbDomainCode6(grbDomainCode);
			break;
		default:
			throw new IllegalArgumentException("index out of bound (index = "+index+")");
		}	
	}
	
	@Column(name = "KEYWORD", length = 2000)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "TRL_CODE", length = 10)
	public String getTrlCode() {
		return trlCode;
	}

	public void setTrlCode(String trlCode) {
		this.trlCode = trlCode;
	}

	@Column(name = "PROJKEY", length = 100)
	public String getProjkey() {
		return projkey;
	}

	public void setProjkey(String projkey) {
		this.projkey = projkey;
	}

	@Column(name = "GRB05_ID", length = 500)
	public String getGrb05Id() {
		return grb05Id;
	}

	public void setGrb05Id(String grb05Id) {
		this.grb05Id = grb05Id;
	}	
	
	@Transient
	public List<OptionGrbDomain> getGrbDomains() {
		return grbDomains;
	}

	public void setGrbDomains(List<OptionGrbDomain> grbDomains) {
		this.grbDomains = grbDomains;
	}

	@Transient
	public OptionTrl getTrl() {
		return trl;
	}

	public void setTrl(OptionTrl trl) {
		this.trl = trl;
	}

	@OneToMany(mappedBy="researchPlan", cascade={CascadeType.ALL})
	//@JoinColumn(name="ID")
	public List<RnDResult> getRndResults() {
		return rndResults;
	}

	public void setRndResults(List<RnDResult> rndResults) {
		this.rndResults = rndResults;
	}

	@Override
	public String toString() {
		return "\"ResearchPlan\" : {\"id\"=\"" + id + "\", \"year\"=\"" + year + "\", \"planNo\"=\"" + planNo
				+ "\", \"name\"=\"" + name + "\", \"manager\"=\"" + manager + "\", \"grbDomainCode1\"=\""
				+ grbDomainCode1 + "\", \"grbDomainCode2\"=\"" + grbDomainCode2 + "\", \"grbDomainCode3\"=\""
				+ grbDomainCode3 + "\", \"grbDomainCode4\"=\"" + grbDomainCode4 + "\", \"grbDomainCode5\"=\""
				+ grbDomainCode5 + "\", \"grbDomainCode6\"=\"" + grbDomainCode6 + "\", \"keyword\"=\"" + keyword
				+ "\", \"trlCode\"=\"" + trlCode + "\", \"projkey\"=\"" + projkey + "\", \"grb05Id\"=\"" + grb05Id
				+ "\",  " + super.toString() + "}";
	}

	
}
