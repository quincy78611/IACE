package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_SUBJECT")
public class OptionSubject extends BaseOption {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8466202865101688332L;

	@Override
	public String toString() {
		return "\"OptionSubject\" : {" + super.toString() + "}";
	}

}
