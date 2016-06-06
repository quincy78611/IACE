package iace.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import iace.dao.consulting.ConsultingDao;
import iace.dao.consulting.IConsultingDao;
import iace.dao.option.IOptionDao;
import iace.dao.option.OptionCompanyLocationDao;
import iace.dao.option.OptionConsultDao;
import iace.dao.option.OptionCooperateModeDao;
import iace.dao.option.OptionCountryDao;
import iace.dao.option.OptionGrbDomainDao;
import iace.dao.option.OptionHadTecSrcDao;
import iace.dao.option.OptionIndustryClassDao;
import iace.dao.option.OptionIndustryDao;
import iace.dao.option.OptionOrganizationClassDao;
import iace.dao.option.OptionOrganizationTypeDao;
import iace.dao.option.OptionSubjectDao;
import iace.dao.option.OptionTrlDao;
import iace.dao.patent.IPatentDao;
import iace.dao.patent.PatentDao;
import iace.dao.questionnaire.IQnrTemplateDao;
import iace.dao.questionnaire.QnrTemplateDao;
import iace.dao.researchPlan.IResearchPlanDao;
import iace.dao.researchPlan.ITechnologyDao;
import iace.dao.researchPlan.ResearchPlanDao;
import iace.dao.researchPlan.TechnologyDao;
import iace.dao.techField.ITechFieldDao;
import iace.dao.techField.TechFieldDao;
import iace.entity.option.OptionCompanyLocation;
import iace.entity.option.OptionConsult;
import iace.entity.option.OptionCooperateMode;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionHadTecSrc;
import iace.entity.option.OptionIndustry;
import iace.entity.option.OptionIndustryClass;
import iace.entity.option.OptionOrganizationClass;
import iace.entity.option.OptionOrganizationType;
import iace.entity.option.OptionSubject;
import iace.entity.option.OptionTrl;

public class DaoFactory {
	protected static Logger log = LogManager.getLogger(DaoFactory.class);
	
	private static IOptionDao<OptionCompanyLocation> optionCompanyLocationDao;
	private static IOptionDao<OptionConsult> optionConsultDao;
	private static IOptionDao<OptionCooperateMode> optionCooperateModeDao;
	private static IOptionDao<OptionCountry> optionCountryDao;
	private static IOptionDao<OptionGrbDomain> optionGrbDomainDao;
	private static IOptionDao<OptionHadTecSrc> optionHadTecSrcDao;
	private static IOptionDao<OptionIndustry> optionIndustryDao;
	@Deprecated
	private static IOptionDao<OptionIndustryClass> optionIndustryClassDao;
	private static IOptionDao<OptionOrganizationClass> optionOrganizationClassDao;
	private static IOptionDao<OptionOrganizationType> optionOrganizationTypeDao;
	private static IOptionDao<OptionTrl> optionTrlDao;
	private static IOptionDao<OptionSubject> optionSubjectDao; 
	
	private static ITechFieldDao techFieldDao;
	private static IPatentDao patentDao;
	private static IResearchPlanDao researchPlanDao;
	private static ITechnologyDao technologyDao;
	private static IConsultingDao consultingDao;
	
	private static IQnrTemplateDao qnrTemplateDao;
	
	public static IOptionDao<OptionCompanyLocation> getOptionCompanyLocationDao() {
		if (optionCompanyLocationDao == null) {
			optionCompanyLocationDao = new OptionCompanyLocationDao();
		}
		return optionCompanyLocationDao;
	}
	
	public static IOptionDao<OptionConsult> getOptionConsultDao() {
		if (optionConsultDao == null) {
			optionConsultDao = new OptionConsultDao();
		}
		return optionConsultDao;
	}
	
	public static IOptionDao<OptionCooperateMode> getOptionCooperateModeDao() {
		if (optionCooperateModeDao == null) {
			optionCooperateModeDao = new OptionCooperateModeDao();
		}
		return optionCooperateModeDao;
	}
	
	public static IOptionDao<OptionCountry> getOptionCountryDao() {
		if (optionCountryDao == null) {
			optionCountryDao = new OptionCountryDao();
		}
		return optionCountryDao;
	}
	
	public static IOptionDao<OptionGrbDomain> getOptionGrbDomainDao() {
		if (optionGrbDomainDao == null) {
			optionGrbDomainDao = new OptionGrbDomainDao();
		}
		return optionGrbDomainDao;
	}
	
	public static IOptionDao<OptionHadTecSrc> getOptionHadTecSrcDao() {
		if (optionHadTecSrcDao == null) {
			optionHadTecSrcDao = new OptionHadTecSrcDao();
		}
		return optionHadTecSrcDao;
	}
	
	public static IOptionDao<OptionIndustry> getOptionIndustryDao() {
		if (optionIndustryDao == null) {
			optionIndustryDao = new OptionIndustryDao();
		}
		return optionIndustryDao;
	}
	
	@Deprecated
	public static IOptionDao<OptionIndustryClass> getOptionIndustryClassDao() {
		if (optionIndustryClassDao == null) {
			optionIndustryClassDao = new OptionIndustryClassDao();
		}
		return optionIndustryClassDao;
	}

	public static IOptionDao<OptionOrganizationClass> getOptionOrganizationClassDao() {
		if (optionOrganizationClassDao == null) {
			optionOrganizationClassDao = new OptionOrganizationClassDao();
		}
		return optionOrganizationClassDao;
	}

	public static IOptionDao<OptionOrganizationType> getOptionOrganizationTypeDao() {
		if (optionOrganizationTypeDao == null) {
			optionOrganizationTypeDao = new OptionOrganizationTypeDao();
		}
		return optionOrganizationTypeDao;
	}

	public static IOptionDao<OptionTrl> getOptionTrlDao() {
		if (optionTrlDao == null) {
			optionTrlDao = new OptionTrlDao();
		}
		return optionTrlDao;
	}
	
	public static IOptionDao<OptionSubject> getOptionSubjectDao() {
		if (optionSubjectDao == null) {
			optionSubjectDao = new OptionSubjectDao();
		}
		return optionSubjectDao;
	}
	
	public static ITechFieldDao getTechFieldDao() {
		if (techFieldDao == null) {
			techFieldDao = new TechFieldDao();
		}		
		return techFieldDao;
	}
	
	public static IPatentDao getPatentDao() {
		if (patentDao == null) {
			patentDao = new PatentDao();
		}
		return patentDao;
	}

	public static IResearchPlanDao getResearchPlanDao() {
		if (researchPlanDao == null) {
			researchPlanDao = new ResearchPlanDao();
		}		
		return researchPlanDao;
	}
	
	public static ITechnologyDao getTechnologyDao() {
		if (technologyDao == null) {
			technologyDao = new TechnologyDao();
		}
		return technologyDao;
	}

	public static IConsultingDao getConsultingDao() {
		if (consultingDao == null) {
			consultingDao = new ConsultingDao();
		}
		return consultingDao;
	}

	public static IQnrTemplateDao getQnrTemplateDao() {
		if (qnrTemplateDao == null) {
			qnrTemplateDao = new QnrTemplateDao();
		}
		return qnrTemplateDao;
	}

	
	

}
