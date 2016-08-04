package iace.service;

import java.util.List;

import iace.dao.qnrCooperateWay.IQnrCooperateWayMeritDao;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;

public class QnrCooperateWayMeritService extends BaseIaceService<QnrCooperateWayMerit> {
	
	QnrCooperateWayMeritService(IQnrCooperateWayMeritDao dao) {
		super(dao);
	}
	
	public void createAll(List<QnrCooperateWayMerit> entities) {
		this.dao.createAll(entities);
	}

}
