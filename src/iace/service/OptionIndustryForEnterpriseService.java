package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionIndustryForEnterprise;

public class OptionIndustryForEnterpriseService extends BaseOptionService<OptionIndustryForEnterprise> {

	OptionIndustryForEnterpriseService(IOptionDao<OptionIndustryForEnterprise> dao) {
		super(dao);
	}

}
