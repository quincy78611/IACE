package iace.entity.enterpriseNeed;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import iace.entity.BaseEntity;
import iace.entity.option.OptionCompanyLocation;
import iace.entity.option.OptionIndustryForEnterprise;

@Entity
@Table(name = "ENTERPRISE_INFO")
public class EnterpriseInfo extends BaseEntity {

	private static final long serialVersionUID = 970867185759228661L;

	private long id;
	private String name;
	private String companyId;
	private String mainProduct;
	private List<OptionIndustryForEnterprise> optionIndustryForEnterpriseList;
	private String phase2;
	private String phase3;
	private OptionCompanyLocation optionCompanyLocation;
	private String address;
	private String personInChargeName;
	private String personInChargeJobtitle;
	private String personInChargePhone;
	private String personInChargeEmail;
	private String intervieweeName;
	private String intervieweeJobtitle;
	private String intervieweePhone;
	private String intervieweeEmail;
	private EnterpriseRequireTech enterpriseRequireTech;
	private EnterpriseSituation enterpriseSituation;
	private EnterpriseAcademiaCoop enterpriseAcademiaCoop;

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

	@Column(name = "NAME", length = 500)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COMPANY_ID", length = 100, unique = true)
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "MAIN_PRODUCT", length = 1000)
	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="ENTERPRISE_INDUSTRY_RELATION", 
    	joinColumns={@JoinColumn(name="ENTERPRISE_INFO_ID")}, 
    	inverseJoinColumns={@JoinColumn(name="OPT_INDUSRTY_ID")})
//	@Fetch(FetchMode.SUBSELECT)
	public List<OptionIndustryForEnterprise> getOptionIndustryForEnterpriseList() {
		return optionIndustryForEnterpriseList;
	}

	public void setOptionIndustryForEnterpriseList(List<OptionIndustryForEnterprise> optionIndustryForEnterpriseList) {
		this.optionIndustryForEnterpriseList = optionIndustryForEnterpriseList;
	}
	
	@Transient
	public List<Long> getOptionIndustryIdList() {
		ArrayList<Long> list = new ArrayList<Long>();
		this.optionIndustryForEnterpriseList.forEach(v -> list.add(v.getId()));
		return list;
	}
	
	public void setOptionIndustryIdList(List<Long> idList) {
		this.optionIndustryForEnterpriseList = new ArrayList<OptionIndustryForEnterprise>();
		for (long id : idList) {
			OptionIndustryForEnterprise option = new OptionIndustryForEnterprise();
			option.setId(id);
			this.optionIndustryForEnterpriseList.add(option);
		}		
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="OPT_COM_LOCATION_ID", referencedColumnName= "ID")
	public OptionCompanyLocation getOptionCompanyLocation() {
		return optionCompanyLocation;
	}

	public void setOptionCompanyLocation(OptionCompanyLocation optionCompanyLocation) {
		this.optionCompanyLocation = optionCompanyLocation;
	}

	@Column(name = "ADDRESS", length = 500)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PERSON_IN_CHARGE_NAME", length = 100)
	public String getPersonInChargeName() {
		return personInChargeName;
	}

	public void setPersonInChargeName(String personInChargeName) {
		this.personInChargeName = personInChargeName;
	}

	@Column(name = "PERSON_IN_CHARGE_JOBTITLE", length = 100)
	public String getPersonInChargeJobtitle() {
		return personInChargeJobtitle;
	}

	public void setPersonInChargeJobtitle(String personInChargeJobtitle) {
		this.personInChargeJobtitle = personInChargeJobtitle;
	}

	@Column(name = "PERSON_IN_CHARGE_PHONE", length = 100)
	public String getPersonInChargePhone() {
		return personInChargePhone;
	}

	public void setPersonInChargePhone(String personInChargePhone) {
		this.personInChargePhone = personInChargePhone;
	}

	@Column(name = "PERSON_IN_CHARGE_EMAIL", length = 100)
	public String getPersonInChargeEmail() {
		return personInChargeEmail;
	}

	public void setPersonInChargeEmail(String personInChargeEmail) {
		this.personInChargeEmail = personInChargeEmail;
	}

	@Column(name = "INTERVIEWEE_NAME", length = 100)
	public String getIntervieweeName() {
		return intervieweeName;
	}

	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	@Column(name = "INTERVIEWEE_JOBTITLE", length = 100)
	public String getIntervieweeJobtitle() {
		return intervieweeJobtitle;
	}

	public void setIntervieweeJobtitle(String intervieweeJobtitle) {
		this.intervieweeJobtitle = intervieweeJobtitle;
	}

	@Column(name = "INTERVIEWEE_PHONE", length = 100)
	public String getIntervieweePhone() {
		return intervieweePhone;
	}

	public void setIntervieweePhone(String intervieweePhone) {
		this.intervieweePhone = intervieweePhone;
	}

	@Column(name = "INTERVIEWEE_EMAIL", length = 100)
	public String getIntervieweeEmail() {
		return intervieweeEmail;
	}

	public void setIntervieweeEmail(String intervieweeEmail) {
		this.intervieweeEmail = intervieweeEmail;
	}

	@OneToOne(mappedBy = "enterpriseInfo", cascade= { CascadeType.ALL }, fetch = FetchType.EAGER)
	public EnterpriseRequireTech getEnterpriseRequireTech() {
		return enterpriseRequireTech;
	}

	public void setEnterpriseRequireTech(EnterpriseRequireTech enterpriseRequireTech) {
		this.enterpriseRequireTech = enterpriseRequireTech;
	}

	@OneToOne(mappedBy = "enterpriseInfo", cascade= { CascadeType.ALL }, fetch = FetchType.EAGER)
	public EnterpriseSituation getEnterpriseSituation() {
		return enterpriseSituation;
	}

	public void setEnterpriseSituation(EnterpriseSituation enterpriseSituation) {
		this.enterpriseSituation = enterpriseSituation;
	}

	@OneToOne(mappedBy = "enterpriseInfo", cascade= { CascadeType.ALL }, fetch = FetchType.EAGER)
	public EnterpriseAcademiaCoop getEnterpriseAcademiaCoop() {
		return enterpriseAcademiaCoop;
	}

	public void setEnterpriseAcademiaCoop(EnterpriseAcademiaCoop enterpriseAcademiaCoop) {
		this.enterpriseAcademiaCoop = enterpriseAcademiaCoop;
	}

	

}
