package iace.dao.option;

import iace.entity.option.School;

public class SchoolDao extends BaseOptionDao<School> {

	public SchoolDao() {
		super(School.class);
	}

	@Override
	public boolean hasBeenUsed(School entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
