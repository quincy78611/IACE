package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionHrst;

public class OptionHrstService extends BaseOptionService<OptionHrst> {

	public OptionHrstService(IOptionDao<OptionHrst> dao) {
		super(dao, OptionHrst.class);
	}

}
