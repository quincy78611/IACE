package iace.entity.option;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "OPT_GRB_DOMAIN")
public class OptionGrbDomain extends BaseOption {

	private static final long serialVersionUID = 8701955362454487326L;

	private Boolean forResearchPlan;

	@Column(name = "FOR_RESEARCH_PLAN")
	@Type(type = "true_false")
	public Boolean getForResearchPlan() {
		return forResearchPlan;
	}

	public void setForResearchPlan(Boolean forResearchPlan) {
		this.forResearchPlan = forResearchPlan;
	}
	
	



	
	

}
