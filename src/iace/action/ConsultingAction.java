package iace.action;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.ExcelUtil;
import core.util.PagedList;
import iace.entity.consulting.Consulting;
import iace.entity.consulting.ConsultingSearchModel;
import iace.entity.option.OptionConsult;
import iace.entity.option.OptionIndustry;
import iace.entity.option.OptionOrganizationType;
import iace.service.ServiceFactory;
import iace.service.consulting.ConsultingService;
import iace.service.option.OptionConsultService;
import iace.service.option.OptionIndustryService;
import iace.service.option.OptionOrganizationTypeService;

public class ConsultingAction extends BaseIaceAction {

	private static final long serialVersionUID = 7983348708278484405L;
	
	private ConsultingService consultingService = ServiceFactory.getConsultingService();
	private OptionOrganizationTypeService optionOrgTypeService = ServiceFactory.getOptionOrganizationTypeService();
	private OptionConsultService optionConsultService = ServiceFactory.getOptionConsultService();
	private OptionIndustryService optionIndustryService = ServiceFactory.getOptionIndustryService();
	
	private ConsultingSearchModel searchCondition = new ConsultingSearchModel();	
	private PagedList<Consulting> consultingPagedList;
	
	private List<OptionOrganizationType> optionOrganizationTypeList;
	private List<OptionConsult> optionConsultList;
	private List<OptionIndustry> optionIndustryList;
	
	private long id;
	private Consulting consulting;	
	
	private String downloadFileName;
	private InputStream downloadFileInputStream;
	
	private String reportFileName;
	private InputStream reportInputStream;

	public ConsultingAction() {
		super.setTitle("諮詢服務表");
	}
	
	public String init() {
		return SUCCESS;
	}
	
	public String index() {
		try {
			this.consultingPagedList = this.consultingService.searchBy(searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String showDetail() {
		try {
			this.consulting = this.consultingService.get(this.id);
			if (this.consulting == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;			
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}	
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		validateBeforeSubmit();
	}
	
	public String createSubmit() {
		try {
			this.consultingService.create(this.consulting, super.getSysLog(), super.getCurrentSysUser());
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
			this.consulting = this.consultingService.get(this.id);
			if(this.consulting == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			
			return SUCCESS;
		} catch(Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public void validateUpdateSubmit() {
		validateBeforeSubmit();
	}
	
	public String updateSubmit() {
		try {
			this.consultingService.update(this.consulting, super.getSysLog(), super.getCurrentSysUser());
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
			this.consulting = this.consultingService.get(this.id);
			if (this.consulting == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteSubmit() {
		try {
			this.consultingService.delete(this.id, super.getSysLog());
			this.addActionMessage("DELETE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	private void validateBeforeSubmit() {
		//TODO
	}
	
	public String exportRawData() {
		try {
			XSSFWorkbook wb = this.consultingService.exportRawData(searchCondition);
			this.downloadFileInputStream = ExcelUtil.workbookToInputStream(wb);
			this.downloadFileName = "consulting_raw_data.xlsx";
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String printReport() {
		try {
			this.reportInputStream = this.consultingService.printReport(this.id);
			this.reportFileName = "print.pdf";
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	//==========================================================================
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Consulting getConsulting() {
		return consulting;
	}

	public void setConsulting(Consulting consulting) {
		this.consulting = consulting;
	}

	public PagedList<Consulting> getConsultingPagedList() {
		return consultingPagedList;
	}

	public List<OptionOrganizationType> getOptionOrganizationTypeList() {
		if (optionOrganizationTypeList == null) {
			optionOrganizationTypeList = this.optionOrgTypeService.listAll(); 
		}
		return optionOrganizationTypeList;
	}

	public List<OptionConsult> getOptionConsultList() {
		if(optionConsultList == null) {
			optionConsultList = this.optionConsultService.listAll();
		}
		return optionConsultList;
	}

	public List<OptionIndustry> getOptionIndustryList() {
		if (optionIndustryList == null) {
			optionIndustryList = this.optionIndustryService.listAll();
		}
		return optionIndustryList;
	}

	public ConsultingSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(ConsultingSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public InputStream getDownloadFileInputStream() {
		return downloadFileInputStream;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public InputStream getReportInputStream() {
		return reportInputStream;
	}
	
	
	

}
