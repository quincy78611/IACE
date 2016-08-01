package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionSchool;

public class OptionSchoolService extends BaseOptionService<OptionSchool> {

	OptionSchoolService(IOptionDao<OptionSchool> dao) {
		super(dao);
	}

}
