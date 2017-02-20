package iace.service.customerService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;

import core.util.EmailUtil;
import core.util.PagedList;
import iace.dao.customerService.IContactUsDao;
import iace.dao.customerService.IContactUsManagerDao;
import iace.entity.customerService.ContactUs;
import iace.entity.customerService.ContactUsManager;
import iace.entity.customerService.ContactUsSearchModel;
import iace.service.BaseIaceService;

public class ContactUsService extends BaseIaceService<ContactUs> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	private IContactUsDao dao;
	private IContactUsManagerDao contactUsManagerdao;

	public ContactUsService(IContactUsDao dao, IContactUsManagerDao contactUsManagerdao) {
		super(dao);
		this.dao = dao;
		this.contactUsManagerdao = contactUsManagerdao;
	}

	public PagedList<ContactUs> searchBy(ContactUsSearchModel arg) {
		return this.dao.searchBy(arg);
	}

	@Override
	public void create(ContactUs entity) throws IOException, SQLException {
		super.create(entity);
	}

	public void sendNotificationEmail(ContactUs entity) throws IOException, MessagingException {
		List<ContactUsManager> managers = this.contactUsManagerdao.listAll();
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/mail.smtp.properties"));
		String from = prop.getProperty("mail.default.from");
		String sender = prop.getProperty("mail.default.sender");
		String subject = "I-ACE平台[客服信箱]通知";
		String content = 
				"<table>" + 
					"<tr>" + 
						"<th><strong>新增日期</strong></th>" +
						"<td>" + sdf.format(entity.getCreateTime()) + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>聯絡人姓名</strong>" +
						"<td>" + entity.getName() + " " + (entity.getGender() ? "先生" : "小姐") + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>公司名稱</strong></th>" +
						"<td>" + entity.getCompanyName() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>連絡電話</strong></th>" +
						"<td>" + entity.getPhone() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>Email</strong></th>" +
						"<td>" + entity.getEmail() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>意見內容</strong></th>" +
						"<td>" + entity.getMessage() + "</td>" + 
					"</tr>" +
				"</table>";

		for (ContactUsManager manager : managers) {
			EmailUtil.send(subject, content, null, from, sender, manager.getEmail());
		}
	}

}
