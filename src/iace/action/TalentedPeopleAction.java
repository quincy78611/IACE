package iace.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.exception.JDBCConnectionException;

import core.util.ExcelUtil;
import core.util.PagedList;
import iace.entity.BaseBatchImportResult;
import iace.entity.option.BaseOption;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionDomain;
import iace.entity.sys.SysRole;
import iace.entity.sys.SysUser;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;
import iace.interceptor.SessionInterceptor;
import iace.service.ServiceFactory;
import iace.service.option.OptionCountryService;
import iace.service.option.OptionDomainService;
import iace.service.sys.SysRoleService;
import iace.service.sys.SysUserService;
import iace.service.talentedPeople.TalentedPeopleService;

public class TalentedPeopleAction extends BaseIaceAction {

	private static final long serialVersionUID = 570851154374907844L;

	private TalentedPeopleService talentedPeopleService = ServiceFactory.getTalentedPeopleService();
	private OptionDomainService optionDomainService = ServiceFactory.getOptionDomainService();
	private OptionCountryService optionCountryService = ServiceFactory.getOptionCountryService();
	private SysUserService sysUserService = ServiceFactory.getSysUserService();
	private SysRoleService sysRoleService = ServiceFactory.getSysRoleService();

	private TalentedPeopleSearchModel searchCondition = new TalentedPeopleSearchModel();
	private PagedList<TalentedPeople> talentedPeoplePagedList;

	private long id;
	private long sysRoleId;
	private TalentedPeople talentedPeople;
	private SysUser sysUser;

	private List<OptionDomain> mainDomainList;
	private List<OptionCountry> countryList;
	private List<BaseOption> rdResultTypeList;
	private List<BaseOption> yearList;
	private List<BaseOption> monthList;
	private List<SysRole> sysRoleList;

	private File uploadFile;
	private String uploadFileContentType;
	private String uploadFileFileName;
	private BaseBatchImportResult<TalentedPeople> batchImportResult;

	private String downloadFileName;
	private InputStream downloadFileInputStream;

	private String reportFileName;
	private InputStream reportInputStream;

	public TalentedPeopleAction() {
		super.setTitle("產學合作人才資料");
	}

	public String init() {
		return SUCCESS;
	}

	public String index() {
		try {
			this.talentedPeoplePagedList = this.talentedPeopleService.searchBy(this.searchCondition);

			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String showDetail() {
		try {
			this.talentedPeople = this.talentedPeopleService.get(this.id);
			if (this.talentedPeople == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
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
			this.talentedPeopleService.create(this.talentedPeople, super.getCurrentSysUser(), true, super.getSysLog());

			super.addActionMessage("CREATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String update() {
		try {
			this.talentedPeople = this.talentedPeopleService.get(this.id);
			if (this.talentedPeople == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public void validateUpdateSubmit() {
		validateBeforeSubmit();
	}

	public String updateSubmit() {
		try {
			this.talentedPeopleService.update(this.talentedPeople, super.getCurrentSysUser(), true, super.getSysLog());
			this.talentedPeople = this.talentedPeopleService.get(this.talentedPeople.getId());
			
			super.addActionMessage("UPDATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String delete() {
		try {
			this.talentedPeople = this.talentedPeopleService.get(this.id);
			if (this.talentedPeople == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String deleteSubmit() {
		try {
			this.talentedPeopleService.delete(this.id, true, super.getSysLog());

			super.addActionMessage("DELETE SUCCESS");
			return index();
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public void validateBeforeSubmit() {
		super.validateNotBlankNLength(this.talentedPeople.getNameCh(), 100, "talentedPeople.nameCh");
		super.validateNotBlankNLength(this.talentedPeople.getNameEn(), 100, "talentedPeople.nameEn");
		super.validateNotBlankNLength(this.talentedPeople.getTel(), 100, "talentedPeople.tel");
		super.validateNotBlankNLength(this.talentedPeople.getEmail(), 100, "talentedPeople.email");
		super.validateEmail(this.talentedPeople.getEmail(), "talentedPeople.email");
		super.validateTextMaxLength(this.talentedPeople.getWorkOrg(), 100, "talentedPeople.workOrg");
		super.validateTextMaxLength(this.talentedPeople.getJob(), 100, "talentedPeople.job");
		super.validateTextMaxLength(this.talentedPeople.getUrl(), 1000, "talentedPeople.url");
		super.validateTextMaxLength(this.talentedPeople.getSpecialty(), 1000, "talentedPeople.specialty");
	}

	public String batchImport() {
		return SUCCESS;
	}

	public String batchImportSubmit() {
		try {
			this.batchImportResult = this.talentedPeopleService.batchImport(this.uploadFile, this.sysRoleId);
			if (this.batchImportResult.getErrMsgs().size() > 0) {
				this.addActionError("部分或全部匯入資料有誤，請看下方錯誤列表");
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String downloadBatchSample() {
		try {
			ServletContext context = ServletActionContext.getServletContext();
			this.downloadFileName = "產學人才資料庫基本資料_批次匯入格式.xlsx";
			String filePath = context.getRealPath("/files/" + this.downloadFileName);
			log.debug(filePath);
			downloadFileInputStream = new FileInputStream(new File(filePath));
			this.downloadFileName = new String(this.downloadFileName.getBytes(), "ISO-8859-1"); // 解決中文檔名瀏覽器無法正常顯示問題
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}

	public String printReport() {
		try {
			this.reportInputStream = this.talentedPeopleService.printReport(this.id);
			this.reportFileName = "print.pdf";
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String exportRawData() {
		try {
			XSSFWorkbook wb = this.talentedPeopleService.exportRawData(this.searchCondition);
			this.downloadFileInputStream = ExcelUtil.workbookToInputStream(wb);
			this.downloadFileName = "raw_data.xlsx";

			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String login() {
		return SUCCESS;
	}

	public String loginSubmit() {
		try {
			if (this.sysUserService.loginCheck(this.sysUser.getAccount(), this.sysUser.getPassword())) {
				this.sysUser = this.sysUserService.getBy(this.sysUser.getAccount(), this.sysUser.getPassword());
				super.session.put(SessionInterceptor.SESSION_KEY_SYS_USER, this.sysUser);

				// sysLog
				super.getSysLog().setSysUser(sysUser);
				super.getSysLog().setEnableLog(true);

				this.talentedPeople = this.talentedPeopleService.get(this.sysUser);
				if (this.talentedPeople == null) {
					super.addActionError("此帳號不是產學人才");
					this.session.clear();
					return INPUT;
				}

				return SUCCESS;
			} else {
				super.addActionError("帳號或密碼錯誤!");
				return INPUT;
			}
		} catch (JDBCConnectionException e) {
			log.error("資料庫連線錯誤，請重新嘗試!", e);
			super.addActionError("資料庫連線錯誤，請重新嘗試!");
			return INPUT;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String selfUpdateSubmit() {
		if (super.getCurrentSysUser().getId() != this.talentedPeople.getSysUser().getId()) {
			super.addActionError("您只能維護自己的產學人才資料");
			return ERROR;
		}
		return updateSubmit();
	}

	// =========================================================================

	public TalentedPeopleSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(TalentedPeopleSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<TalentedPeople> getTalentedPeoplePagedList() {
		return talentedPeoplePagedList;
	}

	public void setTalentedPeoplePagedList(PagedList<TalentedPeople> talentedPeoplePagedList) {
		this.talentedPeoplePagedList = talentedPeoplePagedList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TalentedPeople getTalentedPeople() {
		return talentedPeople;
	}

	public void setTalentedPeople(TalentedPeople talentedPeople) {
		this.talentedPeople = talentedPeople;
	}

	public List<OptionDomain> getMainDomainList() {
		if (mainDomainList == null) {
			mainDomainList = this.optionDomainService.listAll();
		}
		return mainDomainList;
	}

	public List<OptionCountry> getCountryList() {
		if (countryList == null) {
			countryList = this.optionCountryService.listAll();
		}
		return countryList;
	}

	public List<BaseOption> getRdResultTypeList() {
		if (rdResultTypeList == null) {
			rdResultTypeList = new ArrayList<BaseOption>();
			rdResultTypeList.add(new BaseOption("專利獲准", "專利獲准(請填5~8)"));
			rdResultTypeList.add(new BaseOption("專利申請中", "專利申請中(請填5,7)"));
			rdResultTypeList.add(new BaseOption("技術/KNOW-HOW ", "技術/KNOW-HOW "));
			rdResultTypeList.add(new BaseOption("積體電路布局", "積體電路布局"));
			rdResultTypeList.add(new BaseOption("軟體", "軟體"));
			rdResultTypeList.add(new BaseOption("其他", "其他"));
		}
		return rdResultTypeList;
	}

	public List<BaseOption> getYearList() {
		if (yearList == null) {
			yearList = new ArrayList<BaseOption>();
			for (int i=1990; i<2020; i++) {
				yearList.add(new BaseOption(i+"", i+"年"));
			}
		}
		return yearList;
	}

	public List<BaseOption> getMonthList() {
		if (monthList == null) {
			monthList = new ArrayList<BaseOption>();
			monthList.add(new BaseOption("1", "1月"));
			monthList.add(new BaseOption("2", "2月"));
			monthList.add(new BaseOption("3", "3月"));
			monthList.add(new BaseOption("4", "4月"));
			monthList.add(new BaseOption("5", "5月"));
			monthList.add(new BaseOption("6", "6月"));
			monthList.add(new BaseOption("7", "7月"));
			monthList.add(new BaseOption("8", "8月"));
			monthList.add(new BaseOption("9", "9月"));
			monthList.add(new BaseOption("10", "10月"));
			monthList.add(new BaseOption("11", "11月"));
			monthList.add(new BaseOption("12", "12月"));
		}
		return monthList;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BaseBatchImportResult<TalentedPeople> getBatchImportResult() {
		return batchImportResult;
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

	public long getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public List<SysRole> getSysRoleList() {
		if (sysRoleList == null) {
			sysRoleList = this.sysRoleService.listAll();
		}
		return sysRoleList;
	}


	public SysUser getSysUser() {
		return sysUser;
	}
	

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}
