package iace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "R_D_RESULT", schema = "IACE_ADMIN")
public class RnDResult extends BaseEntity {

	private static final long serialVersionUID = 1695680251829946337L;

	private long id;
	//private long researchPlanId;
	private String name;
	private String descriptoin;
	private String trlCode;
	private String trlDesc;
	
	private ResearchPlan researchPlan;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_R_D_RESULT_ID")
	@SequenceGenerator(name = "SEQUENCE_R_D_RESULT_ID", sequenceName = "SEQUENCE_R_D_RESULT_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	@Column(name = "RESEARCH_PLAN_ID", length = 19, nullable = false)
//	public long getResearchPlanId() {
//		return researchPlanId;
//	}
//
//	public void setResearchPlanId(long researchPlanId) {
//		this.researchPlanId = researchPlanId;
//	}

	@Column(name = "NAME", length = 2000 )
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	@Lob
	public String getDescriptoin() {
		return descriptoin;
	}

	public void setDescriptoin(String descriptoin) {
		this.descriptoin = descriptoin;
	}

	@Column(name = "TRL_CODE", length = 10)
	public String getTrlCode() {
		return trlCode;
	}

	public void setTrlCode(String trlCode) {
		this.trlCode = trlCode;
	}

	@Column(name = "TRL_DESC")
	@Lob
	public String getTrlDesc() {
		return trlDesc;
	}

	public void setTrlDesc(String trlDesc) {
		this.trlDesc = trlDesc;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="RESEARCH_PLAN_ID", nullable=false, updatable=false)
	public ResearchPlan getResearchPlan() {
		return researchPlan;
	}

	public void setResearchPlan(ResearchPlan researchPlan) {
		this.researchPlan = researchPlan;
	}

	
	
}
