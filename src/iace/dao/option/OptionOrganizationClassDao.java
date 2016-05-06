package iace.dao.option;

import iace.entity.option.OptionOrganizationClass;

public class OptionOrganizationClassDao extends BaseOptionDao<OptionOrganizationClass> {

	public OptionOrganizationClassDao() {
		super(OptionOrganizationClass.class);
	}

	@Override
	public boolean hasBeenUsed(OptionOrganizationClass entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
