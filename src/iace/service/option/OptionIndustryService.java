package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionIndustry;

public class OptionIndustryService extends BaseOptionService<OptionIndustry> {
	
	public OptionIndustryService(IOptionDao<OptionIndustry> dao) {
		super(dao);
	}
}
