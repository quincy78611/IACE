package iace.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import iace.entity.BaseEntity;
import iace.entity.option.OptionSysAction;
import iace.entity.option.OptionSysNamespace;

@Entity
@Table(name = "SYS_LOG")
public class SysLog extends BaseEntity {

	private static final long serialVersionUID = -3869735672852230110L;

	private boolean enableLog;
	
	private long id;
	private OptionSysNamespace optionSysNamespace;
	private OptionSysAction optionSysAction;
	private String ip;
	private SysUser sysUser;

	private String tableName;
	private String before;
	private String after;
	private transient BaseEntity beforeEntity;
	private transient BaseEntity afterEntity;
	
	@Transient
	public boolean isEnableLog() {
		return enableLog;
	}
	
	public void setEnableLog(boolean enableLog) {
		this.enableLog = enableLog;
	}

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_SYS_LOG_ID")
	@SequenceGenerator(name = "SEQUENCE_SYS_LOG_ID", sequenceName = "SEQUENCE_SYS_LOG_ID", allocationSize = 1, initialValue = 1)	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="OPT_SYS_NAMESPACE_ID")
	public OptionSysNamespace getOptionSysNamespace() {
		return optionSysNamespace;
	}

	public void setOptionSysNamespace(OptionSysNamespace optionSysNamespace) {
		this.optionSysNamespace = optionSysNamespace;
	}
	
	@ManyToOne
	@JoinColumn(name="OPT_SYS_ACTION_ID")
	public OptionSysAction getOptionSysAction() {
		return optionSysAction;
	}

	public void setOptionSysAction(OptionSysAction optionSysAction) {
		this.optionSysAction = optionSysAction;
	}

	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@ManyToOne
	@JoinColumn(name="SYS_USER_ID")
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "BEFORE")
	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	@Column(name = "AFTER")
	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	@Transient
	public BaseEntity getBeforeEntity() {
		return beforeEntity;
	}

	public void setBeforeEntity(BaseEntity beforeEntity) {
		this.beforeEntity = beforeEntity;
		if (this.beforeEntity != null) {
			Table table = this.beforeEntity.getClass().getAnnotation(Table.class);
			this.tableName = table.name();
			this.before = this.beforeEntity.toSysLog();
			this.enableLog = true;
		}
	}

	@Transient
	public BaseEntity getAfterEntity() {
		return afterEntity;
	}

	public void setAfterEntity(BaseEntity afterEntity) {
		this.afterEntity = afterEntity;
		if (this.afterEntity != null) {
			Table table = this.afterEntity.getClass().getAnnotation(Table.class);
			this.tableName = table.name();
			this.after = this.afterEntity.toSysLog();
			this.enableLog = true;
		}
	}
	
	@Transient
	public String[] getBeforeStrings() {
		return this.before.split(", \r\n");
	}
	
	@Transient
	public String[] getAfterStrings() {
		return this.after.split(", \r\n");
	}

}
