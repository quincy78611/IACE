package iace.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import core.util.ThumbnailUtil;

public class FileAction extends BaseIaceAction {

	private static final long serialVersionUID = 4096665557711038165L;
	private static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");
	private static Properties iaceProp = new Properties();
	static {
		try {
			iaceProp.load(FileAction.class.getClassLoader().getResourceAsStream("configs/iace.properties"));
		} catch (IOException e) {
			log.error("讀取設定檔 configs/iace.properties 失敗!");
		}
	}

	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	private boolean uploadSuccess;
	
	private String folderConfigKey; //用來取得設定檔中資料夾路徑用的 property key
	private String downloadFileSubPath; //要下載的檔案的子路徑
	private String downloadFileName; //用來顯示要下載檔案的檔名
	private InputStream fileInputStream;
	
	private int thumbnailWidth;
	private int thumbnailHeight;
	
	public FileAction() {
		super.setTitle("檔案中心");
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
				File dest = new File(getFileFolder(), this.downloadFileSubPath);
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

	public String ckEditorUploadFile() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// CKEditor提交的很重要的一个参数
		String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");

		String day = sdfDay.format(System.currentTimeMillis());
		String uuid = UUID.randomUUID().toString();
		String extension = FilenameUtils.getExtension(this.uploadFileName);
		this.downloadFileSubPath = day + "-" + uuid + "." + extension;
		File dest = new File(getFileFolder(), this.downloadFileSubPath);
		FileUtils.moveFile(this.upload, dest);
		
		if (StringUtils.isBlank(this.downloadFileName)) {
			this.downloadFileName = dest.getName();
		}
		
		String url = ServletActionContext.getRequest().getRequestURL().toString();
		String namespace = ServletActionContext.getActionMapping().getNamespace();
		String urlDomainName = url.substring(0, url.indexOf(namespace));
		String downloadUrl = urlDomainName + "/file/downloadFile?downloadFileSubPath=" + this.downloadFileSubPath;

		// 返回“图像”选项卡并显示图片
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + downloadUrl + "','')"); // 相对路径用于显示图片
		out.println("</script>");
		return null;
	}
	
	public String downloadFile() {
		try {
			File f = new File(getFileFolder(), this.downloadFileSubPath);
			this.fileInputStream = new FileInputStream(f);
			if (StringUtils.isBlank(this.downloadFileName)) {
				this.downloadFileName = "download."+FilenameUtils.getExtension(f.getName());
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String downloadThumbnail() {
		try {
			File f = new File(getFileFolder(), this.downloadFileSubPath);
			byte[] byteArrayImg = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
			byte[] thumbnailBytes = ThumbnailUtil.resize(byteArrayImg, this.thumbnailWidth, this.thumbnailHeight, true, 1.0f);
			this.fileInputStream = new ByteArrayInputStream(thumbnailBytes);
			if (StringUtils.isBlank(this.downloadFileName)) {
				this.downloadFileName = "download."+FilenameUtils.getExtension(f.getName());
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	private String getFileFolder() throws IOException {
		if (StringUtils.isBlank(this.folderConfigKey)) {
			return iaceProp.getProperty("downloadFilesFolder");
		} else {
			return iaceProp.getProperty(this.folderConfigKey);
		}
	}
	
	//==========================================================================
	
	public String getDownloadFileSubPath() {
		return downloadFileSubPath;
	}
	
	public String getFolderConfigKey() {
		return folderConfigKey;
	}

	public void setFolderConfigKey(String folderConfigKey) {
		this.folderConfigKey = folderConfigKey;
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

	public int getThumbnailWidth() {
		return thumbnailWidth;
	}

	public void setThumbnailWidth(int thumbnailWidth) {
		this.thumbnailWidth = thumbnailWidth;
	}

	public int getThumbnailHeight() {
		return thumbnailHeight;
	}

	public void setThumbnailHeight(int thumbnailHeight) {
		this.thumbnailHeight = thumbnailHeight;
	}
	
	
}
