package iace.dao.ePaper;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.ePaper.EPaperSubscriber;
import iace.entity.ePaper.EPaperSubscriberSearchModel;

public interface IEPaperSubscriberDao extends IBaseIaceDao<EPaperSubscriber> {
	public PagedList<EPaperSubscriber> searchBy(EPaperSubscriberSearchModel arg);
	public long queryTotalRecordsCount(EPaperSubscriberSearchModel arg);
	
	public EPaperSubscriber getByEmail(String email);
}
