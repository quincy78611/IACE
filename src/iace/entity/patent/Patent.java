package iace.entity.patent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import core.util.ValidateUtil;
import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionTrl;

@Entity
@Table(name = "PATENT")
public class Patent extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = -148218196320736494L;

	private long id;
	private String name;
	private String assignee;
	private String invertor;
	private OptionCountry country;
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
//	private String importantPictureCode;
	private transient byte[] importantPatentPicture;
//	private transient String importantPatentPictureExtension;
	private TechField techField;
	private String usage;
	private OptionTrl trl;
	private String trlDesc;

	private int clickNum;
	
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

	@Column(name = "NAME", length = 300, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ASSIGNEE", length = 500, nullable = false)
	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	@Transient
	public String getAssigneeShort() {
		String[] assignees = StringUtils.split(this.assignee, "|");
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < Math.min(2, assignees.length); i++) {
			sb.append(assignees[i]).append("\r\n");
		}
		if (assignees.length > 2) {
			sb.append(" ...");
		}
		return sb.toString();
	}

	@Column(name = "INVENTOR", length = 500, nullable = false)
	public String getInvertor() {
		return invertor;
	}

	public void setInvertor(String invertor) {
		this.invertor = invertor;
	}
	
	@ManyToOne
	@JoinColumn(name="COUNTRY", referencedColumnName= "CODE")
	public OptionCountry getCountry() {
		return country;
	}

	public void setCountry(OptionCountry optionCountry) {
		this.country = optionCountry;
	}
	
	public void setCountryByCode(String code) {
		OptionCountry opt = new OptionCountry();
		opt.setCode(code);
		setCountry(opt);
	}

	@Column(name = "APPLICATION_NO", length = 100, nullable = false)
	public String getAppliactionNo() {
		return appliactionNo;
	}

	public void setAppliactionNo(String appliactionNo) {
		this.appliactionNo = appliactionNo;
	}

	@Column(name = "APPLICATION_DATE", nullable = false)
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

	@Column(name = "CATEGORY", length = 100, nullable = false)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "PATENT_STATUS", length = 500, nullable = false)
	public String getPatentStatus() {
		return patentStatus;
	}

	public void setPatentStatus(String patentStatus) {
		this.patentStatus = patentStatus;
	}

	@Column(name = "FAMILY_NO", length = 2000, nullable = false)
	public String getFamilyNo() {
		return familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	@Column(name = "IPC", length = 100, nullable = false)
	public String getIpc() {
		return ipc;
	}

	public void setIpc(String ipc) {
		this.ipc = ipc;
	}

	// @Column(name = "TECH_ABSTRACT", columnDefinition = "CLOB")
	@Column(name = "TECH_ABSTRACT", nullable = false)
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
	
//	@Column(name = "IMPORTANT_PICTURE_CODE", length = 100)
//	public String getImportantPictureCode() {
//		return importantPictureCode;
//	}
//
//	public void setImportantPictureCode(String importantPictureCode) {
//		this.importantPictureCode = importantPictureCode;
//	}
	
	@Transient
	public String getPatentPictureLink() {
		String link = "http://www.thomsoninnovation.com/tip-innovation/zoomImage.do?documentNumber=%s&pageIndex=0&daysValid=0&clipWidth=1080&clipHeight=0&locale=en_US";
		if (StringUtils.isNoneBlank(this.publicationNo)) {
			link = String.format(link, this.publicationNo);
		} else if (StringUtils.isNoneBlank(this.openNo)){
			link = String.format(link, this.openNo);
		} else {
			link ="";
		}
		return link;
	}

	@Transient
	public byte[] getImportantPatentPicture() {
		return importantPatentPicture;
	}

	public void setImportantPatentPicture(byte[] importantPatentPicture) {
		this.importantPatentPicture = importantPatentPicture;
	}
		
	@Transient
	public String getBase64PatentPicture() {
		return Base64.encode(this.importantPatentPicture);
	}

	@Transient
	public String getImportantPatentPictureExtension() {
		return FilenameUtils.getExtension(this.getImportantPicturePath());
	}

//	public void setImportantPatentPictureExtension(String importantPatentPictureExtension) {
//		this.importantPatentPictureExtension = importantPatentPictureExtension;
//	}

	@ManyToOne
	@JoinColumn(name="TECH_FIELD", nullable=false)
	public TechField getTechField() {
		return techField;
	}


	public void setTechField(TechField techField) {
		this.techField = techField;
	}

	@Column(name = "USAGE", length = 500)
	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	@ManyToOne
	@JoinColumn(name="TRL_CODE", referencedColumnName= "CODE")
	public OptionTrl getTrl() {
		return trl;
	}

	public void setTrl(OptionTrl optionTrl) {
		this.trl = optionTrl;
		//this.trlCode = optionTrl.getCode();
	}

	@Column(name = "TRL_DESC", length = 2000)
	public String getTrlDesc() {
		return trlDesc;
	}

	public void setTrlDesc(String trlDesc) {
		this.trlDesc = trlDesc;
	}
	
	@Column(name = "CLICK_NUM")
	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	
	public List<String> validate() {
		List<String> errMsgs = new ArrayList<String>();
		ValidateUtil.notBlankNLength(this.name, 300, "專利資料", errMsgs);
		ValidateUtil.notBlankNLength(this.assignee, 500, "專利權人", errMsgs);
		ValidateUtil.notBlankNLength(this.invertor, 500, "發明人", errMsgs);
//		ValidateUtil.notBlankNLength(this.country, 10, "申請國別", errMsgs);
		ValidateUtil.notBlankNLength(this.appliactionNo, 100, "申請號", errMsgs);	
		ValidateUtil.notNull(this.applicationDate,"申請日", errMsgs);
		ValidateUtil.maxLength(this.openNo, 100, "公開號", errMsgs);
		ValidateUtil.maxLength(this.publicationNo, 100, "公告號", errMsgs);
		ValidateUtil.notBlankNLength(this.category, 100, "專利類別", errMsgs);
		ValidateUtil.notBlankNLength(this.patentStatus, 500, "專利狀態", errMsgs);
		ValidateUtil.notBlankNLength(this.familyNo, 2000, "專利家族", errMsgs);
		ValidateUtil.notBlankNLength(this.ipc, 100, "國際分類號", errMsgs);
		ValidateUtil.notBlank(this.techAbstract, "專利技術摘要", errMsgs);
		ValidateUtil.notNull(this.importantPatentPicture, "重要圖式", errMsgs);
		ValidateUtil.maxLength(this.importantPicturePath, 200, "重要圖式路徑", errMsgs);
//		ValidateUtil.notBlankNLength(this.importantPictureCode, 100, "重要圖式代碼", errMsgs);
		ValidateUtil.notBlankNLength(this.techField.getName(), 500, "專利技術領域", errMsgs);
		ValidateUtil.maxLength(this.usage, 500, "應用範圍/產業", errMsgs);
		ValidateUtil.maxLength(this.trl.getCode(), 10, "技術發展階段", errMsgs);
		ValidateUtil.maxLength(this.trlDesc, 2000, "技術發展階段說明", errMsgs);		
		return errMsgs;
	}

	@Override
	public String toString() {
		return "Patent [id=" + id + ", name=" + name + ", assignee=" + assignee
				+ ", invertor=" + invertor + ", country=" + country
				+ ", appliactionNo=" + appliactionNo + ", applicationDate="
				+ applicationDate + ", openNo=" + openNo + ", openDate="
				+ openDate + ", publicationNo=" + publicationNo
				+ ", publicationDate=" + publicationDate + ", category="
				+ category + ", patentStatus=" + patentStatus + ", familyNo="
				+ familyNo + ", ipc=" + ipc + ", techAbstract=" + techAbstract
				+ ", importantPicturePath=" + importantPicturePath
				+ ", techField=" + techField + ", usage=" + usage + ", trl="
				+ trl + ", trlDesc=" + trlDesc + "]";
	}

	@Override
	public String toSysLog() {
		String s =
				"ID: {"+this.id+"}, \r\n"+
				"專利名稱: {"+this.name+"}, \r\n"+
				"專利權人: {"+this.assignee+"}, \r\n"+
				"發明人: {"+this.invertor+"}, \r\n"+
				"申請國別: {"+this.country.toSysLog()+"}, \r\n"+
				"申請號: {"+this.appliactionNo+"}, \r\n"+
				"申請日: {"+this.applicationDate+"}, \r\n"+
				"公開號: {"+this.openNo+"}, \r\n"+
				"公開日: {"+this.openDate+"}, \r\n"+
				"公告號: {"+this.publicationNo+"}, \r\n"+
				"公告日: {"+this.publicationDate+"}, \r\n"+
				"專利類別: {"+this.category+"}, \r\n"+
				"專利狀態: {"+this.patentStatus+"}, \r\n"+
				"專利家族: {"+this.familyNo+"}, \r\n"+
				"國際分類號: {"+this.ipc+"}, \r\n"+
				"專利技術摘要: {"+this.techAbstract+"}, \r\n"+
				"專利技術領域: {"+this.techField.getName()+"}, \r\n"+
				"應用範圍/產業: {"+this.usage+"}, \r\n"+
				"技術發展階段: {"+(this.trl == null ? null : this.trl.toSysLog())+"}, \r\n"+
				"技術發展階段說明: {"+this.trlDesc+"}";
		return s;
	}

	@Override
	public String toLunceneContent() {
		String content = 
				this.name + " " + 
				this.appliactionNo + " " +
				this.openNo + " " +
				this.publicationNo + " " + 
				this.familyNo + " " +
				this.ipc + " " +
				this.techAbstract + " " +
				this.usage;
		return content;
	}


}
