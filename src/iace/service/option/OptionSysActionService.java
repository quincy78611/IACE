package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionSysAction;

public class OptionSysActionService extends BaseOptionService<OptionSysAction> {

	public OptionSysActionService(IOptionDao<OptionSysAction> dao) {
		super(dao, OptionSysAction.class);
	}

}
