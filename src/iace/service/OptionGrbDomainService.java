package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionGrbDomain;

public class OptionGrbDomainService extends BaseOptionService<OptionGrbDomain> {

	OptionGrbDomainService(IOptionDao<OptionGrbDomain> dao) {
		super(dao);
	}

}
