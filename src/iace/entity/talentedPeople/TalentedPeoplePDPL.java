package iace.entity.talentedPeople;

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

import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;

@Entity
@Table(name = "TALENTED_PEOPLE_PDPL")
public class TalentedPeoplePDPL extends BaseEntity {
	private static final long serialVersionUID = 3672368047137577096L;
	
	private long id;
	private transient TalentedPeople talentedPeople;
	private Boolean agreePDPL;
	private String ip;

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

	@Column(name = "AGREE_PDPL")
	@Type(type="true_false")
	public Boolean getAgreePDPL() {
		return agreePDPL;
	}

	public void setAgreePDPL(Boolean agreePDPL) {
		this.agreePDPL = agreePDPL;
	}

	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
