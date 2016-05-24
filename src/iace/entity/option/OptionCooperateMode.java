package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_COOPERATE_MODE")
public class OptionCooperateMode extends BaseOption {

	private static final long serialVersionUID = -5280552474242370699L;

	@Override
	public String toString() {
		return "\"OptionCooperateMode\" : {" + super.toString() + "}";
	}


	
	
}
