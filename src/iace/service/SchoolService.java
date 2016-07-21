package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.School;

public class SchoolService extends BaseOptionService<School> {

	SchoolService(IOptionDao<School> dao) {
		super(dao);
	}

}
