package iace.dao.option;

import iace.entity.option.OptionGrbDomain;

public class OptionGrbDomainDao extends BaseOptionDao<OptionGrbDomain> {

	public OptionGrbDomainDao() {
		super(OptionGrbDomain.class);
	}

	@Override
	public boolean hasBeenUsed(OptionGrbDomain entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
