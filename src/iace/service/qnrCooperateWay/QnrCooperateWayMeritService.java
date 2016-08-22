package iace.service.qnrCooperateWay;

import java.util.List;

import iace.dao.qnrCooperateWay.IQnrCooperateWayMeritDao;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;
import iace.service.BaseIaceService;

public class QnrCooperateWayMeritService extends BaseIaceService<QnrCooperateWayMerit> {
	
	public QnrCooperateWayMeritService(IQnrCooperateWayMeritDao dao) {
		super(dao);
	}
	
	public void createAll(List<QnrCooperateWayMerit> entities) {
		this.dao.createAll(entities);
	}

}
