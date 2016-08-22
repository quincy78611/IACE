package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionIndustryForEnterprise;

public class OptionIndustryForEnterpriseService extends BaseOptionService<OptionIndustryForEnterprise> {

	public OptionIndustryForEnterpriseService(IOptionDao<OptionIndustryForEnterprise> dao) {
		super(dao);
	}

}
