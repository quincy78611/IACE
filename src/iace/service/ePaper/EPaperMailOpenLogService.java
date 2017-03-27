package iace.service.ePaper;

import iace.dao.ePaper.IEPaperMailOpenLogDao;
import iace.entity.ePaper.EPaperMailOpenLog;
import iace.service.BaseIaceService;

public class EPaperMailOpenLogService extends BaseIaceService<EPaperMailOpenLog> {

	private IEPaperMailOpenLogDao dao;
	
	public EPaperMailOpenLogService(IEPaperMailOpenLogDao dao) {
		super(dao);
		this.dao = dao;
	}

	public long openCount(long epaperId, String fromEmail) {
		return this.dao.openCount(epaperId, fromEmail);
	}
	
	public long totalOpenCount(long epaperId) {
		return this.dao.totalOpenCount(epaperId);
	}
	
	public long distinctPeopleOpenCount(long epaperId) {
		return this.dao.distinctPeopleOpenCount(epaperId);
	}
}
