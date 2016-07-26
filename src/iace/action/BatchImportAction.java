package iace.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import iace.entity.Patent;
import iace.entity.ResearchPlan;
import iace.service.PatentExcelService;
import iace.service.PatentService;
import iace.service.ResearchPlanExcelService;
import iace.service.ResearchPlanService;
import iace.service.ServiceFactory;

public class BatchImportAction extends BaseIaceAction {

	private static final long serialVersionUID = 4680110280391417742L;
	
	private ResearchPlanExcelService researchPlanExcelService = ServiceFactory.getResearchPlanExcelService();
	private PatentExcelService patentExcelService = ServiceFactory.getPatentExcelService();
	private ResearchPlanService researchPlanService = ServiceFactory.getResearchPlanService();
	private PatentService patentService = ServiceFactory.getPatentService();

	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	
	private String downloadFileName;
	private InputStream sampleFileInputStream;
	
	public BatchImportAction() {
		super.setTitle("批次匯入");
	}
	
	public String batchImportResearchPlan() {
		try {
			List<ResearchPlan> entities = this.researchPlanExcelService.excelToResearchPlans(this.uploadFile);
			List<String> errMsgs = this.researchPlanService.createAll(entities);
			if (errMsgs.size() > 0) {
				super.addActionError("----- 匯入檔案中含有下列錯誤，批次匯入失敗，請修正後重新上傳 -----");
				for (String errMsg : errMsgs) {
					super.addActionError(errMsg);
				}
				return INPUT;
			} else {
				super.addActionMessage("成功新增"+entities.size()+"筆研究計畫資料");
				return SUCCESS;
			}
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String downloadResearchPlanBatchSample() {
		try {
			ServletContext context = ServletActionContext.getServletContext();
			this.downloadFileName = "技術資料匯入_sample.xlsx";
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
	
	public String batchImportPatent() {
		try {
//			String filePath = "D:\\SYSVIN\\2016\\i-ACE鏈結產學媒合平台";
//			File fileToCreate = new File(filePath, this.uploadFileFileName);
//			FileUtils.copyFile(this.uploadFile, fileToCreate);
			List<Patent> patentList = this.patentExcelService.excelToPatents(uploadFile);
			List<String> errMsgs = this.patentService.createAll(patentList);
			if (errMsgs.size() > 0) {
				super.addActionError("----- 匯入檔案中含有下列錯誤，批次匯入失敗，請修正後重新上傳 -----");
				for (String errMsg : errMsgs) {
					super.addActionError(errMsg);
				}
				return INPUT;
			} else {
				super.addActionMessage("成功新增"+patentList.size()+"筆專利資料");
				return SUCCESS;
			}
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String downloadPatentBatchSample() {
		try {
			ServletContext context = ServletActionContext.getServletContext();
			this.downloadFileName = "專利資料匯入_sample.xlsx";
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
	
	//==========================================================================

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

	public InputStream getSampleFileInputStream() {
		return sampleFileInputStream;
	}

	
	


	
}
