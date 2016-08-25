package iace.service.option;

import java.util.List;

import iace.dao.option.IOptionSchoolDao;
import iace.entity.option.OptionSchool;

public class OptionSchoolService extends BaseOptionService<OptionSchool> {
	
	private IOptionSchoolDao dao;
	
	public OptionSchoolService(IOptionSchoolDao dao) {
		super(dao, OptionSchool.class);
		this.dao = dao;
	}
	
	public List<OptionSchool> listUnfillQnrCooperateWay() {
		return this.dao.listUnfillQnrCooperateWay();
	}

}
