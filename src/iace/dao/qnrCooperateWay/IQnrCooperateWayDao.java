package iace.dao.qnrCooperateWay;

import java.util.List;

import iace.dao.IBaseIaceDao;
import iace.entity.qnrCooperateWay.QnrCooperateWay;

public interface IQnrCooperateWayDao extends IBaseIaceDao<QnrCooperateWay> {
	
	public List<Long> listAllSchoolId(); 
}
