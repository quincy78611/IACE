package iace.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_CONSULT", schema = "IACE_ADMIN")
public class OptionConsult extends BaseOption {

	private static final long serialVersionUID = -7712023909321390653L;

	@Override
	public String toString() {
		return "\"OptionConsult\" : {" + super.toString() + "}";
	}


	
	
	
	


}
