package iace.dao.option;

import iace.entity.option.OptionTrl;

public class OptionTrlDao extends BaseOptionDao<OptionTrl> {

	public OptionTrlDao() {
		super(OptionTrl.class);
	}

	@Override
	public boolean hasBeenUsed(OptionTrl entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
