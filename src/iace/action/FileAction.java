package iace.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileAction extends BaseIaceAction {

	private static final long serialVersionUID = 4096665557711038165L;
	private static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private boolean uploadSuccess;
	
	private String fileFolder;
	private String downloadFileSubPath; //要下載的檔案的子路徑
	private String downloadFileName; //用來顯示要下載檔案的檔名
	private InputStream fileInputStream;
	
	public FileAction() {
		super.setTitle("檔案中心");
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.fileFolder = prop.getProperty("downloadFilesFolder");
		} catch (IOException e) {
			log.fatal("", e);
		}
	}
	
	public boolean hasUpload() {
		return this.upload != null && this.uploadContentType != null && this.uploadFileName != null;
	}
	
	public String uploadFile() {
		try {
			if (hasUpload() == false) {
				this.uploadSuccess = false;
			} else {
				String day = sdfDay.format(System.currentTimeMillis());
				String uuid = UUID.randomUUID().toString();
				String extension = FilenameUtils.getExtension(this.uploadFileName);
				this.downloadFileSubPath = day + File.separator + uuid + "." + extension;
				File dest = new File(this.fileFolder, this.downloadFileSubPath);
				FileUtils.moveFile(this.upload, dest);
				
				if (StringUtils.isBlank(this.downloadFileName)) {
					this.downloadFileName = dest.getName();
				}
				
				super.addActionMessage("UPLOAD SUCCESS");
				this.uploadSuccess = true;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String downloadFile() {
		try {
			this.fileInputStream = new FileInputStream(new File(this.fileFolder, this.downloadFileSubPath));
//			this.downloadFileName = new String(this.downloadFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	//==========================================================================
	
	public String getDownloadFileSubPath() {
		return downloadFileSubPath;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public boolean getUploadSuccess() {
		return uploadSuccess;
	}

	public void setDownloadFileSubPath(String downloadFileSubPath) {
		this.downloadFileSubPath = downloadFileSubPath;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
}
