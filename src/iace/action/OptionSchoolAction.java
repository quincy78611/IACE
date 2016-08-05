package iace.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import iace.entity.option.OptionSchool;
import iace.service.OptionSchoolExcelService;
import iace.service.ServiceFactory;

public class OptionSchoolAction extends BaseOptionAction<OptionSchool> {

	private static final long serialVersionUID = 3938291958134335193L;

	private OptionSchoolExcelService optionSchoolExcelService = ServiceFactory.getOptionSchoolService();
	
	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	
	private String downloadFileName;
	private InputStream sampleFileInputStream;
	
	public OptionSchoolAction() {
		super("學校 代碼", ServiceFactory.getSchoolService());
	}

	public String batchImport() {
		try {
			List<OptionSchool> optionList = this.optionSchoolExcelService.excelToOptionSchoolList(this.uploadFile);
			int newInsertDataCount = super.optionService.createAll(optionList);
			super.addActionMessage("成功新增"+newInsertDataCount+"筆資料");
			super.index();
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String downloadBatchSample() {
		try {
			ServletContext context = ServletActionContext.getServletContext();
			this.downloadFileName = "學校代碼資料匯入_sample.xlsx";
			String filePath = context.getRealPath("/files/"+this.downloadFileName);
			log.debug(filePath);
			sampleFileInputStream = new FileInputStream(new File(filePath));
			this.downloadFileName = new String(this.downloadFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}	
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public InputStream getSampleFileInputStream() {
		return sampleFileInputStream;
	}

	public void setSampleFileInputStream(InputStream sampleFileInputStream) {
		this.sampleFileInputStream = sampleFileInputStream;
	}
	
	
	
}
