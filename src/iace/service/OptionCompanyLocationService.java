package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionCompanyLocation;

public class OptionCompanyLocationService extends BaseOptionService<OptionCompanyLocation> {

	OptionCompanyLocationService(IOptionDao<OptionCompanyLocation> dao) {
		super(dao);
	}

}
