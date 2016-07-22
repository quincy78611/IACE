package iace.service;

import java.util.List;

import iace.dao.option.IOptionSubjectDao;
import iace.entity.option.OptionSubject;

public class OptionSubjectService extends BaseOptionService<OptionSubject> {
	private IOptionSubjectDao dao;
	
	OptionSubjectService(IOptionSubjectDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public List<OptionSubject> listSpecificLv(int lv, String parentCode) {
		return this.dao.listSpecificLv(lv, parentCode);
	}
	
	
}
