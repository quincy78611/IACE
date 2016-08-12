package iace.entity.sys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import iace.entity.BaseEntity;

@Entity
@Table(name = "SYS_ROLE")
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = -868106738545265052L;
	private long id;
	private String name;
	private List<SysAuth> authList = new ArrayList<SysAuth>();
	
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

	@OneToMany(mappedBy="sysRole", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)	
	public List<SysAuth> getAuthList() {
		return authList;
	}

	public void setAuthList(List<SysAuth> authList) {
		this.authList = authList;
	}




}
