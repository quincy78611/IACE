package iace.service.ePaper;

import core.util.PagedList;
import iace.dao.ePaper.IEPaperSubscriberDao;
import iace.entity.ePaper.EPaperSubscriber;
import iace.entity.ePaper.EPaperSubscriberSearchModel;
import iace.service.BaseIaceService;

public class EPaperSubscriberService extends BaseIaceService<EPaperSubscriber> {
	private IEPaperSubscriberDao dao;
	
	public EPaperSubscriberService(IEPaperSubscriberDao dao) {
		super(dao);
		this.dao = dao;
	}

	public EPaperSubscriber getByEmail(String email) {
		return this.dao.getByEmail(email);
	}
	
	public PagedList<EPaperSubscriber> searchBy(EPaperSubscriberSearchModel arg) {
		return this.dao.searchBy(arg);
	}
}
