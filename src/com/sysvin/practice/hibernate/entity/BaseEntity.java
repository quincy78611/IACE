package com.sysvin.practice.hibernate.entity;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -2668993083430118513L;
		
	private Date createTime;
	private Date updateTime;
	private Timestamp ver;
	private String isValid;
	
	public static final String valid = "T";
	public static final String inValid = "F";
	
	public BaseEntity() {}
	
	public BaseEntity(BaseEntity e) {
		this.createTime = e.createTime;
		this.updateTime = e.updateTime;
		this.ver = e.ver;
		this.isValid = e.isValid;
	}

	public void create() {
		Date now = new Date();
		this.createTime = now;
		this.updateTime = now;
		this.ver = new Timestamp(now.getTime());
		this.isValid = valid;
	}
	
	public void update() {
		Date now = new Date();
		this.updateTime = now;
		// shouldn't change ver's value due to "Optimistic locking" issue
		// this.ver = new Timestamp(now.getTime());
	}
	
	public void delete() {
		update();
		this.isValid = inValid;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Version
	@Column(name = "VER", nullable = false)
	public Timestamp getVer() {
		return ver;
	}

	public void setVer(Timestamp ver) {
		this.ver = ver;
	}

	@Column(name = "IS_VALID", nullable = false, length = 1)
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		if (isValid.equals(valid) || isValid.equals(inValid)) {
			this.isValid = isValid;		
		} else {
			String msg = String.format("Value can only be %s or %s!", valid, inValid);
			throw new IllegalArgumentException(msg);
		}	
	}

	@Override
	public String toString() {
		return "BaseEntity [createTime=" + createTime + ", updateTime=" + updateTime + ", ver=" + ver + ", isValid="
				+ isValid + "]";
	}
	
	
}
