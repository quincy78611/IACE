package iace.dao.option;

import iace.entity.option.OptionCooperateMode;

public class OptionCooperateModeDao extends BaseOptionDao<OptionCooperateMode> {

	public OptionCooperateModeDao() {
		super(OptionCooperateMode.class);
	}

	@Override
	public boolean hasBeenUsed(OptionCooperateMode entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
