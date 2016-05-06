package iace.dao.option;

import iace.entity.option.OptionOrganizationType;

public class OptionOrganizationTypeDao extends BaseOptionDao<OptionOrganizationType> {

	public OptionOrganizationTypeDao() {
		super(OptionOrganizationType.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasBeenUsed(OptionOrganizationType entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
