package iace.dao.option;

import iace.entity.option.OptionIndustryClass;

public class OptionIndustryClassDao extends BaseOptionDao<OptionIndustryClass> {

	public OptionIndustryClassDao() {
		super(OptionIndustryClass.class);
	}

	@Override
	public boolean hasBeenUsed(OptionIndustryClass entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
