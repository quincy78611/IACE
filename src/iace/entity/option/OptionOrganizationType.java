package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_ORG_TYPE", schema = "IACE_ADMIN")
public class OptionOrganizationType extends BaseOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6159108709167370748L;

	public OptionOrganizationType() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "\"OptionOrganizationType\" : {" + super.toString() + "}";
	}

	
}
