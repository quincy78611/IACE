package iace.dao.sys;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysLogSearchModel;

public interface ISysLogDao extends IBaseIaceDao<SysLog> {

	public PagedList<SysLog> searchBy(SysLogSearchModel arg);
}
