package iace.entity.coopExample;

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

import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.BaseOption;

@Entity
@Table(name = "COOP_EXAMPLE")
public class CoopEx extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = 6103079547676237969L;

	private static List<BaseOption> typeList = new ArrayList<BaseOption>();
	static {
		typeList.add(new BaseOption("商品化", "商品化"));
		typeList.add(new BaseOption("專利推廣", "專利推廣"));
		typeList.add(new BaseOption("新創事業", "新創事業"));
	}
	
	private long id;
	private Integer year;
	private String type;
	private String title;
	private String projName;
	private String rdTeam;
	private String assisTeam;
	private String content;
	
	private transient byte[] thumbnail;
	private List<CoopExImg> imgs = new ArrayList<CoopExImg>();
	private List<CoopExVideo> videos = new ArrayList<CoopExVideo>();
	private List<CoopExAttachFile> attachFiles = new ArrayList<CoopExAttachFile>();

	private int clickNum;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_COOP_EX_ID")
	@SequenceGenerator(name = "SEQUENCE_COOP_EX_ID", sequenceName = "SEQUENCE_COOP_EX_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "YEAR", length = 4)
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	@Column(name = "TYPE", length = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TITLE", length = 500)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "PROJ_NAME", length = 1000)
	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	@Column(name = "RD_TEAM", length = 1000)
	public String getRdTeam() {
		return rdTeam;
	}

	public void setRdTeam(String rdTeam) {
		this.rdTeam = rdTeam;
	}

	@Column(name = "ASSIS_TEAM", length = 1000)
	public String getAssisTeam() {
		return assisTeam;
	}

	public void setAssisTeam(String assisTeam) {
		this.assisTeam = assisTeam;
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
	
	@Transient
	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	@Transient
	public String getBase64Thumbnail() {
		if (this.thumbnail != null) {
			return Base64.encode(this.thumbnail);
		}
		return null;
	}

	@OneToMany(mappedBy="coopEx", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<CoopExImg> getImgs() {
		return imgs;
	}

	public void setImgs(List<CoopExImg> imgs) {
		this.imgs = imgs;
	}

	@OneToMany(mappedBy="coopEx", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<CoopExVideo> getVideos() {
		return videos;
	}

	public void setVideos(List<CoopExVideo> videos) {
		this.videos = videos;
	}

	@OneToMany(mappedBy="coopEx", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<CoopExAttachFile> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<CoopExAttachFile> attachFiles) {
		this.attachFiles = attachFiles;
	}
	
	@Column(name = "CLICK_NUM")
	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	
	public static List<BaseOption> getTypeList() {
		return typeList;
	}

	//==========================================================================
	
	@Override
	public void create() {
		super.create();
		for (CoopExImg img: this.getImgs()) {
			img.setCoopEx(this);
			img.create();
		}
		for (CoopExVideo video: this.getVideos()) {
			video.setCoopEx(this);
			video.create();
		}
		for (CoopExAttachFile file: this.getAttachFiles()) {
			file.setCoopEx(this);
			file.create();
		}
	}

	@Override
	public void update() {
		super.update();
		for (CoopExImg img: this.getImgs()) {
			img.setCoopEx(this);
			img.update();
		}
		for (CoopExVideo video: this.getVideos()) {
			video.setCoopEx(this);
			video.update();
		}
		for (CoopExAttachFile file: this.getAttachFiles()) {
			file.setCoopEx(this);
			file.update();
		}		
	}

	@Override
	public String toSysLog() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID: {"+this.id+"}, \r\n");
		sb.append("年度: {"+this.year+"}, \r\n");
		sb.append("類別: {"+this.type+"}, \r\n");
		sb.append("標題: {"+this.title+"}, \r\n");
		sb.append("案名: {"+this.projName+"}, \r\n");
		sb.append("研發團隊: {"+this.rdTeam+"}, \r\n");
		sb.append("輔導團隊: {"+this.assisTeam+"}, \r\n");
		sb.append("內容: {"+this.content+"}, \r\n");
		
		return sb.toString();
	}

	@Override
	public String toLunceneContent() {
		String str = this.title + " " + this.projName + " " + this.rdTeam + " " + this.assisTeam + " " + this.content; 
		return str;
	}	
	
	

}
