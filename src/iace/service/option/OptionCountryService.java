package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionCountry;

public class OptionCountryService extends BaseOptionService<OptionCountry> {

	public OptionCountryService(IOptionDao<OptionCountry> dao) {
		super(dao, OptionCountry.class);
	}

}
