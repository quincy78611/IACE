package iace.dao.option;

import iace.entity.option.OptionCountry;

public class OptionCountryDao extends BaseOptionDao<OptionCountry> {

	public OptionCountryDao() {
		super(OptionCountry.class);
	}

	@Override
	public boolean hasBeenUsed(OptionCountry entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
