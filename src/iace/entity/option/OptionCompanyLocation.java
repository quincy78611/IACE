package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_COM_LOCATION", schema = "IACE_ADMIN")
public class OptionCompanyLocation extends BaseOption {

	private static final long serialVersionUID = 1930251288828278499L;

	@Override
	public String toString() {
		return "\"OptionCompanyLocation\" : {" + super.toString() + "}";
	}










	
	
	


}
