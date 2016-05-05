package iace.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.entity.BaseEntity;

@Entity
@Table(name = "PATENT", schema = "IACE_ADMIN")
public class Patent extends BaseEntity {

	private static final long serialVersionUID = -148218196320736494L;

	private long id;
	private String name;
	private String assignee;
	private String invertor;
	private String country;
	private String appliactionNo;
	private Date applicationDate;
	private String openNo;
	private Date openDate;
	private String publicationNo;
	private Date publicationDate;
	private String category;
	private String patentStatus;
	private String familyNo;
	private String ipc;
	private String techAbstract;
	private String importantPicturePath;
	private String techField;
	private String usage;
	private String trlCode;
	private String trlDesc;
	
	private OptionTrl trl;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PATENT_ID")
	@SequenceGenerator(name = "SEQUENCE_PATENT_ID", sequenceName = "SEQUENCE_PATENT_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 300)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ASSIGNEE", length = 500)
	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@Column(name = "INVENTOR", length = 500)
	public String getInvertor() {
		return invertor;
	}

	public void setInvertor(String invertor) {
		this.invertor = invertor;
	}

	@Column(name = "COUNTRY", length = 100)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "APPLICATION_NO", length = 100)
	public String getAppliactionNo() {
		return appliactionNo;
	}

	public void setAppliactionNo(String appliactionNo) {
		this.appliactionNo = appliactionNo;
	}

	@Column(name = "APPLICATION_DATE")
	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Column(name = "OPEN_NO", length = 100)
	public String getOpenNo() {
		return openNo;
	}

	public void setOpenNo(String openNo) {
		this.openNo = openNo;
	}

	@Column(name = "OPEN_DATE")
	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	@Column(name = "PUBLICATION_NO", length = 100)
	public String getPublicationNo() {
		return publicationNo;
	}

	public void setPublicationNo(String publicationNo) {
		this.publicationNo = publicationNo;
	}

	@Column(name = "PUBLICATION_DATE")
	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Column(name = "CATEGORY", length = 100)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "PATENT_STATUS", length = 500)
	public String getPatentStatus() {
		return patentStatus;
	}

	public void setPatentStatus(String patentStatus) {
		this.patentStatus = patentStatus;
	}

	@Column(name = "FAMILY_NO", length = 2000)
	public String getFamilyNo() {
		return familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	@Column(name = "IPC", length = 100)
	public String getIpc() {
		return ipc;
	}

	public void setIpc(String ipc) {
		this.ipc = ipc;
	}

	// @Column(name = "TECH_ABSTRACT", columnDefinition = "CLOB")
	@Column(name = "TECH_ABSTRACT")
	@Lob
	public String getTechAbstract() {
		return techAbstract;
	}

	public void setTechAbstract(String techAbstract) {
		this.techAbstract = techAbstract;
	}

	@Column(name = "IMPORTANT_PICTURE_PATH", length = 200)
	public String getImportantPicturePath() {
		return importantPicturePath;
	}

	public void setImportantPicturePath(String importantPicturePath) {
		this.importantPicturePath = importantPicturePath;
	}

	@Column(name = "TECH_FIELD", length = 500)
	public String getTechField() {
		return techField;
	}

	public void setTechField(String techField) {
		this.techField = techField;
	}

	@Column(name = "USAGE", length = 500)
	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	@Column(name = "TRL_CODE", length = 10)
	public String getTrlCode() {
		return trlCode;
	}

	public void setTrlCode(String trlCode) {
		this.trlCode = trlCode;
		this.trl = null;
	}

	@Transient
	public OptionTrl getTrl() {
		return trl;
	}

	public void setTrl(OptionTrl optionTrl) {
		this.trl = optionTrl;
		this.trlCode = optionTrl.getCode();
	}

	@Column(name = "TRL_DESC", length = 2000)
	public String getTrlDesc() {
		return trlDesc;
	}

	public void setTrlDesc(String trlDesc) {
		this.trlDesc = trlDesc;
	}

	@Override
	public String toString() {
		return "\"Patent\" : {\"id\"=\"" + id + "\", \"name\"=\"" + name + "\", \"assignee\"=\"" + assignee
				+ "\", \"invertor\"=\"" + invertor + "\", \"country\"=\"" + country + "\", \"appliactionNo\"=\""
				+ appliactionNo + "\", \"applicationDate\"=\"" + applicationDate + "\", \"openNo\"=\"" + openNo
				+ "\", \"openDate\"=\"" + openDate + "\", \"publicationNo\"=\"" + publicationNo
				+ "\", \"publicationDate\"=\"" + publicationDate + "\", \"category\"=\"" + category
				+ "\", \"patentStatus\"=\"" + patentStatus + "\", \"familyNo\"=\"" + familyNo + "\", \"ipc\"=\"" + ipc
				+ "\", \"techAbstract\"=\"" + techAbstract + "\", \"importantPicturePath\"=\"" + importantPicturePath
				+ "\", \"techField\"=\"" + techField + "\", \"usage\"=\"" + usage + "\", \"trlCode\"=\"" + trlCode
				+ "\", \"trlDesc\"=\"" + trlDesc + "\",  " + super.toString() + "}";
	}

}
