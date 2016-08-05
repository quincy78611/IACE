package iace.dao.option;

import iace.entity.option.OptionConsult;

public class OptionConsultDao extends BaseOptionDao<OptionConsult> {

	public OptionConsultDao() {
		super(OptionConsult.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
