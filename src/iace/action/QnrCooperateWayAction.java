package iace.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import core.util.AESEncrypter;
import iace.entity.option.OptionSchool;
import iace.entity.qnrCooperateWay.QnrCooperateWay;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;
import iace.service.OptionSchoolService;
import iace.service.QnrCooperateWayExcelService;
import iace.service.QnrCooperateWayService;
import iace.service.ServiceFactory;

public class QnrCooperateWayAction extends BaseIaceAction {

	private static final long serialVersionUID = -8674132276568056185L;

	private OptionSchoolService schoolService = ServiceFactory.getSchoolService(); 
	private QnrCooperateWayService qnrCooperateWayService = ServiceFactory.getQnrCooperateWayService();
	private QnrCooperateWayExcelService excelService = ServiceFactory.getQnrCooperateWayExcelService();

	private long schoolId;
	private String encryptSchoolId;
	private long qnrCooperateWayId;
	private QnrCooperateWay qnrCoopereateWay;
	private List<QnrCooperateWayMerit> qnrCooperateWayMerits;
	
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
			log.error("", e);
			return ERROR;
		}
	}
	
	public String downloadQnrLinksExcel() {
		try {
			List<OptionSchool> schools = this.schoolService.listAll();
			
			String currentUrl = ServletActionContext.getRequest().getRequestURL().toString();
			String qnrUrl = currentUrl.substring(0, currentUrl.lastIndexOf("/")) + "/fillInQnrPDPL";
			
			XSSFWorkbook wb = this.excelService.exportQnrLinksExcel(schools, qnrUrl);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			this.qrnExcelFileInputStream = new ByteArrayInputStream(baos.toByteArray());
			this.qnrExcelFileName = "問卷連結.xlsx";
			this.qnrExcelFileName = new String(this.qnrExcelFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}
	
	public String downloadUnfillQnrLinksExcel() {
		try {
			List<OptionSchool> schools = this.schoolService.listUnfillQnrCooperateWay();
			
			String currentUrl = ServletActionContext.getRequest().getRequestURL().toString();
			String qnrUrl = currentUrl.substring(0, currentUrl.lastIndexOf("/")) + "/fillInQnrPDPL";
			
			XSSFWorkbook wb = this.excelService.exportQnrLinksExcel(schools, qnrUrl);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			this.qrnExcelFileInputStream = new ByteArrayInputStream(baos.toByteArray());
			this.qnrExcelFileName = "問卷連結.xlsx";
			this.qnrExcelFileName = new String(this.qnrExcelFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
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
			log.error("", e);
			return ERROR;
		}
	}
	
	public String fillInQnrPDPL() {
		try {
			this.schoolId = Long.valueOf(AESEncrypter.decrypt(AESEncrypter.KEY, this.encryptSchoolId));	
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}
	
	public void validateFillInQnrPDPLSubmit() {
		validateApplicantData();
	}
	
	public String fillInQnrPDPLSubmit() {
		return SUCCESS;
	}
	
	public String fillInQnrPart0To3Submit() {
		try {
			OptionSchool school = this.schoolService.get(this.schoolId);
			this.qnrCoopereateWay.setSchool(school);
			this.qnrCooperateWayService.create(this.qnrCoopereateWay);
			
			this.qnrCooperateWayId = this.qnrCoopereateWay.getId();
			this.qnrCooperateWayMerits = new ArrayList<QnrCooperateWayMerit>();
			for (int year : QnrCooperateWayMerit.YEARS) {
				QnrCooperateWayMerit m = new QnrCooperateWayMerit();
				m.setYear(year);
				m.setQnrCooperateWay(this.qnrCoopereateWay);
				this.qnrCooperateWayMerits.add(m);
			}			
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}
	
	public void validateFillInQnrPart4Submit() {
		validateApplicantData();
		for (int i=0; i<this.qnrCooperateWayMerits.size(); i++) {
			String testValue, fieldName;
			QnrCooperateWayMerit merit = this.qnrCooperateWayMerits.get(i);
			
			testValue = merit.getC1();
			fieldName = "qnrCooperateWayMerits["+i+"].c1";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
			
			testValue = merit.getC2();
			fieldName = "qnrCooperateWayMerits["+i+"].c2";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
			
			testValue = merit.getC3();
			fieldName = "qnrCooperateWayMerits["+i+"].c3";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
			
			testValue = merit.getC4();
			fieldName = "qnrCooperateWayMerits["+i+"].c4";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
			
			testValue = merit.getC5();
			fieldName = "qnrCooperateWayMerits["+i+"].c5";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
			
			testValue = merit.getC6();
			fieldName = "qnrCooperateWayMerits["+i+"].c6";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
			
			testValue = merit.getC7();
			fieldName = "qnrCooperateWayMerits["+i+"].c7";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
			
			testValue = merit.getC8();
			fieldName = "qnrCooperateWayMerits["+i+"].c8";
			if (super.validateNotBlank(testValue, fieldName)) {
				super.validateStringAsDouble(testValue, fieldName);
			}
		}
	}
	
	public String fillInQnrPart4Submit() {
		try {
			QnrCooperateWay orgignQnrCooperateWay = this.qnrCooperateWayService.get(this.qnrCooperateWayId);
			if (orgignQnrCooperateWay.getQnrCooperateWayMerits() == null || 
				orgignQnrCooperateWay.getQnrCooperateWayMerits().size() == 0) {
				orgignQnrCooperateWay.setName(this.qnrCoopereateWay.getName());
				orgignQnrCooperateWay.setApplicantId(this.qnrCoopereateWay.getApplicantId());
				orgignQnrCooperateWay.setEmail(this.qnrCoopereateWay.getEmail());
				orgignQnrCooperateWay.setAddress(this.qnrCoopereateWay.getAddress());
				orgignQnrCooperateWay.setQnrCooperateWayMerits(this.qnrCooperateWayMerits);
				for (QnrCooperateWayMerit entity : this.qnrCooperateWayMerits) {
					entity.setQnrCooperateWay(orgignQnrCooperateWay);
					entity.create();
				}
				this.qnrCooperateWayService.update(orgignQnrCooperateWay);
			} else {
				this.addActionError("您已經填過此問卷，無法重複填寫!");
			}
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}
	
	private void validateApplicantData() {
		boolean isAllValid = true;
		if (this.qnrCoopereateWay.getAggreePDPL()) {
			isAllValid = super.validateNotBlankNLength(this.qnrCoopereateWay.getName(), 20, "qnrCoopereateWay.name") && isAllValid;
			if (super.validateNotBlankNLength(this.qnrCoopereateWay.getApplicantId(), 20, "qnrCoopereateWay.applicantId")) {
				isAllValid = super.validateTWPID(this.qnrCoopereateWay.getApplicantId(), "qnrCoopereateWay.applicantId") && isAllValid;
			} else {
				isAllValid = false;
			}
			
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

	public List<QnrCooperateWayMerit> getQnrCooperateWayMerits() {
		return qnrCooperateWayMerits;
	}

	public void setQnrCooperateWayMerits(List<QnrCooperateWayMerit> qnrCooperateWayMerits) {
		this.qnrCooperateWayMerits = qnrCooperateWayMerits;
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
