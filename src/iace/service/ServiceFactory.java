package iace.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import iace.dao.DaoFactory;


public class ServiceFactory {
	
	protected static Logger log = LogManager.getLogger(ServiceFactory.class);
	
	private static OptionIndustryService optionIndustryService;

	public static OptionIndustryService getOptionIndustryService() {
		if (optionIndustryService == null) {
			optionIndustryService = new OptionIndustryService(DaoFactory.getOptionIndustryDao());
		}
		return optionIndustryService;
	}
	
	

}
