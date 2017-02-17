package iace.dao.rdFocus;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.rdFocus.RdFocus;
import iace.entity.rdFocus.RdFocusSearchModel;

public interface IRdFocusDao extends IBaseIaceDao<RdFocus> {
	
	public PagedList<RdFocus> searchBy(RdFocusSearchModel arg);
	public long queryTotalRecordsCount(RdFocusSearchModel arg);
	public List<RdFocus> sampleForHomePage();
}
