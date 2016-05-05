package com.sysvin.practice.hibernate.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity {
	private static final long serialVersionUID = 7217971038081534930L;

	private String userUuid;
	private String userId;
	private String password;
	private String name;
	private Date enableDate;
	private Date disableDate;
	private String phone;
	private String email;
	private String gender;
	private Date birthday;
	private String address;
	
	public User() {}
	
	public User(User u) {
		super(u);
		this.userUuid = u.userUuid;
		this.userId = u.userId;
		this.password = u.password;
		this.name = u.name;
		this.enableDate = u.enableDate;
		this.disableDate = u.disableDate;
		this.phone = u.phone;
		this.email = u.email;
		this.gender = u.gender;
		this.birthday = u.birthday;
		this.address = u.address;
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "USER_UUID", updatable = false, nullable = false, length=32)
	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	@Column(name = "USER_ID", unique = true, nullable = false, length=50)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENABLE_DATE")
	public Date getEnableDate() {
		return enableDate;
	}

	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DISABLE_DATE")
	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userUuid=" + userUuid + ", userId=" + userId + ", password=" + password + ", name=" + name
				+ ", enableDate=" + enableDate + ", disableDate=" + disableDate + ", phone=" + phone + ", email="
				+ email + ", gender=" + gender + ", birthday=" + birthday + ", address=" + address + "]";
	}
	
	
	

}
