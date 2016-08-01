package iace.service;

import java.util.Collections;
import java.util.List;

import core.util.BeanComparator;
import iace.dao.option.IOptionDao;
import iace.dao.qnrCooperateWay.IQnrCooperateWayMeritDao;
import iace.entity.option.OptionSchool;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;

public class QnrCooperateWayMeritService extends BaseIaceService<QnrCooperateWayMerit> {

	private IQnrCooperateWayMeritDao qnrCooperateWayMeritDao;
	private IOptionDao<OptionSchool> schoolDao;
	
	QnrCooperateWayMeritService(IQnrCooperateWayMeritDao dao, IOptionDao<OptionSchool> schoolDao) {
		super(dao);
		this.qnrCooperateWayMeritDao = dao;
		this.schoolDao = schoolDao;
	}

	@SuppressWarnings("unchecked")
	public List<QnrCooperateWayMerit> getForUpdate(long schoolId) {
		List<QnrCooperateWayMerit> yearMeritList = this.qnrCooperateWayMeritDao.getBySchool(schoolId);
		if (yearMeritList == null || yearMeritList.size() != 3) {
			OptionSchool school = this.schoolDao.get(schoolId);
			int[] years = {2013, 2014, 2015};
			for (int year : years) {
				QnrCooperateWayMerit yearMerit = this.qnrCooperateWayMeritDao.getBySchool(schoolId, year);
				if (yearMerit == null) {
					yearMerit = new QnrCooperateWayMerit();
					yearMerit.setYear(year);
					yearMerit.setSchool(school);
					this.qnrCooperateWayMeritDao.create(yearMerit);
					yearMeritList.add(yearMerit);
				}
			}
		}

		BeanComparator bc = new BeanComparator(QnrCooperateWayMerit.class, "getYear");
		Collections.sort(yearMeritList, bc);
		
		return yearMeritList;
	}
	
	public void updateSchoolMerit(List<QnrCooperateWayMerit> entities) {
		this.dao.updateAll(entities);
	}

}
