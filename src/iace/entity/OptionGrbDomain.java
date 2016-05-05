package iace.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_GRB_DOMAIN", schema = "IACE_ADMIN")
public class OptionGrbDomain extends BaseOption {

	private static final long serialVersionUID = 8701955362454487326L;

	@Override
	public String toString() {
		return "\"OptionGrbDomain\" : {" + super.toString() + "}";
	}



	
	

}
