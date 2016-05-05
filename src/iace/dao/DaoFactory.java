package iace.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoFactory {
	protected static Logger log = LogManager.getLogger(DaoFactory.class);

	private static IOptionIndustryDao optionIndustryDao;
	
	public static IOptionIndustryDao getOptionIndustryDao() {
		if (optionIndustryDao == null) {
			optionIndustryDao = new OptionIndustryDao();
		}
		return optionIndustryDao;
	}
}
