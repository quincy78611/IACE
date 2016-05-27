package core.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import iace.entity.Patent;
import iace.entity.ResearchPlan;
import iace.entity.Technology;
import iace.entity.SysRole;
import iace.entity.SysUser;
import iace.entity.TechField;
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
            addEntityMapping();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            		.applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {  
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
    
    private static void addEntityMapping() {
    	configuration.addAnnotatedClass(OptionCompanyLocation.class);
    	configuration.addAnnotatedClass(OptionConsult.class);
    	configuration.addAnnotatedClass(OptionCooperateMode.class);
    	configuration.addAnnotatedClass(OptionCountry.class);
    	configuration.addAnnotatedClass(OptionGrbDomain.class);
    	configuration.addAnnotatedClass(OptionHadTecSrc.class);
    	configuration.addAnnotatedClass(OptionIndustry.class);
    	configuration.addAnnotatedClass(OptionIndustryClass.class);
    	configuration.addAnnotatedClass(OptionOrganizationClass.class);
    	configuration.addAnnotatedClass(OptionOrganizationType.class);
    	configuration.addAnnotatedClass(OptionTrl.class);
    	configuration.addAnnotatedClass(OptionSubject.class);
    	configuration.addAnnotatedClass(Patent.class);
    	configuration.addAnnotatedClass(ResearchPlan.class);
    	configuration.addAnnotatedClass(Technology.class);
    	configuration.addAnnotatedClass(SysRole.class);
    	configuration.addAnnotatedClass(SysUser.class);
    	configuration.addAnnotatedClass(TechField.class);
    }
}
