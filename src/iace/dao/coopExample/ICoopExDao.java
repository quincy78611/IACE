package iace.dao.coopExample;

import iace.dao.IBaseIaceDao;
import iace.entity.coopExample.CoopEx;

public interface ICoopExDao extends IBaseIaceDao<CoopEx> {
	
	public boolean isProjNameExist(long currentId, String projName);
}
