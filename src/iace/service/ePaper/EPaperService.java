package iace.service.ePaper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;

import org.apache.lucene.queryparser.classic.ParseException;

import core.util.EmailUtil;
import core.util.PagedList;
import iace.dao.ePaper.IEPaperDao;
import iace.dao.ePaper.IEPaperSubscriberDao;
import iace.dao.member.IMemberDao;
import iace.entity.ePaper.EPaper;
import iace.entity.ePaper.EPaperSearchModel;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysUser;
import iace.service.BaseIaceService;

public class EPaperService extends BaseIaceService<EPaper> {

	private IEPaperDao dao;
	private IEPaperSubscriberDao subscriberDao;
	private IMemberDao memberDao;
	
	public EPaperService(IEPaperDao dao, IEPaperSubscriberDao subscriberDao, IMemberDao memberDao) {
		super(dao);
		this.dao = dao;
		this.subscriberDao = subscriberDao;
		this.memberDao = memberDao;
	}

	public PagedList<EPaper> searchBy(EPaperSearchModel arg) {
		return this.dao.searchBy(arg);
	}

	public void sendTestEmail(long id, String to) throws MessagingException, IOException {
		EPaper epaper = get(id);
		String content = readEpaperContent(epaper);
		EmailUtil.send(epaper.getTitle(), content, null, "linkiac2@gmail.com", "科技部鏈結產學合作計畫辦公室", to);
	}
	
	public void publish(long id, SysUser user, SysLog syslog) throws IOException, SQLException, ParseException, MessagingException {
		EPaper epaper = get(id);
		epaper.setPostDate(new Date(System.currentTimeMillis()));
		epaper.setPublishState(true);
		
		String content = readEpaperContent(epaper);
		Set<String> emails = new HashSet<String>();
		emails.addAll(this.subscriberDao.allEmailList());
		emails.addAll(this.memberDao.allEmailList());
		for (String to : emails) {
			EmailUtil.send(epaper.getTitle(), content, null, "linkiac2@gmail.com", "科技部鏈結產學合作計畫辦公室", to);
		}
		
		update(epaper, user, false, syslog);
	}
	
	private String readEpaperContent(EPaper epaper) {
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(epaper.getFilePath()));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		return content;
	}

	
}
