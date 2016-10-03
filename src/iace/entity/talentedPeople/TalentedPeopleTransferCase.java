package iace.entity.talentedPeople;

import java.sql.Date;

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
@Table(name = "TALENTED_PEOPLE_TRANSFER_CASE")
public class TalentedPeopleTransferCase extends BaseEntity {

	private static final long serialVersionUID = 2349692596426033716L;
	
	private long id;
	private TalentedPeople talentedPeople;
	private String applyField;
	private String targetOrg;
	private int yearStart;
	private int monthStart;
	private Integer yearEnd;
	private Integer monthEnd;
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

	@Column(name = "APPLY_FIELD", length = 500)
	public String getApplyField() {
		return applyField;
	}

	public void setApplyField(String applyField) {
		this.applyField = applyField;
	}

	@Column(name = "TARGET_ORG", length = 500)
	public String getTargetOrg() {
		return targetOrg;
	}

	public void setTargetOrg(String targetOrg) {
		this.targetOrg = targetOrg;
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
	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
	}

	@Column(name = "MONTH_E", length = 2)
	public Integer getMonthEnd() {
		return monthEnd;
	}

	public void setMonthEnd(Integer monthEnd) {
		this.monthEnd = monthEnd;
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

}
