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

import iace.entity.BaseEntity;

@Entity
@Table(name = "TALENTED_PEOPLE_MAIN_PROJECT")
public class TalentedPeopleMainProject extends BaseEntity {

	private static final long serialVersionUID = -6738846194198008518L;
	
	private long id;
	private TalentedPeople talentedPeople;
	private String name;
	private String coopComName;
	private int yearStart;
	private int monthStart;
	private int yearEnd;
	private int monthEnd;

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

	@Column(name = "COOP_COM_NAME", length = 500)
	public String getCoopComName() {
		return coopComName;
	}

	public void setCoopComName(String coopComName) {
		this.coopComName = coopComName;
	}

	@Column(name = "YEAR_S", length = 4)
	public int getYearStart() {
		return yearStart;
	}

	public void setYearStart(int yearStart) {
		this.yearStart = yearStart;
	}

	@Column(name = "MONTH_S", length = 2)
	public int getMonthStart() {
		return monthStart;
	}

	public void setMonthStart(int monthStart) {
		this.monthStart = monthStart;
	}

	@Column(name = "YEAR_E", length = 4)
	public int getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(int yearEnd) {
		this.yearEnd = yearEnd;
	}

	@Column(name = "MONTH_E", length = 2)
	public int getMonthEnd() {
		return monthEnd;
	}

	public void setMonthEnd(int monthEnd) {
		this.monthEnd = monthEnd;
	}

}
