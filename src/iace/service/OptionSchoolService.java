package iace.service;

import java.util.List;

import iace.dao.option.IOptionSchoolDao;
import iace.entity.option.OptionSchool;

public class OptionSchoolService extends BaseOptionService<OptionSchool> {
	
	private IOptionSchoolDao dao;
	
	OptionSchoolService(IOptionSchoolDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public List<OptionSchool> listUnfillQnrCooperateWay() {
		return this.dao.listUnfillQnrCooperateWay();
	}

}
