package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionGrbDomain;

public class OptionGrbDomainService extends BaseOptionService<OptionGrbDomain> {

	public OptionGrbDomainService(IOptionDao<OptionGrbDomain> dao) {
		super(dao, OptionGrbDomain.class);
	}

}
