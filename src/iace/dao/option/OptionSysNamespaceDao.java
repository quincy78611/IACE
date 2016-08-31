package iace.dao.option;

import iace.entity.option.OptionSysNamespace;

public class OptionSysNamespaceDao extends BaseOptionDao<OptionSysNamespace> {

	public OptionSysNamespaceDao() {
		super(OptionSysNamespace.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
