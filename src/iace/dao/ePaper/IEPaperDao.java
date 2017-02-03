package iace.dao.ePaper;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.ePaper.EPaper;
import iace.entity.ePaper.EPaperSearchModel;

public interface IEPaperDao extends IBaseIaceDao<EPaper> {
	public PagedList<EPaper> searchBy(EPaperSearchModel arg);
	public long queryTotalRecordsCount(EPaperSearchModel arg);
}
