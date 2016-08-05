package iace.dao.option;

import iace.entity.option.OptionIndustryForEnterprise;

public class OptionIndustryForEnterpriseDao extends BaseOptionDao<OptionIndustryForEnterprise> {

	public OptionIndustryForEnterpriseDao() {
		super(OptionIndustryForEnterprise.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
