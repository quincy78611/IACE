package iace.entity.consulting;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;
import iace.entity.option.OptionConsult;
import iace.entity.option.OptionIndustry;
import iace.entity.option.OptionOrganizationType;

@Entity
@Table(name = "CONSULTING")
public class Consulting extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7748438753574099348L;

	private long id;
	private String name;
	private String organization;
	private OptionOrganizationType optionOrganizationType;
	private String orgTypeOther;
	private OptionConsult optionConsult;
	private String consultTypeOther;
	private OptionIndustry optionIndustry;
	private String industryOther;
	private String phone;
	private String email;
	private String content;
	private String approval;
	private Date consultDate;
	private boolean beenHandled;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CONSULTING_ID")
	@SequenceGenerator(name = "SEQUENCE_CONSULTING_ID", sequenceName = "SEQUENCE_CONSULTING_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORGANIZATION", length = 500)
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@ManyToOne
	@JoinColumn(name="ORG_TYPE_CODE", referencedColumnName= "CODE")
	public OptionOrganizationType getOptionOrganizationType() {
		return optionOrganizationType;
	}

	public void setOptionOrganizationType(OptionOrganizationType optionOrganizationType) {
		this.optionOrganizationType = optionOrganizationType;
	}

	@Column(name = "ORG_TYPE_OTHER", length = 500)
	public String getOrgTypeOther() {
		return orgTypeOther;
	}

	public void setOrgTypeOther(String orgTypeOther) {
		this.orgTypeOther = orgTypeOther;
	}

	@ManyToOne
	@JoinColumn(name="CONSULT_TYPE_CODE", referencedColumnName= "CODE")
	public OptionConsult getOptionConsult() {
		return optionConsult;
	}

	public void setOptionConsult(OptionConsult optionConsult) {
		this.optionConsult = optionConsult;
	}

	@Column(name = "CONSULT_TYPE_OTHER", length = 500)
	public String getConsultTypeOther() {
		return consultTypeOther;
	}

	public void setConsultTypeOther(String consultTypeOther) {
		this.consultTypeOther = consultTypeOther;
	}

	@ManyToOne
	@JoinColumn(name="INDUSTRY_CODE", referencedColumnName= "CODE")
	public OptionIndustry getOptionIndustry() {
		return optionIndustry;
	}

	public void setOptionIndustry(OptionIndustry optionIndustry) {
		this.optionIndustry = optionIndustry;
	}

	@Column(name = "INDUSTRY_OTHER", length = 500)
	public String getIndustryOther() {
		return industryOther;
	}

	public void setIndustryOther(String industryOther) {
		this.industryOther = industryOther;
	}

	@Column(name = "PHONE", length = 100)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CONTENT")
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "APPROVAL", length = 1)
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		if (approval.equalsIgnoreCase("true")) {
			this.approval = BaseEntity.TRUE;
		} else if (approval.equalsIgnoreCase("false")) {
			this.approval = BaseEntity.FALSE;
		} else {
			this.approval = approval;
		}		
	}
	
	@Transient
	public boolean getApprovalCheckBox() {
		return this.approval.equals(BaseEntity.TRUE) ? true : false;
	}
	
	public void setApprovalCheckBox(boolean isApprove) {
		this.approval = isApprove ? BaseEntity.TRUE : BaseEntity.FALSE;
	}

	@Column(name = "CONSULT_DATE")
	@Temporal(TemporalType.DATE)
	public Date getConsultDate() {
		return consultDate;
	}

	public void setConsultDate(Date consultDate) {
		this.consultDate = consultDate;
	}
	
	@Column(name = "BEEN_HANDLED")
	@Type(type="true_false")
	public boolean getBeenHandled() {
		return beenHandled;
	}

	public void setBeenHandled(boolean beenHandled) {
		this.beenHandled = beenHandled;
	}

	@Deprecated
	@Override
	public String toSysLog() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID: {"+this.id+"}, \r\n");
		sb.append("姓名: {"+this.name+"}, \r\n");
		sb.append("單位名稱: {"+this.organization+"}, \r\n");
		sb.append("單位類型: {"+this.optionOrganizationType.toSysLog()+"}, \r\n");
		sb.append("單位類型(其他): {"+this.orgTypeOther+"}, \r\n");
		sb.append("諮詢類型: {"+this.optionConsult.toSysLog()+"}, \r\n");
		sb.append("諮詢類型(其他): {"+this.consultTypeOther+"}, \r\n");
		sb.append("產業/領域別: {"+this.optionIndustry.toSysLog()+"}, \r\n");
		sb.append("產業/領域別(其他): {"+this.getIndustryOther()+"}, \r\n");
		sb.append("聯絡電話: {"+this.phone+"}, \r\n");
		sb.append("E-MAIL: {"+this.email+"}, \r\n");
		sb.append("諮詢日期: {"+this.consultDate+"}, \r\n");
		sb.append("內容說明: {"+this.content+"}, \r\n");
		sb.append("同意: {"+this.approval+"}, \r\n");
		
		return sb.toString();
	}
	
	

}
