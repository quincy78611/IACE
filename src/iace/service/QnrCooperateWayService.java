package iace.service;

import iace.dao.qnrCooperateWay.IQnrCooperateWayDao;
import iace.entity.qnrCooperateWay.QnrCooperateWay;

public class QnrCooperateWayService extends BaseIaceService<QnrCooperateWay> {

	QnrCooperateWayService(IQnrCooperateWayDao dao) {
		super(dao);
	}

}
