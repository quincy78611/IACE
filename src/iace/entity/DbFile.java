package iace.entity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DiscriminatorOptions;

import iace.entity.option.BaseOption;


@Entity
@Table(name = "DB_FILE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorOptions(force=true)
public abstract class DbFile extends BaseEntity {
	private static final long serialVersionUID = -4858468713348065564L;
	
	public static int FILE_TYPE_FILE = 0;
	public static int FILE_TYPE_IMAGE = 1;

	private static List<BaseOption> fileTypeList = new ArrayList<BaseOption>();
	static {
		fileTypeList.add(new BaseOption(FILE_TYPE_FILE+"", "檔案"));
		fileTypeList.add(new BaseOption(FILE_TYPE_IMAGE+"", "圖片"));
	}
	
	private long id;

	private transient String fileFolder;
	private String fileSubPath;
	private byte[] fileContent;
	private int fileType; // 0:檔案, 1:圖片
	private String fileTitle;

	private transient File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private byte[] thumbnail;

	// =========================================================================

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FILE_ID")
	@SequenceGenerator(name = "SEQ_FILE_ID", sequenceName = "SEQ_FILE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Transient
	public String getFileFolder() {
		return fileFolder;
	}

	public void setFileFolder(String fileFolder) {
		this.fileFolder = fileFolder;
	}

	@Column(name = "FILE_SUB_PATH", length = 200)
	public String getFileSubPath() {
		return fileSubPath;
	}

	public void setFileSubPath(String fileSubPath) {
		this.fileSubPath = fileSubPath;
	}

	@Column(name = "FILE_CONTENT")
	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
	@Transient
	public InputStream getFileContentInputStream() throws IOException {	
		if (this.fileContent == null) {
			throw new NullPointerException("檔案不存在或是檔案內容已遺失");
		} else {
			return new ByteArrayInputStream(this.fileContent);
		}
	}

	@Transient
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	@Column(name = "UPLOAD_CONTENT_TYPE")
	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	@Column(name = "UPLOAD_FILE_NAME")
	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	@Column(name = "FILE_TYPE")
	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	@Column(name = "FILE_TITLE")
	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	
	@Column(name = "THUMBNAIL")
	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public static List<BaseOption> getFileTypeList() {
		return fileTypeList;
	}

	// =========================================================================


	public boolean hasUpload() {
		return this.upload != null && this.uploadContentType != null && this.uploadFileName != null;
	}

	@Transient
	public boolean isSaveInDisk() {
		return StringUtils.isNotBlank(this.fileSubPath);
	}

	@Transient
	public boolean isSaveInDb() {
		return StringUtils.isBlank(this.fileSubPath) ;
	}

	public void saveUploadFile() throws IOException {
		if (hasUpload()) {
			if (isSaveInDb()) {
				Path p = Paths.get(this.upload.getAbsolutePath());
				this.fileContent = Files.readAllBytes(p);
			} else {
				if (StringUtils.isBlank(this.fileFolder)) {
					throw new IOException("Must set [fileFolder] value before calling this method!");
				} else {
					this.upload.renameTo(new File(this.fileFolder, this.fileSubPath));
				}
			}
		} else {
			throw new IOException("There is no upload file!");
		}
	}

	public byte[] loadFileContentFromDisk() throws IOException {
		if (isSaveInDb()) {
			throw new IOException("File is save in Database, shouldn't call this method!");
		} else {
			if (StringUtils.isBlank(this.fileFolder)) {
				throw new IOException("Must set [fileFolder] value before calling this method!");
			} else {
				File f = new File(this.fileFolder, this.fileSubPath);
				if (f.exists()) {
					Path p = Paths.get(f.getAbsolutePath());
					this.fileContent = Files.readAllBytes(p);
					return this.fileContent;
				} else {
					return null;
				}
			}
		}
	}
	
}
