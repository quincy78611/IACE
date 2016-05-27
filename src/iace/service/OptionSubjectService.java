package iace.service;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionSubject;

public class OptionSubjectService extends BaseOptionService<OptionSubject> {

	OptionSubjectService(IOptionDao<OptionSubject> dao) {
		super(dao);
	}
}
