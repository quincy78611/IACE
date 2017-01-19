package iace.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileAction extends BaseIaceAction {

	private static final long serialVersionUID = 4096665557711038165L;

	private String fileFolder;
	private String downloadFileName;
	private InputStream fileInputStream;
	
	public FileAction() {
		super();
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.fileFolder = prop.getProperty("downloadFilesFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	public String downloadFileByName() {
		try {
			this.fileInputStream = new FileInputStream(new File(this.fileFolder, this.downloadFileName));
			this.downloadFileName = new String(this.downloadFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
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
