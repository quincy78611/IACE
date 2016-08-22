package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionConsult;

public class OptionConsultService extends BaseOptionService<OptionConsult> {

	public OptionConsultService(IOptionDao<OptionConsult> dao) {
		super(dao);
	}

}
