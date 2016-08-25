package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionDomain;

public class OptionDomainService extends BaseOptionService<OptionDomain> {

	public OptionDomainService(IOptionDao<OptionDomain> dao) {
		super(dao, OptionDomain.class);
	}

}
