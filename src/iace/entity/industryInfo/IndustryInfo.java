package iace.entity.industryInfo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.BaseOption;

@Entity
@Table(name = "INDUSTRY_INFO")
public class IndustryInfo extends BaseEntity implements IntegrationSearch {
	private static final long serialVersionUID = -4348180685333981591L;
	private static List<BaseOption> categoryList = new ArrayList<BaseOption>();
	static {
		categoryList.add(new BaseOption("新聞雷達", "新聞雷達"));
		categoryList.add(new BaseOption("產業評析", "產業評析"));
	}
	
	private long id;
	private String category; // 新聞雷達, 產業評析
	private String title;
	private String link;
	private String source;
	private Date postDate;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INDUSTRY_INFO_ID")
	@SequenceGenerator(name = "SEQ_INDUSTRY_INFO_ID", sequenceName = "SEQ_INDUSTRY_INFO_ID", allocationSize = 1, initialValue = 1)
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

	@Column(name = "LINK")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public static List<BaseOption> getCategoryList() {
		return categoryList;
	}

	@Override
	public String toLunceneContent() {
		String str = this.title;
		return str;
	}

}
