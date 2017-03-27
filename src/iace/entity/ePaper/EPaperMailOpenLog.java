package iace.entity.ePaper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;

@Entity
@Table(name = "EPAPER_MAIL_OPEN_LOG")
public class EPaperMailOpenLog extends BaseEntity {
	private static final long serialVersionUID = -9140480004588586822L;
	private long id;
	private long epaperId;
	private String email;

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

	@Column(name = "EPAPER_ID", length = 19)
	public long getEpaperId() {
		return epaperId;
	}

	public void setEpaperId(long epaperId) {
		this.epaperId = epaperId;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
