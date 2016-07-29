package iace.action;

import java.util.List;

import iace.entity.enterpriseNeed.EnterpriseInfo;
import iace.entity.option.OptionCompanyLocation;
import iace.entity.option.OptionCooperateMode;
import iace.entity.option.OptionHadTecSrc;
import iace.entity.option.OptionIndustryForEnterprise;
import iace.service.EnterpriseInfoService;
import iace.service.OptionCompanyLocationService;
import iace.service.OptionCooperateModeService;
import iace.service.OptionHadTecSrcService;
import iace.service.OptionIndustryForEnterpriseService;
import iace.service.ServiceFactory;

public class EnterpriseNeedAction extends BaseIaceAction {

	private static final long serialVersionUID = -4591959836078338744L;

	private EnterpriseInfoService enterpriseInfoService = ServiceFactory.getEnterpriseInfoService();
	private OptionIndustryForEnterpriseService optionIndustryService = ServiceFactory.getOptionIndustryForEnterpriseService();
	private OptionCompanyLocationService optionCompanyLocationService = ServiceFactory.getOptionCompanyLocationService();
	private OptionHadTecSrcService optionHadTecSrcService = ServiceFactory.getOptionHadTecSrcService();
	private OptionCooperateModeService optionCooperateModeService = ServiceFactory.getOptionCooperateModeService();
	
	private List<OptionIndustryForEnterprise> optionIndustryList;
	private List<OptionCompanyLocation> optionCompanyLocationList;
	private List<OptionHadTecSrc> optionHadTecSrcList;
	private List<OptionCooperateMode> optionCooperateModeList;
	
	private EnterpriseInfo enterpriseInfo;	
	
	public EnterpriseNeedAction() {
		super.setTitle("企業需求單");
		this.optionIndustryList = this.optionIndustryService.listAll();
		this.optionCompanyLocationList = this.optionCompanyLocationService.listAll();
		this.optionHadTecSrcList = this.optionHadTecSrcService.listAll();
		this.optionCooperateModeList = this.optionCooperateModeService.listAll();		
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		//TODO
		double hadTecSrcRation = this.enterpriseInfo.getEnterpriseSituation().getHadTecSrcRation();
		validateNumberRange(hadTecSrcRation, 1, 0, "enterpriseInfo.enterpriseSituation.hadTecSrcRation");
	}
	
	public String createSubmit() {
		try {
			this.enterpriseInfoService.create(this.enterpriseInfo);
			this.addActionMessage("CREATE SUCCESS!");
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public EnterpriseInfo getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}

	public List<OptionIndustryForEnterprise> getOptionIndustryList() {
		return optionIndustryList;
	}

	public List<OptionCompanyLocation> getOptionCompanyLocationList() {
		return optionCompanyLocationList;
	}

	public List<OptionHadTecSrc> getOptionHadTecSrcList() {
		return optionHadTecSrcList;
	}

	public List<OptionCooperateMode> getOptionCooperateModeList() {
		return optionCooperateModeList;
	}
	
	
	
	
	
	
	
	
	
	

}
