package iace.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TECH_FIELD", schema = "IACE_ADMIN")
public class TechField extends BaseEntity {

	private static final long serialVersionUID = -4341693809181367264L;

	private long id;
	private String name;
	
	private List<Patent> patentList;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_OPPTION_ID")
	@SequenceGenerator(name = "SEQUENCE_OPPTION_ID", sequenceName = "SEQUENCE_OPPTION_ID", allocationSize = 1, initialValue = 1)
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

	@OneToMany(mappedBy="techField")
	public List<Patent> getPatentList() {
		return patentList;
	}

	public void setPatentList(List<Patent> patentList) {
		this.patentList = patentList;
	}

	@Override
	public String toString() {
		return "TechField [id=" + id + ", name=" + name + "]";
	}
	
	

}
