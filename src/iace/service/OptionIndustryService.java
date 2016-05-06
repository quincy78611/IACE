package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionIndustry;

public class OptionIndustryService extends BaseOptionService<OptionIndustry> {
	
	OptionIndustryService(IOptionDao<OptionIndustry> dao) {
		super(dao);
	}
}
