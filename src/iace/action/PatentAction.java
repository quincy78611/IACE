package iace.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import core.action.BaseAction;
import iace.entity.Patent;
import iace.entity.TechField;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionTrl;
import iace.service.OptionCountryService;
import iace.service.OptionTrlService;
import iace.service.PatentService;
import iace.service.ServiceFactory;
import iace.service.TechFieldService;

public class PatentAction extends BaseAction {

	private static final long serialVersionUID = 6073541012565178740L;
	
	private PatentService patentService = ServiceFactory.getPatentService();
	private TechFieldService techFieldService = ServiceFactory.getTechFieldService();
	private OptionCountryService optionCountryService = ServiceFactory.getOptionCountryService();
	private OptionTrlService optionTrlService = ServiceFactory.getOptionTrlService();

	private String searchPatentName;
	private String searchAppliactionNo;	
	private String searchCountry;
	private long searchTechField;
	
	private List<TechField> techFieldList;
	private List<OptionCountry> optionCountryList;
	private List<OptionTrl> optionTrlList;
	
	private List<Patent> patentList;
	
	private long id;
	private Patent patent;
	
	public PatentAction() {
		super.setTitle("專利資料");
	}
	
	public String init() {		
		return SUCCESS;
	}
	
	public String index() {
		try {
			patentList = patentService.searchBy(searchPatentName, searchAppliactionNo, searchCountry, searchTechField);			
			return SUCCESS;
		} catch (Exception e) {
			return SUCCESS;
		}		
	}
	
	public String showDetail() {
		try {
			this.patent = this.patentService.get(this.id);			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String create(){
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		validateBeforeSubmit();
	}
	
	public String createSubmit() {
		try {			
			this.patentService.create(this.patent);
			this.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String update() {
		try {
			this.patent = this.patentService.get(this.id);			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public void validateUpdateSubmit() {
		validateBeforeSubmit();
	}
	
	public String updateSubmit() {
		try {			
			this.patentService.update(this.patent);
			this.addActionMessage("UPDATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String delete() {
		try {
			this.patent = this.patentService.get(this.id);			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteSubmit() {
		try {
			this.patentService.delete(this.patent);
			this.addActionMessage("DELETE SUCCESS!");

			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	@Override
	public void validate(){
		String actionName = ActionContext.getContext().getName();
		if ("showDetail".equals(actionName) || "update".equals(actionName) || "delete".equals(actionName)) {
			validateIdExist();
		}
	}
	
	private void validateBeforeSubmit() {
		super.validateNotBlankNLength(this.patent.getName(), 300, "patent.name");		
		super.validateNotBlankNLength(this.patent.getAssignee(), 500, "patent.assignee");
		super.validateNotBlankNLength(this.patent.getInvertor(), 500, "patent.invertor");
		super.validateNotBlankNLength(this.patent.getCountry(), 10, "patent.country");
		super.validateNotBlankNLength(this.patent.getAppliactionNo(), 100, "patent.appliactionNo");
		super.validateNotNull(this.patent.getApplicationDate(), "patent.applicationDate");
		super.validateTextMaxLength(this.patent.getOpenNo(), 100, "patent.openNo");
//		super.validateNotNull(this.patent.getOpenDate(), "patent.openDate");
		super.validateTextMaxLength(this.patent.getPublicationNo(), 100, "patent.publicationNo");
//		super.validateNotNull(this.patent.getPublicationDate(), "patent.publicationDate");
		super.validateNotBlankNLength(this.patent.getCategory(), 100, "patent.category");
		super.validateNotBlankNLength(this.patent.getPatentStatus(), 500, "patent.patentStatus");
		super.validateNotBlankNLength(this.patent.getFamilyNo(), 2000, "patent.familyNo");
		super.validateNotBlankNLength(this.patent.getIpc(), 100, "patent.ipc");
		super.validateNotBlank(this.patent.getTechAbstract(), "patent.techAbstract");
		super.validateNotBlankNLength(this.patent.getImportantPicturePath(), 200, "patent.importantPicturePath");
		super.validateTextMaxLength(this.patent.getImportantPictureCode(), 100, "patent.importantPictureCode");
		super.validateNotBlankNLength(this.patent.getTechField().getName(), 500, "patent.techField.name");
		super.validateTextMaxLength(this.patent.getUsage(), 500, "patent.usage");	
		if (this.patent.getTrl() != null && StringUtils.isNotBlank(this.patent.getTrl().getCode())) {
			try {
				if (this.optionTrlService.isCodeExist(this.patent.getTrl().getCode()) == false) {
					super.addFieldError("patent.trl.code", "發展階段代碼不存在");
				}
			} catch(Exception e) {
				super.addActionError(e.getMessage());
			}
		}		
		super.validateTextMaxLength(this.patent.getTrlDesc(), 2000, "patent.trlDesc");
		
		try {
			if (this.patentService.checkUK(this.patent) == false) {
				super.addFieldError("patent.appliactionNo", "申請號和專業技術領域合起來必須唯一");
				super.addFieldError("patent.techField.name", "申請號和專業技術領域合起來必須唯一");
			}
		} catch(Exception e) {
			super.addActionError(e.getMessage());
		}
	}
	
	private void validateIdExist() {
		try {
			this.patent = this.patentService.get(id);
			if (this.patent == null) {
				this.addActionError("找不到資料");
			}
		} catch(Exception e) {
			this.addActionError(e.getMessage());
		}
	}
	
	//==========================================================================

	public List<Patent> getPatentList() {
		return patentList;
	}	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Patent getPatent() {
		return patent;
	}

	public void setPatent(Patent patent) {
		this.patent = patent;
	}

	public List<OptionCountry> getOptionCountryList() {
		this.optionCountryList = this.optionCountryService.listAll();
		return optionCountryList;
	}

	public List<OptionTrl> getOptionTrlList() {
		this.optionTrlList = this.optionTrlService.listAll();
		return optionTrlList;
	}

	public List<TechField> getTechFieldList() {
		this.techFieldList = this.techFieldService.listAll();
		return techFieldList;
	}

	public String getSearchCountry() {
		return searchCountry;
	}

	public void setSearchCountry(String searchCountry) {
		this.searchCountry = searchCountry;
	}

	public String getSearchPatentName() {
		return searchPatentName;
	}

	public void setSearchPatentName(String searchPatentName) {
		this.searchPatentName = searchPatentName;
	}

	public String getSearchAppliactionNo() {
		return searchAppliactionNo;
	}

	public void setSearchAppliactionNo(String searchAppliactionNo) {
		this.searchAppliactionNo = searchAppliactionNo;
	}

	public long getSearchTechField() {
		return searchTechField;
	}

	public void setSearchTechField(long searchTechField) {
		this.searchTechField = searchTechField;
	}



	
	

}

