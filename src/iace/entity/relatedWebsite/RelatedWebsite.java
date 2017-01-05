package iace.entity.relatedWebsite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;

@Entity
@Table(name = "RELATED_WEBSITE")
public class RelatedWebsite extends BaseEntity {
	private static final long serialVersionUID = 4033777453324911761L;
	
	private long id;
	private String url;
	private String picName;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RELATED_WEBSITE_ID")
	@SequenceGenerator(name = "SEQ_RELATED_WEBSITE_ID", sequenceName = "SEQ_RELATED_WEBSITE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "PIC_NAME")
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

}
