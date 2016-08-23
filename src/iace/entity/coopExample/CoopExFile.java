package iace.entity.coopExample;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import iace.entity.BaseEntity;

@MappedSuperclass
public class CoopExFile extends BaseEntity {

	private static final long serialVersionUID = -8512564987948352767L;

	private long id;
	private CoopEx coopEx;
	private String filePath;
	private String fileContentType;
	private String fileName;
	private String fileDesc;

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COOP_EXAMPLE_ID", updatable = false)
	public CoopEx getCoopEx() {
		return coopEx;
	}

	public void setCoopEx(CoopEx coopEx) {
		this.coopEx = coopEx;
	}

	@Column(name = "FILE_PATH", length = 200)
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "FILE_CONTENT_TYPE", length = 200)
	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	@Column(name = "FILE_NAME", length = 100)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_DESC", length = 2000)
	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	@Transient
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	@Transient
	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	@Transient
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Transient 
	public boolean hasUploadFile() {
		return this.upload != null && this.uploadContentType != null && this.uploadFileName != null;
	}
}
