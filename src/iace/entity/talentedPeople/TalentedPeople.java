package iace.entity.talentedPeople;

import java.io.File;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import core.util.AESEncrypter;
import iace.entity.BaseEntity;
import iace.entity.option.OptionGrbDomain;

@Entity
@Table(name = "TALENTED_PEOPLE")
public class TalentedPeople extends BaseEntity {

	private static final long serialVersionUID = -769195556580032302L;

	private long id;
	private String nameCh;
	private String nameEn;
	private String gender;
	private int expYear;
	private String tel;
	private String email;
	private String workOrg;
	private String job;
	private String url;
	private String otherSpecialty;
	private List<OptionGrbDomain> domains = new ArrayList<OptionGrbDomain>();

	private byte[] headShot;
	private File uploadheadShot;
	private String uploadheadShotContentType;
	private String uploadheadShotFileName;

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
	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
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
	
	@Column(name = "OTHER_SPECIALTY", length = 1000)
	public String getOtherSpecialty() {
		return otherSpecialty;
	}

	public void setOtherSpecialty(String otherSpecialty) {
		this.otherSpecialty = otherSpecialty;
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
}
