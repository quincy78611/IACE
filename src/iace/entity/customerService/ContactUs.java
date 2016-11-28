package iace.entity.customerService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;

@Entity
@Table(name = "CONTACT_US")
public class ContactUs extends BaseEntity {
	private static final long serialVersionUID = -9071583682662660010L;

	private long id;
	private String name;
	private boolean gender; // true: male, false: female
	private String companyName;
	private String phone;
	private String email;
	private String message;
	private boolean beenHandled;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACT_US_ID")
	@SequenceGenerator(name = "SEQ_CONTACT_US_ID", sequenceName = "SEQ_CONTACT_US_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER")
	@Type(type="true_false")
	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	@Column(name = "COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MESSAGE")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "BEEN_HANDLED")
	@Type(type="true_false")
	public boolean getBeenHandled() {
		return beenHandled;
	}

	public void setBeenHandled(boolean beenHandled) {
		this.beenHandled = beenHandled;
	}

}
