package iace.service;

import iace.dao.qnrCooperateWay.IQnrCooperateWayDao;
import iace.entity.qnrCooperateWay.QnrCooperateWay;

public class QnrCooperateWayService extends BaseIaceService<QnrCooperateWay> {
	private IQnrCooperateWayDao qnrCooperateWayDao;

	QnrCooperateWayService(IQnrCooperateWayDao dao) {
		super(dao);
		this.qnrCooperateWayDao = dao;
	}

	public QnrCooperateWay getBySchool(long schoolId) {
		return this.qnrCooperateWayDao.getBySchool(schoolId);
	}

}
