package iace.dao.option;

import iace.entity.option.OptionIndustry;

public class OptionIndustryDao extends BaseOptionDao<OptionIndustry> {

	public OptionIndustryDao() {
		super(OptionIndustry.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
