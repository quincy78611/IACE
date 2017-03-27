package iace.dao.ePaper;

import iace.dao.IBaseIaceDao;
import iace.entity.ePaper.EPaperMailOpenLog;

public interface IEPaperMailOpenLogDao extends IBaseIaceDao<EPaperMailOpenLog> {

	public long openCount(long epaperId, String fromEmail);
	
	public long totalOpenCount(long epaperId);

	public long distinctPeopleOpenCount(long epaperId);
}
