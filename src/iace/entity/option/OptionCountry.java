package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_COUNTRY", schema = "IACE_ADMIN")
public class OptionCountry extends BaseOption {

	private static final long serialVersionUID = 5058595760923176194L;

	@Override
	public String toString() {
		return "\"OptionCountry\" : {" + super.toString() + "}";
	}


}
