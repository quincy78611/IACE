package iace.action;

import java.util.ArrayList;
import java.util.List;

import core.util.AESEncrypter;
import iace.entity.option.School;
import iace.entity.qnrCooperateWay.QnrCooperateWay;
import iace.entity.qnrCooperateWay.QnrCooperateWayLinkModel;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;
import iace.service.QnrCooperateWayMeritService;
import iace.service.QnrCooperateWayService;
import iace.service.SchoolService;
import iace.service.ServiceFactory;

public class QnrCooperateWayAction extends BaseIaceAction {

	private static final long serialVersionUID = -8674132276568056185L;

	private SchoolService schoolService = ServiceFactory.getSchoolService(); 
	private QnrCooperateWayService qnrCooperateWayService = ServiceFactory.getQnrCooperateWayService();
	private QnrCooperateWayMeritService qnrCooperateWayMeritService = ServiceFactory.getQnrCooperateWayMeritService();

	private long schoolId;
	private String encryptSchoolId;
	private QnrCooperateWay qnrCoopereateWay;
	private List<QnrCooperateWayLinkModel> qnrCooperateWayLinks;
	private List<QnrCooperateWayMerit> qnrCooperateWayMerits;
	
	private final String encryptKey = "SYSVIN";

	public QnrCooperateWayAction() {
		super.setTitle("精進大學產學合作發展機制調查問卷");
	}

	public String index() {
		try {
			List<School> schools = this.schoolService.listAll();
			this.qnrCooperateWayLinks = new ArrayList<QnrCooperateWayLinkModel>();
			for (School school : schools) {
				QnrCooperateWayLinkModel model = new QnrCooperateWayLinkModel();
				model.setSchool(school);
				String encryptId = AESEncrypter.encrypt(encryptKey, String.valueOf(school.getId()));
				model.setEncryptSchoolId(encryptId);
				this.qnrCooperateWayLinks.add(model);
			}
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}
	
	public String fillInQnrPart0To3() {
		try {
			this.schoolId = Integer.valueOf(AESEncrypter.decrypt(encryptKey, encryptSchoolId));	
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}
	
	public String fillInQnrPart0To3Submit() {
		try {
			School school = this.schoolService.get(this.schoolId);
			this.qnrCoopereateWay.setSchool(school);
			this.qnrCooperateWayService.create(this.qnrCoopereateWay);
			this.schoolId = this.qnrCoopereateWay.getSchool().getId();
			this.qnrCooperateWayMerits = this.qnrCooperateWayMeritService.getForUpdate(this.schoolId);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}

	public String fillInQnrPart4() {
		try {
			this.schoolId = Integer.valueOf(AESEncrypter.decrypt(encryptKey, encryptSchoolId));			
			this.qnrCooperateWayMerits = this.qnrCooperateWayMeritService.getForUpdate(this.schoolId);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}
	
	public String fillInQnrPart4Submit() {
		try {
			this.qnrCooperateWayMeritService.updateSchoolMerit(this.qnrCooperateWayMerits);
			super.addActionMessage("成功儲存!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}

	public SchoolService getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

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

	public List<QnrCooperateWayLinkModel> getQnrCooperateWayLinks() {
		return qnrCooperateWayLinks;
	}

	public void setQnrCooperateWayLinks(List<QnrCooperateWayLinkModel> qnrCooperateWayLinks) {
		this.qnrCooperateWayLinks = qnrCooperateWayLinks;
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

	public String getEncryptKey() {
		return encryptKey;
	}
	
	

}
