package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionConsult;

public class OptionConsultService extends BaseOptionService<OptionConsult> {

	OptionConsultService(IOptionDao<OptionConsult> dao) {
		super(dao);
	}

}
