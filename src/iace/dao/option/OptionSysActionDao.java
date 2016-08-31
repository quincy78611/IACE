package iace.dao.option;

import iace.entity.option.OptionSysAction;

public class OptionSysActionDao extends BaseOptionDao<OptionSysAction> {

	public OptionSysActionDao() {
		super(OptionSysAction.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
