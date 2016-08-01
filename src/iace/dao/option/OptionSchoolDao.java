package iace.dao.option;

import iace.entity.option.OptionSchool;

public class OptionSchoolDao extends BaseOptionDao<OptionSchool> {

	public OptionSchoolDao() {
		super(OptionSchool.class);
	}

	@Override
	public boolean hasBeenUsed(OptionSchool entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
