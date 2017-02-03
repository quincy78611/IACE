package iace.entity.ePaper;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;

@Entity
@Table(name = "EPAPER")
public class EPaper extends BaseEntity {
	private static final long serialVersionUID = 1574883937623691462L;
	
	private long id;
	private String title;
	private int no; //期數
	private Date postDate;
	private boolean publishState; //發布狀態 (0:未發佈, 1:已發佈)
	
	private String fileName;
	
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

	@Column(name = "TITLE", length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "NO")
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	@Column(name = "POST_DATE")
	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Column(name = "PUBLISH_STATE")
	@Type(type="true_false")
	public boolean getPublishState() {
		return publishState;
	}

	public void setPublishState(boolean publishState) {
		this.publishState = publishState;
	}

	@Column(name = "FILE_NAME", length = 200)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Transient
	public String getFilePath() {
		ServletContext context = ServletActionContext.getServletContext();
		return context.getRealPath("/ePapers/" + this.fileName);
	}
	
	@Transient
	public String getUrl() {
		return "/ePapers/" + this.fileName;
	}
}
