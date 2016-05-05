package iace.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_INDUSTRY_CLASS", schema = "IACE_ADMIN")
public class OptionIndustryClass extends BaseOption {

	private static final long serialVersionUID = -1672186825816312803L;

	public OptionIndustryClass() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "\"OptionIndustryClass\" : {" + super.toString() + "}";
	}

	
}
