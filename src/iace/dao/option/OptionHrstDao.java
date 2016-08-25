package iace.dao.option;

import iace.entity.option.OptionHrst;

public class OptionHrstDao extends BaseOptionDao<OptionHrst> {

	public OptionHrstDao() {
		super(OptionHrst.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
