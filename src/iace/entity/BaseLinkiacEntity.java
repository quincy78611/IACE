package iace.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

@MappedSuperclass
public abstract class BaseLinkiacEntity extends BaseEntity {

	private static final long serialVersionUID = 6365031563606445552L;

	private String metaTitle;
	private String metaDes;
	private String metaKeyword;
	
	private int sort; // from large to small
	private boolean displayStatus;
	private boolean homeDisplayStatus;
	
	private int clickNum;
	
	@Column(name = "META_TITLE")
	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	@Column(name = "META_DES")
	public String getMetaDes() {
		return metaDes;
	}

	public void setMetaDes(String metaDes) {
		this.metaDes = metaDes;
	}

	@Column(name = "META_KEYWORD")
	public String getMetaKeyword() {
		return metaKeyword;
	}

	public void setMetaKeyword(String metaKeyword) {
		this.metaKeyword = metaKeyword;
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

	@Column(name = "HOME_DISPLAY_STATUS")
	@Type(type="true_false")
	public boolean getHomeDisplayStatus() {
		return homeDisplayStatus;
	}

	public void setHomeDisplayStatus(boolean homeDisplayStatus) {
		this.homeDisplayStatus = homeDisplayStatus;
	}

	@Column(name = "CLICK_NUM")
	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	
}
