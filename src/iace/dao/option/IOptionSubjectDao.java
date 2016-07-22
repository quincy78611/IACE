package iace.dao.option;

import java.util.List;

import iace.entity.option.OptionSubject;

public interface IOptionSubjectDao extends IOptionDao<OptionSubject> {

	public List<OptionSubject> listSpecificLv(int lv, String parentCode);
}
