package iace.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import core.util.AESEncrypter;
import iace.entity.option.OptionSchool;
import iace.entity.qnrCooperateWay.QnrCooperateWay;
import iace.service.ServiceFactory;
import iace.service.option.OptionSchoolService;
import iace.service.qnrCooperateWay.QnrCooperateWayExcelService;
import iace.service.qnrCooperateWay.QnrCooperateWayService;

public class QnrCooperateWayAction extends BaseIaceAction {

	private static final long serialVersionUID = -8674132276568056185L;

	private OptionSchoolService schoolService = ServiceFactory.getOptionSchoolService(); 
	private QnrCooperateWayService qnrCooperateWayService = ServiceFactory.getQnrCooperateWayService();
	private QnrCooperateWayExcelService excelService = ServiceFactory.getQnrCooperateWayExcelService();

	private long schoolId;
	private String encryptSchoolId;
	private long qnrCooperateWayId;
	private QnrCooperateWay qnrCoopereateWay;
	
	private String qnrExcelFileName;
	private InputStream qrnExcelFileInputStream;
	
	public QnrCooperateWayAction() {
		super.setTitle("精進大學產學合作發展機制調查問卷");
	}

	public String index() {
		try {
			OptionSchool school = this.schoolService.getByCode("TEST");
			this.encryptSchoolId = AESEncrypter.encrypt(AESEncrypter.KEY, String.valueOf(school.getId()));
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String downloadQnrLinksExcel() {
		try {
			List<OptionSchool> schools = this.schoolService.listAll();
			
			String currentUrl = ServletActionContext.getRequest().getRequestURL().toString();
			String qnrUrl = currentUrl.substring(0, currentUrl.lastIndexOf("/")) + "/fillInQnr";
			
			XSSFWorkbook wb = this.excelService.exportQnrLinksExcel(schools, qnrUrl);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			this.qrnExcelFileInputStream = new ByteArrayInputStream(baos.toByteArray());
			this.qnrExcelFileName = "問卷連結.xlsx";
			this.qnrExcelFileName = new String(this.qnrExcelFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String downloadUnfillQnrLinksExcel() {
		try {
			List<OptionSchool> schools = this.schoolService.listUnfillQnrCooperateWay();
			
			String currentUrl = ServletActionContext.getRequest().getRequestURL().toString();
			String qnrUrl = currentUrl.substring(0, currentUrl.lastIndexOf("/")) + "/fillInQnr";
			
			XSSFWorkbook wb = this.excelService.exportQnrLinksExcel(schools, qnrUrl);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			this.qrnExcelFileInputStream = new ByteArrayInputStream(baos.toByteArray());
			this.qnrExcelFileName = "問卷連結.xlsx";
			this.qnrExcelFileName = new String(this.qnrExcelFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String downloadQnrResultExcel() {
		try {
			List<QnrCooperateWay> qnrList = this.qnrCooperateWayService.listAll();
			XSSFWorkbook wb = this.excelService.exportQnrResulotExcel(qnrList);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			this.qrnExcelFileInputStream = new ByteArrayInputStream(baos.toByteArray());
			this.qnrExcelFileName = "前三部分問卷結果.xlsx";
			this.qnrExcelFileName = new String(this.qnrExcelFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String fillInQnr() {
		try {
			this.schoolId = Long.valueOf(AESEncrypter.decrypt(AESEncrypter.KEY, this.encryptSchoolId));	
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public void validateFillInQnrSubmit() {
		validateApplicantData();
	}
	
	public String fillInQnrSubmit() {
		try {
			OptionSchool school = this.schoolService.get(this.schoolId);
			this.qnrCoopereateWay.setSchool(school);
			this.qnrCooperateWayService.create(this.qnrCoopereateWay);
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	private void validateApplicantData() {
		boolean isAllValid = true;
		if (this.qnrCoopereateWay.getAggreePDPL()) {
			isAllValid = super.validateNotBlankNLength(this.qnrCoopereateWay.getName(), 20, "qnrCoopereateWay.name") && isAllValid;
			if (super.validateNotBlankNLength(this.qnrCoopereateWay.getEmail(), 100, "qnrCoopereateWay.email")) {
				isAllValid = super.validateEmail(this.qnrCoopereateWay.getEmail(), "qnrCoopereateWay.email") && isAllValid;
			} else {
				isAllValid = false;
			}
			isAllValid = super.validateNotBlankNLength(this.qnrCoopereateWay.getAddress(), 200, "qnrCoopereateWay.address") && isAllValid;
		}
		
		if (!isAllValid) {
			this.addActionError("部分資料有問題，請重新檢查!");
		}
	}

	// =========================================================================
	
	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}

	public String getEncryptSchoolId() {
		return encryptSchoolId;
	}

	public void setEncryptSchoolId(String encryptSchoolId) {
		this.encryptSchoolId = encryptSchoolId;
	}

	public long getQnrCooperateWayId() {
		return qnrCooperateWayId;
	}

	public void setQnrCooperateWayId(long qnrCooperateWayId) {
		this.qnrCooperateWayId = qnrCooperateWayId;
	}

	public QnrCooperateWay getQnrCoopereateWay() {
		return qnrCoopereateWay;
	}

	public void setQnrCoopereateWay(QnrCooperateWay qnrCoopereateWay) {
		this.qnrCoopereateWay = qnrCoopereateWay;
	}

	public String getQnrExcelFileName() {
		return qnrExcelFileName;
	}

	public void setQnrExcelFileName(String qnrExcelFileName) {
		this.qnrExcelFileName = qnrExcelFileName;
	}

	public InputStream getQrnExcelFileInputStream() {
		return qrnExcelFileInputStream;
	}

	public void setQrnExcelFileInputStream(InputStream qrnExcelFileInputStream) {
		this.qrnExcelFileInputStream = qrnExcelFileInputStream;
	}


}


