package iace.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import iace.dao.DaoFactory;


public class ServiceFactory {
	
	protected static Logger log = LogManager.getLogger(ServiceFactory.class);
	
	private static OptionCompanyLocationService optionCompanyLocationService;
	private static OptionConsultService optionConsultService;
	private static OptionCooperateModeService optionCooperateModeService;
	private static OptionCountryService optionCountryService;
	private static OptionGrbDomainService optionGrbDomainService;
	private static OptionHadTecSrcService optionHadTecSrcService;
	private static OptionIndustryService optionIndustryService;
	private static OptionIndustryClassService optionIndustryClassService;
	private static OptionOrganizationClassService optionOrganizationClassService;
	private static OptionOrganizationTypeService optionOrganizationTypeService;
	private static OptionTrlService optionTrlService;
	
	private static PatentService patentService;
	private static TechFieldService techFieldService;
	private static ResearchPlanService researchPlanService;
	
	private static PatentExcelAccessService excelAccessService; 
	
	public static OptionCompanyLocationService getOptionCompanyLocationService() {
		if (optionCompanyLocationService == null) {
			optionCompanyLocationService = new OptionCompanyLocationService(DaoFactory.getOptionCompanyLocationDao());
		}
		return optionCompanyLocationService;
	}
	
	public static OptionConsultService getOptionConsultService() {
		if (optionConsultService == null) {
			optionConsultService = new OptionConsultService(DaoFactory.getOptionConsultDao());
		}
		return optionConsultService;
	}
	
	public static OptionCooperateModeService getOptionCooperateModeService() {
		if (optionCooperateModeService == null) {
			optionCooperateModeService = new OptionCooperateModeService(DaoFactory.getOptionCooperateModeDao());
		}
		return optionCooperateModeService;
	}
	
	public static OptionCountryService getOptionCountryService() {
		if (optionCountryService == null) {
			optionCountryService = new OptionCountryService(DaoFactory.getOptionCountryDao());
		}
		return optionCountryService;
	}
	
	public static OptionGrbDomainService getOptionGrbDomainService() {
		if (optionGrbDomainService == null) {
			optionGrbDomainService = new OptionGrbDomainService(DaoFactory.getOptionGrbDomainDao());
		}
		return optionGrbDomainService;
	}

	public static OptionHadTecSrcService getOptionHadTecSrcService() {
		if (optionHadTecSrcService == null) {
			optionHadTecSrcService = new OptionHadTecSrcService(DaoFactory.getOptionHadTecSrcDao());
		}
		return optionHadTecSrcService;
	}
	
	public static OptionIndustryService getOptionIndustryService() {
		if (optionIndustryService == null) {
			optionIndustryService = new OptionIndustryService(DaoFactory.getOptionIndustryDao());
		}
		return optionIndustryService;
	}
	
	public static OptionIndustryClassService getOptionIndustryClassService() {
		if (optionIndustryClassService == null) {
			optionIndustryClassService = new OptionIndustryClassService(DaoFactory.getOptionIndustryClassDao());
		}
		return optionIndustryClassService;
	}
	
	public static OptionOrganizationClassService getOptionOrganizationClassService() {
		if (optionOrganizationClassService == null) {
			optionOrganizationClassService = new OptionOrganizationClassService(DaoFactory.getOptionOrganizationClassDao());
		}
		return optionOrganizationClassService;
	}
		
	public static OptionOrganizationTypeService getOptionOrganizationTypeService() {
		if (optionOrganizationTypeService == null) {
			optionOrganizationTypeService = new OptionOrganizationTypeService(DaoFactory.getOptionOrganizationTypeDao());
		}
		return optionOrganizationTypeService;
	}
	
	public static OptionTrlService getOptionTrlService() {
		if (optionTrlService == null) {
			optionTrlService = new OptionTrlService(DaoFactory.getOptionTrlDao());
		}
		return optionTrlService;
	}	
	
	public static PatentService getPatentService() {
		if (patentService == null) {
			patentService = new PatentService(
					DaoFactory.getPatentDao(),
					DaoFactory.getTechFieldDao(),
					DaoFactory.getOptionCountryDao());
		}
		return patentService;
	}
	
	public static TechFieldService getTechFieldService() {
		if(techFieldService == null) {
			techFieldService = new TechFieldService(DaoFactory.getTechFieldDao());
		}		
		return techFieldService;
	}
	
	public static PatentExcelAccessService getExcelAccessService() {
		if (excelAccessService == null) {
			excelAccessService = new PatentExcelAccessService();
		}
		return excelAccessService;
	}

	public static ResearchPlanService getResearchPlanService() {
		if (researchPlanService == null) {
			researchPlanService = new ResearchPlanService(
					DaoFactory.getResearchPlanDao(),
					DaoFactory.getOptionGrbDomainDao(),
					DaoFactory.getOptionTrlDao());
		}
		
		return researchPlanService;
	}
	
	
}
