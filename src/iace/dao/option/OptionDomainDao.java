package iace.dao.option;

import iace.entity.option.OptionDomain;

public class OptionDomainDao extends BaseOptionDao<OptionDomain> {

	public OptionDomainDao() {
		super(OptionDomain.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
