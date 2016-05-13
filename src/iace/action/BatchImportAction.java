package iace.action;

import java.io.File;
import java.util.List;

import core.action.BaseAction;
import iace.entity.Patent;
import iace.service.PatentExcelAccessService;
import iace.service.PatentService;
import iace.service.ServiceFactory;

public class BatchImportAction extends BaseAction {

	private static final long serialVersionUID = 4680110280391417742L;
	
	private PatentExcelAccessService excelAccessService = ServiceFactory.getExcelAccessService();
	private PatentService patentService = ServiceFactory.getPatentService();

	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	
	public BatchImportAction() {
		super.setTitle("批次匯入");
	}
	
	public String batchImportPatent() {
		try {
//			String filePath = "D:\\SYSVIN\\2016\\i-ACE鏈結產學媒合平台";
//			File fileToCreate = new File(filePath, this.uploadFileFileName);
//			FileUtils.copyFile(this.uploadFile, fileToCreate);
			List<Patent> patentList = this.excelAccessService.excelToPatents(uploadFile);
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
	
	


	
}
