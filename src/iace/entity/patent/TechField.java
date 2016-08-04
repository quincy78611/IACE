package iace.entity.patent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;

@Entity
@Table(name = "TECH_FIELD")
public class TechField extends BaseEntity {

	private static final long serialVersionUID = -4341693809181367264L;

	private long id;
	private String name;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_OPTION_ID")
	@SequenceGenerator(name = "SEQUENCE_OPTION_ID", sequenceName = "SEQUENCE_OPTION_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 500, nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	@Override
	public String toString() {
		return "TechField [id=" + id + ", name=" + name + "]";
	}
	
}
