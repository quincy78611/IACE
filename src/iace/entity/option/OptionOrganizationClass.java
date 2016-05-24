package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_ORG_CLASS")
public class OptionOrganizationClass extends BaseOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2625720771332677857L;

	public OptionOrganizationClass() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "\"OptionOrganizationClass\" : {" + super.toString() + "}";
	}
	
	

}
