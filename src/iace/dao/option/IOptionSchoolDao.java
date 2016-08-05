package iace.dao.option;

import java.util.List;

import iace.entity.option.OptionSchool;

public interface IOptionSchoolDao extends IOptionDao<OptionSchool> {
	public List<OptionSchool> listUnfillQnrCooperateWay();
}
