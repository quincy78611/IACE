package iace.entity.enterpriseNeed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import iace.entity.BaseEntity;

@Entity
@Table(name = "ENTERPRISE_ACADEMIA_COOP")
public class EnterpriseAcademiaCoop extends BaseEntity {

	private static final long serialVersionUID = 5489013613912410384L;

	private long id;
	private EnterpriseInfo enterpriseInfo;
	private String coopSchool;
	private String currentCoopProjectTopic;
	private String wantedCoopSchoolTopic;
	private String suggestion;

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

	@Column(name = "COOP_SCHOOL", length = 1000)
	public String getCoopSchool() {
		return coopSchool;
	}

	public void setCoopSchool(String coopSchool) {
		this.coopSchool = coopSchool;
	}

	@Transient
	public boolean getHasCurrentCoopProject() {
		return this.currentCoopProjectTopic != null;
	}
	
	public void setHasCurrentCoopProject(boolean has) {
		if (has == false) {
			this.currentCoopProjectTopic = null;
		}
	}
	
	@Column(name = "CURRENT_COOP_PROJECT_TOPIC", length = 1000)
	public String getCurrentCoopProjectTopic() {
		return currentCoopProjectTopic;
	}

	public void setCurrentCoopProjectTopic(String currentCoopProjectTopic) {
		this.currentCoopProjectTopic = currentCoopProjectTopic;
	}
	
	@Transient
	public boolean getHasWantedCoopSchool() {
		return this.wantedCoopSchoolTopic != null;
	}
	
	public void setHasWantedCoopSchool(boolean has) {
		if (has == false) {
			this.wantedCoopSchoolTopic = null;
		}
	}
	

	@Column(name = "WANTED_COOP_SCHOOL_TOPIC", length = 1000)
	public String getWantedCoopSchoolTopic() {
		return wantedCoopSchoolTopic;
	}

	public void setWantedCoopSchoolTopic(String wantedCoopSchoolTopic) {
		this.wantedCoopSchoolTopic = wantedCoopSchoolTopic;
	}

	@Column(name = "SUGGESTION", length = 1000)
	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

}
