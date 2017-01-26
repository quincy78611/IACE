package iace.entity.ePaper;

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
@Table(name = "EPAPER_SUBSCRIBER")
public class EPaperSubscriber extends BaseEntity {
	private static final long serialVersionUID = 6847633948456424557L;
	
	private long id;
	private String name;
	private String orgName;
	private String tel;
	private String email;
	private boolean isSubscribe;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EPAPER_ID")
	@SequenceGenerator(name = "SEQ_EPAPER_ID", sequenceName = "SEQ_EPAPER_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORG_NAME", length = 200)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "TEL", length = 50)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IS_SUBSCRIBE")
	@Type(type="true_false")
	public boolean getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

}
