package iace.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;

@Entity
@Table(name = "SYS_AUTH")
public class SysAuth extends BaseEntity {

	private static final long serialVersionUID = -6648651525963704721L;

	private long id;
	private SysRole sysRole;
	private SysFunction sysFunction;
	private boolean enable;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_SYS_AUTH_ID")
	@SequenceGenerator(name = "SEQUENCE_SYS_AUTH_ID", sequenceName = "SEQUENCE_SYS_AUTH_ID", allocationSize = 1, initialValue = 1)	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYS_ROLE_ID", nullable = false, updatable = false)	
	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SYS_FUNCTION_ID", referencedColumnName= "ID")
	public SysFunction getSysFunction() {
		return sysFunction;
	}

	public void setSysFunction(SysFunction sysFunction) {
		this.sysFunction = sysFunction;
	}

	@Column(name = "ENABLE")
	@Type(type = "true_false")
	public boolean getEnable() {
		return enable;
	}



	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
