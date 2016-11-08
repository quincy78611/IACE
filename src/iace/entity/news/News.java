package iace.entity.news;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import iace.entity.BaseLinkiacEntity;

@Entity
@Table(name = "NEWS")
public class News extends BaseLinkiacEntity {

	private static final long serialVersionUID = 8273447145316025013L;

	private long id;
	private String category; // 一般公告, 新聞稿
	private String title;
	private String source;
	private Date postDate;
	private String content;
	
	private List<NewsAttach> attachs = new ArrayList<NewsAttach>();

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NEWS_ID")
	@SequenceGenerator(name = "SEQ_NEWS_ID", sequenceName = "SEQ_NEWS_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "CATEGORY")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "SOURCE")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "POST_DATE")
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@OneToMany(mappedBy="news", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)	
	public List<NewsAttach> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<NewsAttach> attachs) {
		this.attachs = attachs;
	}


	
}
