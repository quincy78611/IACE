package iace.service;

import iace.dao.DaoFactory;
import iace.service.coopExample.CoopExAttachFileService;
import iace.service.coopExample.CoopExService;
import iace.service.coopExample.CoopExVideoService;


public class ServiceFactory {
	
	private static SysFunctionService sysFunctionService;
	private static SysRoleService sysRoleService;
	private static SysUserService sysUserService;
	
	private static OptionCompanyLocationService optionCompanyLocationService;
	private static OptionConsultService optionConsultService;
	private static OptionCooperateModeService optionCooperateModeService;
	private static OptionCountryService optionCountryService;
	private static OptionGrbDomainService optionGrbDomainService;
	private static OptionHadTecSrcService optionHadTecSrcService;
	private static OptionIndustryService optionIndustryService;
	private static OptionIndustryForEnterpriseService optionIndustryForEnterpriseService;
	private static OptionOrganizationClassService optionOrganizationClassService;
	private static OptionOrganizationTypeService optionOrganizationTypeService;
	private static OptionTrlService optionTrlService;
	private static OptionSubjectService optionSubjectService;
	private static OptionSchoolService optionSchoolService;
	private static OptionSchoolExcelService optionSchoolExcelService;
	
	private static PatentService patentService;
	private static TechFieldService techFieldService;
	private static PatentExcelService patentExcelService;
	
	private static ResearchPlanService researchPlanService;
	private static TechnologyService technologyService;	
	private static ResearchPlanExcelService researchPlanExcelService;
	
	private static ConsultingService consultingService;
	
	private static EnterpriseInfoService enterpriseInfoService;
	
	private static QnrTemplateService qnrTemplateService;
	private static QnrService qnrService;
	
	private static QnrCooperateWayService qnrCooperateWayService;
	private static QnrCooperateWayMeritService qnrCooperateWayMeritService;
	private static QnrCooperateWayExcelService qnrCooperateWayExcelService;
	
	private static CoopExService coopExService;
	private static CoopExVideoService coopExVideoService;
	private static CoopExAttachFileService coopExAttachFileService;
	
	public static SysFunctionService getSysFunctionService() {
		if (sysFunctionService == null) {
			sysFunctionService = new SysFunctionService(DaoFactory.getSysFunctionDao());
		}
		return sysFunctionService;
	}
	
	public static SysRoleService getSysRoleService() {
		if (sysRoleService == null) {
			sysRoleService = new SysRoleService(
					DaoFactory.getSysRoleDao(), 
					DaoFactory.getSysFunctionDao());
		}
		return sysRoleService;
	}
	
	public static SysUserService getSysUserService() {
		if (sysUserService == null) {
			sysUserService = new SysUserService(DaoFactory.getSysUserDao());
		}
		return sysUserService;
	}
	
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
	
	public static OptionIndustryForEnterpriseService getOptionIndustryForEnterpriseService() {
		if (optionIndustryForEnterpriseService == null) {
			optionIndustryForEnterpriseService = new OptionIndustryForEnterpriseService(DaoFactory.getOptionIndustryForEnterpriseDao());
		}
		return optionIndustryForEnterpriseService;
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
	
	public static OptionSubjectService getOptionSubjectService() {
		if (optionSubjectService == null) {
			optionSubjectService = new OptionSubjectService(DaoFactory.getOptionSubjectDao());
		}
		return optionSubjectService;
	}
	
	public static OptionSchoolService getOptionSchoolService() {
		if (optionSchoolService == null) {
			optionSchoolService = new OptionSchoolService(DaoFactory.getOptionSchoolDao());
		}
		return optionSchoolService;
	}
	
	public static OptionSchoolExcelService getOptionSchoolExcelService() {
		if (optionSchoolExcelService == null) {
			optionSchoolExcelService = new OptionSchoolExcelService();
		}
		return optionSchoolExcelService;
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
	
	public static PatentExcelService getPatentExcelService() {
		if (patentExcelService == null) {
			patentExcelService = new PatentExcelService();
		}
		return patentExcelService;
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
	
	public static TechnologyService getTechnologyService() {
		if (technologyService == null) {
			technologyService = new TechnologyService(DaoFactory.getTechnologyDao());
		}
		return technologyService;
	}
	
	public static ResearchPlanExcelService getResearchPlanExcelService() {
		if (researchPlanExcelService == null) {
			researchPlanExcelService = new ResearchPlanExcelService();
		}
		return researchPlanExcelService;
	}

	public static ConsultingService getConsultingService() {
		if (consultingService == null) {
			consultingService = new ConsultingService(DaoFactory.getConsultingDao());
		}
		return consultingService;
	}
	
	public static EnterpriseInfoService getEnterpriseInfoService() {
		if (enterpriseInfoService == null) {
			enterpriseInfoService = new EnterpriseInfoService(
					DaoFactory.getEnterpriseInfoDao(),
					DaoFactory.getOptionIndustryForEnterpriseDao(),
					DaoFactory.getOptionCompanyLocationDao(),
					DaoFactory.getOptionHadTecSrcDao(),
					DaoFactory.getOptionCooperateModeDao());
		}
		return enterpriseInfoService;
	}

	public static QnrTemplateService getQnrTemplateService() {
		if (qnrTemplateService == null) {
			qnrTemplateService = new QnrTemplateService(
					DaoFactory.getQnrTemplateDao(), 
					DaoFactory.getQnrDao());
		}
		return qnrTemplateService;
	}

	public static QnrService getQnrService() {
		if (qnrService == null) {
			qnrService = new QnrService(DaoFactory.getQnrDao());
		}
		return qnrService;
	}
	
	public static QnrCooperateWayService getQnrCooperateWayService(){
		if (qnrCooperateWayService == null) {
			qnrCooperateWayService = new QnrCooperateWayService(DaoFactory.getQnrCooperateWayDao());
		}
		return qnrCooperateWayService;
	}
	
	public static QnrCooperateWayMeritService getQnrCooperateWayMeritService(){
		if (qnrCooperateWayMeritService == null) {
			qnrCooperateWayMeritService = new QnrCooperateWayMeritService(
					DaoFactory.getQnrCooperateWayMeritDao());
		}
		return qnrCooperateWayMeritService;
	}
	
	public static QnrCooperateWayExcelService getQnrCooperateWayExcelService() {
		if (qnrCooperateWayExcelService == null) {
			qnrCooperateWayExcelService = new QnrCooperateWayExcelService();
		}
		return qnrCooperateWayExcelService;
	}

	public static CoopExService getCoopExService() {
		if (coopExService == null) {
			coopExService = new CoopExService(
					DaoFactory.getCoopExDao(),
					DaoFactory.getCoopExImgDao(),
					DaoFactory.getCoopExVideoDao(),
					DaoFactory.getCoopExAttachFileDao());
		}
		return coopExService;
	}

	public static CoopExVideoService getCoopExVideoService() {
		if (coopExVideoService == null) {
			coopExVideoService = new CoopExVideoService(DaoFactory.getCoopExVideoDao());
		}
		return coopExVideoService;
	}

	public static CoopExAttachFileService getCoopExAttachFileService() {
		if (coopExAttachFileService == null) {
			coopExAttachFileService = new CoopExAttachFileService(DaoFactory.getCoopExAttachFileDao());
		}
		return coopExAttachFileService;
	}
	
	
}
