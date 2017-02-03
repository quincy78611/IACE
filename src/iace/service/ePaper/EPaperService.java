package iace.service.ePaper;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import org.apache.lucene.queryparser.classic.ParseException;

import core.util.PagedList;
import iace.dao.ePaper.IEPaperDao;
import iace.entity.ePaper.EPaper;
import iace.entity.ePaper.EPaperSearchModel;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysUser;
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

	public void publish(long id, SysUser user, SysLog syslog) throws IOException, SQLException, ParseException {
		EPaper epaper = get(id);
		epaper.setPostDate(new Date(System.currentTimeMillis()));
		epaper.setPublishState(true);
		update(epaper, user, false, syslog);
		//TODO
	}
	
}
