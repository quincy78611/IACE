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

import org.apache.commons.lang3.StringUtils;

import iace.entity.BaseEntity;

@Entity
@Table(name = "SYS_USER")
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = -696213086687157723L;

	private long id;
	private String account;
	private String password;
	private String name;
	private SysRole sysRole;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_SYS_USER_ID")
	@SequenceGenerator(name = "SEQUENCE_SYS_USER_ID", sequenceName = "SEQUENCE_SYS_USER_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long userId) {
		this.id = userId;
	}

	@Column(name = "ACCOUNT", length = 100)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "PASSWORD", length = 100)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SYS_ROLE_ID", referencedColumnName= "ID")
	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public boolean hasAuth(String namespace, String actionName) {
		for (SysAuth auth : this.sysRole.getAuthList()) {
			SysFunction action = auth.getSysFunction();
			if (auth.getEnable() &&
				StringUtils.equals(action.getNamespace(), namespace) && 
				action.hasActionName(actionName)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasNamespace(String namespace) {
		for (SysAuth auth : this.sysRole.getAuthList()) {
			SysFunction action = auth.getSysFunction();
			if (auth.getEnable() && StringUtils.equals(action.getNamespace(), namespace)) {
				return true;
			}			
		}
		return false;
	}
	

}