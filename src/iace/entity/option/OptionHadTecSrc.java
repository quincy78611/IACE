package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OPT_HAD_TEC_SRC")
public class OptionHadTecSrc extends BaseOption {

	private static final long serialVersionUID = -5597275503218970987L;

	@Override
	public String toString() {
		return "\"OptionHadTecSrc\" : {" + super.toString() + "}";
	}
	
	

}
