package iace.entity.activity;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import iace.entity.BaseLinkiacEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.BaseOption;

@Entity
@Table(name = "ACTIVITY")
public class Activity extends BaseLinkiacEntity implements IntegrationSearch {
	private static final long serialVersionUID = -6881036457895017672L;
	
	private static List<BaseOption> categoryList = new ArrayList<BaseOption>();
	static {
		categoryList.add(new BaseOption("成果發表", "成果發表"));
		categoryList.add(new BaseOption("計畫宣導", "計畫宣導"));
		categoryList.add(new BaseOption("媒合會", "媒合會"));
		categoryList.add(new BaseOption("人培課程", "人培課程"));
		categoryList.add(new BaseOption("外部活動", "外部活動"));
		categoryList.add(new BaseOption("計畫研習", "計畫研習"));
	}
	
	private long id;
	private String category;
	private String title;
	private String source;
	private Date postDate;
	private String content;
	private String actDate; //活動日期
	private String actAddress;
	private String organizer; //主辦單位
	private String advisor; //指導單位
	private String contact; //聯絡窗口
	private String signUpNum; //報名人數
	private String signUpPeriod; //報名期間
	private String signUpLink;
	
	private List<ActivityAttach> attachList = new ArrayList<ActivityAttach>();
	private List<ActivityVideo> videoList = new ArrayList<ActivityVideo>();
	
	private byte[] thumbnail;
	
	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACTIVITY_ID")
	@SequenceGenerator(name = "SEQ_ACTIVITY_ID", sequenceName = "SEQ_ACTIVITY_ID", allocationSize = 1, initialValue = 1)
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
	
	@Transient
	public String getContentWithoutHTML() {
		String replace = this.content.replaceAll("\\<[^>]*>","");
		return replace;
	}

	@Column(name = "ACT_DATE")
	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}

	@Column(name = "ACT_ADDRESS")
	public String getActAddress() {
		return actAddress;
	}

	public void setActAddress(String actAddress) {
		this.actAddress = actAddress;
	}

	@Column(name = "ORGANIZER")
	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	@Column(name = "ADVISOR")
	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	@Column(name = "CONTACT")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "SIGN_UP_NUM")
	public String getSignUpNum() {
		return signUpNum;
	}

	public void setSignUpNum(String signUpNum) {
		this.signUpNum = signUpNum;
	}

	@Column(name = "SIGN_UP_PERIOD")
	public String getSignUpPeriod() {
		return signUpPeriod;
	}

	public void setSignUpPeriod(String signUpPeriod) {
		this.signUpPeriod = signUpPeriod;
	}

	@Column(name = "SIGN_UP_LINK")
	public String getSignUpLink() {
		return signUpLink;
	}

	public void setSignUpLink(String signUpLink) {
		this.signUpLink = signUpLink;
	}
	
	@OneToMany(mappedBy="activity", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<ActivityAttach> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<ActivityAttach> attachList) {
		this.attachList = attachList;
	}
	
	@OneToMany(mappedBy="activity", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<ActivityVideo> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<ActivityVideo> videoList) {
		this.videoList = videoList;
	}

	@Column(name = "THUMBNAIL")
	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Transient
	public String getBase64Thumbnail() {
		return Base64.encode(this.thumbnail);
	}

	public static List<BaseOption> getCategoryList() {
		return categoryList;
	}

	@Override
	public String toLunceneContent() {
		String str = this.title + " " + this.organizer + " " + this.advisor + " " + this.content;
		return str;
	}

}
