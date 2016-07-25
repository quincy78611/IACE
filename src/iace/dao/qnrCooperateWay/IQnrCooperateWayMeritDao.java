package iace.dao.qnrCooperateWay;

import java.util.List;

import iace.dao.IBaseIaceDao;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;

public interface IQnrCooperateWayMeritDao extends IBaseIaceDao<QnrCooperateWayMerit> {
	public List<QnrCooperateWayMerit> getBySchool(long schoolId);
	
	public QnrCooperateWayMerit getBySchool(long schoolId, int year);
}
