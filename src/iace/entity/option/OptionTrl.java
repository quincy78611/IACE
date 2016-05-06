package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_TRL", schema = "IACE_ADMIN")
public class OptionTrl extends BaseOption {

	private static final long serialVersionUID = 3688977033226616304L;

	@Override
	public String toString() {
		return "\"OptionTrl\" : {" + super.toString() + "}";
	}


	
	
}
