package iace.dao.option;

import iace.entity.option.OptionSubject;

public class OptionSubjectDao extends BaseOptionDao<OptionSubject> {
	
	public OptionSubjectDao() {
		super(OptionSubject.class);
	}

	@Override
	public boolean hasBeenUsed(OptionSubject entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
