package iace.service.ePaper;

import core.util.PagedList;
import iace.dao.ePaper.IEPaperDao;
import iace.entity.ePaper.EPaper;
import iace.entity.ePaper.EPaperSearchModel;
import iace.service.BaseIaceService;

public class EPaperService extends BaseIaceService<EPaper> {

	private IEPaperDao dao;
	
	public EPaperService(IEPaperDao dao) {
		super(dao);
		this.dao = dao;
	}

	public PagedList<EPaper> searchBy(EPaperSearchModel arg) {
		return this.dao.searchBy(arg);
	}	
	
}
