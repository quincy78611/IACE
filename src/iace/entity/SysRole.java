package iace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_ROLE")
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = -868106738545265052L;
	private long id;
	private String name;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_SYS_ROLE_ID")
	@SequenceGenerator(name = "SEQUENCE_SYS_ROLE_ID", sequenceName = "SEQUENCE_SYS_ROLE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long roleId) {
		this.id = roleId;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\"SysRole\" : {\"id\"=\"" + id + "\", \"name\"=\"" + name + "\",  " + super.toString() + "}";
	}



}
