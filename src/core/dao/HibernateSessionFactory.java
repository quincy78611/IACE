package core.dao;


import java.util.Map.Entry;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import iace.entity.consulting.Consulting;
import iace.entity.coopExample.CoopEx;
import iace.entity.coopExample.CoopExAttachFile;
import iace.entity.coopExample.CoopExImg;
import iace.entity.coopExample.CoopExVideo;
import iace.entity.enterpriseNeed.EnterpriseAcademiaCoop;
import iace.entity.enterpriseNeed.EnterpriseInfo;
import iace.entity.enterpriseNeed.EnterpriseRequireTech;
import iace.entity.enterpriseNeed.EnterpriseSituation;
import iace.entity.incubationCenter.IncubationCenter;
import iace.entity.literature.Literature;
import iace.entity.option.OptionCompanyLocation;
import iace.entity.option.OptionConsult;
import iace.entity.option.OptionCooperateMode;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionDomain;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionHadTecSrc;
import iace.entity.option.OptionHrst;
import iace.entity.option.OptionIndustry;
import iace.entity.option.OptionOrganizationClass;
import iace.entity.option.OptionOrganizationType;
import iace.entity.option.OptionSchool;
import iace.entity.option.OptionSubject;
import iace.entity.option.OptionSysAction;
import iace.entity.option.OptionSysNamespace;
import iace.entity.option.OptionTrl;
import iace.entity.patent.Patent;
import iace.entity.patent.TechField;
import iace.entity.qnrCooperateWay.QnrCooperateWay;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.Technology;
import iace.entity.sys.SysAuth;
import iace.entity.sys.SysFunction;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysRole;
import iace.entity.sys.SysUser;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleMainProject;
import iace.entity.talentedPeople.TalentedPeopleRdResult;
import iace.entity.talentedPeople.TalentedPeopleTransferCase;


public class HibernateSessionFactory {

    private static Configuration configuration = new Configuration();  
	private static String CONFIG_FILE_LOCATION = "configs/hibernate.cfg.xml";  
	
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();  
	private static org.hibernate.SessionFactory sessionFactory;
	
    static {  
    	rebuildSessionFactory();
    } 
    
    public static void rebuildSessionFactory() {  
        try {            
            configuration.configure(CONFIG_FILE_LOCATION);
//            configuration.configure();
//            showHibernateConfig();
            addEntityMapping();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            		.applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            if (sessionFactory == null) {
            	throw new NullPointerException("sessionFactory is null!");
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.err.println("%%%% Error Creating SessionFactory %%%%"); 
        }  
    }
    
    public static Session getSession() throws HibernateException {  
        Session session = (Session) threadLocal.get();  
  
        if (session == null || !session.isOpen()) {  
            if (sessionFactory == null) {  
                rebuildSessionFactory();  
            }  
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;  
            threadLocal.set(session);  
        }  
  
        return session;
    } 

    public static void closeSession() throws HibernateException {  
        Session session = (Session) threadLocal.get();  
        threadLocal.set(null);  
  
        if (session != null) {  
            session.close();  
        }  
    }
    
    @Deprecated
    public static SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }    
 
    @Deprecated
    public static Configuration getConfiguration() {  
        return configuration;  
    } 
    
    public static void showHibernateConfig() {
    	Properties prop = configuration.getProperties();
    	System.out.println("");
    	System.out.println("==================================================");
    	for (Entry<Object, Object> e : prop.entrySet()) {
			String key = (String) e.getKey();
			String value = (String) e.getValue();
			System.out.println(key+":"+value);
		}
    	System.out.println("==================================================");
    	System.out.println("");
    }
    
    public static String getConnectionDriverClass() {
    	Properties prop = configuration.getProperties();
    	return prop.getProperty("hibernate.connection.driver_class");   	
    }
    
    public static String getConnectionUrl() {
    	Properties prop = configuration.getProperties();
    	return prop.getProperty("hibernate.connection.url");
    }
    
    public static String getConnectionUserName() {
    	Properties prop = configuration.getProperties();
    	return prop.getProperty("hibernate.connection.username");
    }
    
    public static String getConnectionPassword() {
    	Properties prop = configuration.getProperties();
    	return prop.getProperty("hibernate.connection.password");
    }

    public static String getDefaultSchema() {
    	Properties prop = configuration.getProperties();
    	return prop.getProperty("hibernate.default_schema");
    }
       
    private static void addEntityMapping() {
    	configuration.addAnnotatedClass(OptionCompanyLocation.class);
    	configuration.addAnnotatedClass(OptionConsult.class);
    	configuration.addAnnotatedClass(OptionCooperateMode.class);
    	configuration.addAnnotatedClass(OptionCountry.class);
    	configuration.addAnnotatedClass(OptionGrbDomain.class);
    	configuration.addAnnotatedClass(OptionHadTecSrc.class);
    	configuration.addAnnotatedClass(OptionHrst.class);
    	configuration.addAnnotatedClass(OptionIndustry.class);
    	configuration.addAnnotatedClass(OptionDomain.class);
    	configuration.addAnnotatedClass(OptionOrganizationClass.class);
    	configuration.addAnnotatedClass(OptionOrganizationType.class);
    	configuration.addAnnotatedClass(OptionTrl.class);
    	configuration.addAnnotatedClass(OptionSubject.class);
    	configuration.addAnnotatedClass(OptionSchool.class);
    	configuration.addAnnotatedClass(OptionSysNamespace.class);
    	configuration.addAnnotatedClass(OptionSysAction.class);
    	
    	configuration.addAnnotatedClass(EnterpriseInfo.class);
    	configuration.addAnnotatedClass(EnterpriseRequireTech.class);
    	configuration.addAnnotatedClass(EnterpriseSituation.class);
    	configuration.addAnnotatedClass(EnterpriseAcademiaCoop.class);
    	
    	configuration.addAnnotatedClass(Patent.class);
    	configuration.addAnnotatedClass(TechField.class);
    	
    	configuration.addAnnotatedClass(ResearchPlan.class);
    	configuration.addAnnotatedClass(Technology.class);

    	configuration.addAnnotatedClass(Consulting.class);
    	
    	configuration.addAnnotatedClass(QnrTable.class);
    	configuration.addAnnotatedClass(QnrTableColumn.class);
    	
    	configuration.addAnnotatedClass(QnrCooperateWay.class);
    	
    	configuration.addAnnotatedClass(SysUser.class);
    	configuration.addAnnotatedClass(SysRole.class);
    	configuration.addAnnotatedClass(SysAuth.class);
    	configuration.addAnnotatedClass(SysFunction.class);
    	configuration.addAnnotatedClass(SysLog.class);
    	
    	configuration.addAnnotatedClass(CoopEx.class);
    	configuration.addAnnotatedClass(CoopExImg.class);
    	configuration.addAnnotatedClass(CoopExVideo.class);
    	configuration.addAnnotatedClass(CoopExAttachFile.class);
   	
    	configuration.addAnnotatedClass(TalentedPeople.class);
    	configuration.addAnnotatedClass(TalentedPeopleRdResult.class);
    	configuration.addAnnotatedClass(TalentedPeopleTransferCase.class);
    	configuration.addAnnotatedClass(TalentedPeopleMainProject.class);
    	
    	configuration.addAnnotatedClass(IncubationCenter.class);
    	
    	configuration.addAnnotatedClass(Literature.class);
    }
}
