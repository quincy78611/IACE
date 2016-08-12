package iace.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import iace.entity.BaseEntity;

@Entity
@Table(name = "SYS_FUNCTION")
public class SysFunction extends BaseEntity {

	private static final long serialVersionUID = -8689976494867826443L;

	private long id;
	private String displayName;
	private String namespace;
	private String actionName1;
	private String actionName2;
	private String actionName3;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_SYS_FUNCTION_ID")
	@SequenceGenerator(name = "SEQUENCE_SYS_FUNCTION_ID", sequenceName = "SEQUENCE_SYS_FUNCTION_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "DISPLAY_NAME")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "NAMESPACE")
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	@Column(name = "ACTION_NAME1")
	public String getActionName1() {
		return actionName1;
	}

	public void setActionName1(String actionName) {
		this.actionName1 = actionName;
	}

	@Column(name = "ACTION_NAME2")
	public String getActionName2() {
		return actionName2;
	}

	public void setActionName2(String actionName2) {
		this.actionName2 = actionName2;
	}

	@Column(name = "ACTION_NAME3")
	public String getActionName3() {
		return actionName3;
	}

	public void setActionName3(String actionName3) {
		this.actionName3 = actionName3;
	}

	public boolean hasActionName(String actionName) {
		boolean res = 
				StringUtils.equals(this.actionName1, actionName) ||
				StringUtils.equals(this.actionName2, actionName) ||
				StringUtils.equals(this.actionName3, actionName);
		return res;
	}
}
