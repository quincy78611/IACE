package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionTrl;

public class OptionTrlService extends BaseOptionService<OptionTrl> {

	public OptionTrlService(IOptionDao<OptionTrl> dao) {
		super(dao, OptionTrl.class);
		// TODO Auto-generated constructor stub
	}

}
