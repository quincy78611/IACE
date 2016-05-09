package iace.entity.option;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import iace.entity.BaseEntity;

@MappedSuperclass
public abstract class BaseOption  extends BaseEntity {

	private static final long serialVersionUID = -6693566207871210805L;

	private long id;
	private String name;
	private String code;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_OPPTION_ID")
	@SequenceGenerator(name = "SEQUENCE_OPPTION_ID", sequenceName = "SEQUENCE_OPPTION_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long optionCompanyLocationId) {
		this.id = optionCompanyLocationId;
	}
	
	@Column(name = "NAME", length = 500)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 10)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "\"id\"=\"" + id + "\", \"name\"=\"" + name + "\", \"code\"=\"" + code + "\",  " + super.toString();
	}










	
	

}
