package iace.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 5908489787919929004L;
	public static final String valid = "T";
	public static final String inValid = "F";

	private String isValid;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	private Timestamp ver;
	
	public BaseEntity() {}
	
	public BaseEntity(BaseEntity entity) {
		this.isValid = entity.isValid;
		this.createTime = entity.createTime;
		this.createUser = entity.createUser;
		this.updateTime = entity.updateTime;
		this.updateUser = entity.updateUser;
		this.ver = entity.ver;
	}

	@Column(name = "IS_VALID", length=1)
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Column(name = "CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_USER", length=100)	
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_USER", length=100)
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Version
	@Column(name = "VER", nullable = false)
	public Timestamp getVer() {
		return ver;
	}

	public void setVer(Timestamp ver) {
		this.ver = ver;
	}

	@Override
	public String toString() {
		return "BaseEntity [isValid=" + isValid + ", createTime=" + createTime + ", createUser=" + createUser + ", updateTime=" + updateTime + ", updateUser=" + updateUser + ", ver=" + ver + "]";
	}

	public void create() {
		Date now = new Date();
		this.createTime = now;
		this.updateTime = now;
		//this.ver = new Timestamp(now.getTime());
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
	
	
	

}
