package iace.dao.option;

import iace.entity.option.OptionCompanyLocation;

public class OptionCompanyLocationDao extends BaseOptionDao<OptionCompanyLocation> {	
	public OptionCompanyLocationDao() {
		super(OptionCompanyLocation.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
