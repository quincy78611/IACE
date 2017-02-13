package iace.entity.videosArea;

import java.io.File;
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

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import core.util.StringUtil;
import iace.entity.BaseLinkiacEntity;

@Entity
@Table(name = "VIDEOS_AREA")
public class VideosArea extends BaseLinkiacEntity {
	private static final long serialVersionUID = -8019558815579768240L;
	
	private long id;
	private String title;
	private String source;
	private String content; // 內容
	private String actDate; // 活動日期
	private String actAddress;
	private String organizer; // 主辦單位
	private String advisor; // 指導單位
	
	private transient byte[] thumbnail;
	private transient File uploadThumbnail;
	private transient String uploadThumbnailContentType;
	private transient String uploadThumbnailFileName;
	
	private List<Video> videoList = new ArrayList<Video>();

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VIDEOS_AREA_ID")
	@SequenceGenerator(name = "SEQ_VIDEOS_AREA_ID", sequenceName = "SEQ_VIDEOS_AREA_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Transient
	public String getContentWithoutHTML() {
		return StringUtil.filterHtml(this.content);
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
	
	public void setBase64Thumbnail(String s) {
		if (StringUtils.isNotBlank(s)) {
			this.thumbnail = Base64.decode(s);
		}
	}
	
	@Transient
	public File getUploadThumbnail() {
		return uploadThumbnail;
	}

	public void setUploadThumbnail(File uploadThumbnail) {
		this.uploadThumbnail = uploadThumbnail;
	}

	@Transient
	public String getUploadThumbnailContentType() {
		return uploadThumbnailContentType;
	}

	public void setUploadThumbnailContentType(String uploadThumbnailContentType) {
		this.uploadThumbnailContentType = uploadThumbnailContentType;
	}

	@Transient
	public String getUploadThumbnailFileName() {
		return uploadThumbnailFileName;
	}

	public void setUploadThumbnailFileName(String uploadThumbnailFileName) {
		this.uploadThumbnailFileName = uploadThumbnailFileName;
	}

	@OneToMany(mappedBy="videosArea", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	
}
