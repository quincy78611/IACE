package iace.entity.rdFocus;

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
import iace.entity.option.BaseOption;

@Entity
@Table(name = "RD_FOCUS")
public class RdFocus extends BaseLinkiacEntity {
	private static final long serialVersionUID = -5094986030346445527L;
	
	private static List<BaseOption> categoryList = new ArrayList<BaseOption>();
	static {
		categoryList.add(new BaseOption("國際前瞻趨勢", "國際前瞻趨勢"));
		categoryList.add(new BaseOption("研發焦點", "研發焦點"));
		categoryList.add(new BaseOption("國內外產學合作產業化個案", "國內外產學合作產業化個案"));
		categoryList.add(new BaseOption("專利推薦", "專利推薦"));
	}

	private long id;
	private String category;
	private String title;
	private String source;
	private Date postDate;
	private String content;
	
	private List<RdFocusAttach> attachs = new ArrayList<RdFocusAttach>();

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RD_FOCUS_ID")
	@SequenceGenerator(name = "SEQ_RD_FOCUS_ID", sequenceName = "SEQ_RD_FOCUS_ID", allocationSize = 1, initialValue = 1)
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

	public static List<BaseOption> getCategoryList() {
		return categoryList;
	}

	@OneToMany(mappedBy="rdFocus", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)	
	public List<RdFocusAttach> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<RdFocusAttach> attachs) {
		this.attachs = attachs;
	}
	
	

}
