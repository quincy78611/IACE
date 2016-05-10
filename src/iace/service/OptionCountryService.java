package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionCountry;

public class OptionCountryService extends BaseOptionService<OptionCountry> {

	OptionCountryService(IOptionDao<OptionCountry> dao) {
		super(dao);
	}

}
