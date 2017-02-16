package iace.entity.marquee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;

@Entity
@Table(name = "MARQUEE")
public class Marquee extends BaseEntity {
	private static final long serialVersionUID = 8345255861975427368L;

	private long id;
	private String text;
	private String url;
	private int sort; // from large to small
	private boolean displayStatus;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MARQUEE_ID")
	@SequenceGenerator(name = "SEQ_MARQUEE_ID", sequenceName = "SEQ_MARQUEE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="TEXT")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name="URL")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Transient
	public boolean getHasLink() {
		return StringUtils.isNotBlank(this.url);
	}

	@Column(name = "SORT")
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	@Column(name = "DISPLAY_STATUS")
	@Type(type="true_false")
	public boolean getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(boolean displayStatus) {
		this.displayStatus = displayStatus;
	}



}
