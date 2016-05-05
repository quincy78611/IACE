package core.dao;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import iace.entity.OptionCompanyLocation;
import iace.entity.OptionConsult;
import iace.entity.OptionCooperateMode;
import iace.entity.OptionGrbDomain;
import iace.entity.OptionHadTecSrc;
import iace.entity.OptionIndustry;
import iace.entity.OptionIndustryClass;
import iace.entity.OptionOrganizationClass;
import iace.entity.OptionOrganizationType;
import iace.entity.OptionTrl;
import iace.entity.Patent;
import iace.entity.ResearchPlan;
import iace.entity.RnDResult;
import iace.entity.SysRole;
import iace.entity.SysUser;


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
    	configuration.addAnnotatedClass(com.sysvin.practice.hibernate.entity.User.class);
    	
    	configuration.addAnnotatedClass(OptionCompanyLocation.class);
    	configuration.addAnnotatedClass(OptionConsult.class);
    	configuration.addAnnotatedClass(OptionCooperateMode.class);
    	configuration.addAnnotatedClass(OptionGrbDomain.class);
    	configuration.addAnnotatedClass(OptionHadTecSrc.class);
    	configuration.addAnnotatedClass(OptionIndustry.class);
    	configuration.addAnnotatedClass(OptionIndustryClass.class);
    	configuration.addAnnotatedClass(OptionOrganizationClass.class);
    	configuration.addAnnotatedClass(OptionOrganizationType.class);
    	configuration.addAnnotatedClass(OptionTrl.class);
    	configuration.addAnnotatedClass(Patent.class);
    	configuration.addAnnotatedClass(ResearchPlan.class);
    	configuration.addAnnotatedClass(RnDResult.class);
    	configuration.addAnnotatedClass(SysRole.class);
    	configuration.addAnnotatedClass(SysUser.class);
    }
}
