package iace.dao.rdFocus;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.rdFocus.RdFocus;
import iace.entity.rdFocus.RdFocusSearchModel;

public interface IRdFocusDao extends IBaseIaceDao<RdFocus> {
	
	public PagedList<RdFocus> searchBy(RdFocusSearchModel arg);
	public long queryTotalRecordsCount(RdFocusSearchModel arg);
}
