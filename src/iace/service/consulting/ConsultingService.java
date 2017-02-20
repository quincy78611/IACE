package iace.service.consulting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import core.dao.HibernateSessionFactory;
import core.util.EmailUtil;
import core.util.ExcelUtil;
import core.util.PagedList;
import iace.dao.consulting.IConsultingDao;
import iace.dao.consulting.IConsultingManagerDao;
import iace.entity.consulting.Consulting;
import iace.entity.consulting.ConsultingManager;
import iace.entity.consulting.ConsultingSearchModel;
import iace.service.BaseIaceService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class ConsultingService extends BaseIaceService<Consulting> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	private IConsultingDao consultingDao;
	private IConsultingManagerDao consultingManagerDao;
	
	public ConsultingService(IConsultingDao dao, IConsultingManagerDao consultingManagerDao) {
		super(dao);
		this.consultingDao = dao;
		this.consultingManagerDao = consultingManagerDao;
	}
	
	public PagedList<Consulting> searchBy(int pageIndex, int pageSize, String name, String organization) {
		PagedList<Consulting> res = this.consultingDao.searchBy(pageIndex, pageSize, name, organization);
		return res;
	}
	
	public PagedList<Consulting> searchBy(ConsultingSearchModel model) {
		PagedList<Consulting> res = this.consultingDao.searchBy(model);
		return res;
	}
	
	public XSSFWorkbook exportRawData(ConsultingSearchModel model) {
		List<Consulting> consultingList = this.consultingDao.listAll(model);
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("姓名");
			row.createCell(++c).setCellValue("單位名稱");
			row.createCell(++c).setCellValue("單位類型");
			row.createCell(++c).setCellValue("單位類型(其他)");
			row.createCell(++c).setCellValue("諮詢類型");
			row.createCell(++c).setCellValue("諮詢類型(其他)");
			row.createCell(++c).setCellValue("產業/領域別");
			row.createCell(++c).setCellValue("產業/領域別(其他)");
			row.createCell(++c).setCellValue("聯絡電話");
			row.createCell(++c).setCellValue("E-MAIL");
			row.createCell(++c).setCellValue("諮詢日期");
			row.createCell(++c).setCellValue("內容說明");
		}
		
		// data part
		for (Consulting con : consultingList) {
			row = sheet.createRow(++r);
			c = -1;
			ExcelUtil.createNSetCellValue(row, ++c, con.getName());
			ExcelUtil.createNSetCellValue(row, ++c, con.getOrganization());
			ExcelUtil.createNSetCellValue(row, ++c, con.getOptionOrganizationType().getName());
			ExcelUtil.createNSetCellValue(row, ++c, con.getOrgTypeOther());
			ExcelUtil.createNSetCellValue(row, ++c, con.getOptionConsult().getName());
			ExcelUtil.createNSetCellValue(row, ++c, con.getConsultTypeOther());
			ExcelUtil.createNSetCellValue(row, ++c, con.getOptionIndustry().getName());
			ExcelUtil.createNSetCellValue(row, ++c, con.getIndustryOther());
			ExcelUtil.createNSetCellValue(row, ++c, con.getPhone());
			ExcelUtil.createNSetCellValue(row, ++c, con.getEmail());
			ExcelUtil.createNSetCellValue(row, ++c, con.getConsultDate());
			ExcelUtil.createNSetCellValue(row, ++c, con.getContent());
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	
	public InputStream printReport(long id) throws JRException, IOException {
		// inputStream
		ServletContext context = ServletActionContext.getServletContext();
		String reportSource = context.getRealPath("/report/jasper/consulting/CONSULTING.jasper");
		FileInputStream fis = new FileInputStream(reportSource);
		
		// outputStream
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		// parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("consultingId", id);
		
		// session
		Session session = HibernateSessionFactory.getSession();
		SessionImpl sessionImpl = (SessionImpl) session; 
		Connection conn = sessionImpl.connection();
		
		// run
		JasperRunManager.runReportToPdfStream(fis, os, parameters, conn);
		return new ByteArrayInputStream(os.toByteArray());
	}
	
	public void sendNotificationEmail(Consulting entity) throws IOException, MessagingException {
		entity = this.dao.get(entity.getId());
		List<ConsultingManager> managers = this.consultingManagerDao.listAll();
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/mail.smtp.properties"));
		String from = prop.getProperty("mail.default.from");
		String sender = prop.getProperty("mail.default.sender");
		String subject = "I-ACE平台[我要諮詢]通知";
		String content = 
				"<table>" + 
					"<tr>" + 
						"<th><strong>新增日期</strong></th>" +
						"<td>" + sdf.format(entity.getCreateTime()) + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>姓名</strong>" +
						"<td>" + entity.getName() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>單位名稱</strong>" +
						"<td>" + entity.getOrganization() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>單位類型</strong>" +
						"<td>" + entity.getOptionOrganizationType().getName() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>單位類型(其他)</strong>" +
						"<td>" + (entity.getOrgTypeOther() == null ? "" : entity.getOrgTypeOther()) + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>諮詢類型</strong>" +
						"<td>" + entity.getOptionConsult().getName() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>諮詢類型(其他)</strong>" +
						"<td>" + (entity.getConsultTypeOther() == null ? "" : entity.getConsultTypeOther()) + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>產業/領域別</strong>" +
						"<td>" + entity.getOptionIndustry().getName() + "</td>" + 
					"</tr>" +
					"<tr>" + 
						"<th><strong>產業/領域別(其他)</strong>" +
						"<td>" + (entity.getIndustryOther() == null ? "" : entity.getIndustryOther()) + "</td>" + 
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
						"<th><strong>內容</strong></th>" +
						"<td>" + entity.getContent() + "</td>" + 
					"</tr>" +
				"</table>";

		for (ConsultingManager manager : managers) {
			EmailUtil.send(subject, content, null, from, sender, manager.getEmail());
		}
	}
}
