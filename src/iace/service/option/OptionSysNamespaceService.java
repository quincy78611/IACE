package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionSysNamespace;

public class OptionSysNamespaceService extends BaseOptionService<OptionSysNamespace> {

	public OptionSysNamespaceService(IOptionDao<OptionSysNamespace> dao) {
		super(dao, OptionSysNamespace.class);
	}

}
