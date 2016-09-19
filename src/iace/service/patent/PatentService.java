package iace.service.patent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import core.dao.HibernateSessionFactory;
import core.util.ExcelUtil;
import core.util.PagedList;
import iace.dao.option.IOptionDao;
import iace.dao.patent.IPatentDao;
import iace.dao.techField.ITechFieldDao;
import iace.entity.option.OptionCountry;
import iace.entity.patent.Patent;
import iace.entity.patent.PatentSearchModel;
import iace.entity.patent.TechField;
import iace.service.BaseIaceService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class PatentService extends BaseIaceService<Patent> {

	private IPatentDao patentDao;
	private ITechFieldDao techFieldDao;
	private IOptionDao<OptionCountry> optionCountryDao;
	
	private String patentPictureFolder;
	
	public PatentService(IPatentDao patentDao, ITechFieldDao techFieldDao, 
			IOptionDao<OptionCountry> optionCountryDao) {
		super(patentDao);
		this.patentDao = patentDao;
		this.techFieldDao = techFieldDao;
		this.optionCountryDao = optionCountryDao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.patentPictureFolder = prop.getProperty("patentPicFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}
	
	public PagedList<Patent> searchBy(PatentSearchModel model) {
		PagedList<Patent> res = this.patentDao.searchBy(model);
		for (Patent p : res.getList()) {
			loadImportantPicToEntity(p);
		}
		return res;
	}
	
	@Override
	public Patent get(Long id) {
		Patent p = this.patentDao.get(id);
		loadImportantPicToEntity(p);
		return p;
	}

	@Override
	public void create(Patent entity) throws IOException {
		setOptionCountryToEntity(entity);
		getOrInsertTechField(entity);
		setPatentImportantPicturePath(entity);
		try {
			savePatentPicture(entity);
		} catch (IOException e) {
			throw new IllegalArgumentException("儲存圖片失敗!");
		}
		this.patentDao.create(entity);
	}
	
	public List<String> createAll(List<Patent> entities) {
		// entity設值
		for (Patent p : entities) {
			setOptionCountryToEntity(p);
			getOrInsertTechField(p);
			setPatentImportantPicturePath(p);
		}
		
		List<String> errMsgs = new ArrayList<String>();
		Set<String> ukSet = this.patentDao.getUKs();		
		for (int i=0;i<entities.size();i++) {
			Patent p = entities.get(i);
			//必填欄位與資料長度檢核
			for (String msg : p.validate()) {
				errMsgs.add("第"+(i+1)+"筆:"+msg);
			}			
			
			// 檢查唯一
			String uk = String.format("%s-%d", p.getAppliactionNo(), p.getTechField().getId());
			if (ukSet.contains(uk)) {
				String errMsg = String.format("第%d筆資料已存在", i+1);
				errMsgs.add(errMsg);
			} else {
				ukSet.add(uk);
			}
		}		
		if (errMsgs.size() > 0) {
			return errMsgs;
		}

		// 儲存圖片 ---------------------------------------------------------------
		for (int i=0;i<entities.size();i++) {
			try {
				savePatentPicture(entities.get(i));
			} catch (IOException e) {
				String errMsg = String.format("第%d列圖片儲存失敗", i+1);
				log.warn(errMsg, e);
				errMsgs.add(errMsg);
			}
		}	
		
		if (errMsgs.size() > 0) {
			return errMsgs;
		}
		//----------------------------------------------------------------------
		this.patentDao.createAll(entities);
		return errMsgs;
	}

	@Override
	public void update(Patent entity) {
		setOptionCountryToEntity(entity);
		getOrInsertTechField(entity);
		setPatentImportantPicturePath(entity);
		try {
			savePatentPicture(entity);
		} catch (IOException e) {
			throw new IllegalArgumentException("儲存圖片失敗!");
		}
		this.patentDao.update(entity);		
	}

	@Override
	public void delete(Patent entity) {
		String imagePath = entity.getImportantPicturePath();
		this.patentDao.delete(entity.getId());
		if (StringUtils.isNotBlank(imagePath)) {
			File f = new File(this.patentPictureFolder, imagePath);
			if (f.exists()) f.delete();		
		}
	}	

	@Override
	public void delete(Long id) throws IOException {
		Patent entity = this.patentDao.get(id);
		delete(entity);
	}
		
	public boolean checkUK(Patent entity) {
		return this.patentDao.checkUK(entity);
	}
	
	private void setOptionCountryToEntity(Patent entity) {		
		if (this.optionCountryDao.isCodeExist(entity.getCountry().getCode())) {
			OptionCountry opt = this.optionCountryDao.getByCode(entity.getCountry().getCode());
			entity.setCountry(opt);
		}
		else {
			throw new IllegalArgumentException("國別代碼:「"+entity.getCountry().getCode()+"」不存在");
		}
	}
	
	private void getOrInsertTechField(Patent entity) {
		if (this.techFieldDao.isNameExist(entity.getTechField().getName())) {
			TechField tf = this.techFieldDao.getByName(entity.getTechField().getName());
			entity.setTechField(tf);
		} else {
			TechField tf = new TechField();
			tf.setName(entity.getTechField().getName());		
			this.techFieldDao.create(tf);
			entity.setTechField(tf);
		}
	}

	private void setPatentImportantPicturePath(Patent entity) {
		if (entity.getImportantPatentPicture() != null) {
			String fileName = String.format("%s-%d.%s", 
			entity.getAppliactionNo(),
			entity.getTechField().getId(),
			entity.getImportantPatentPictureExtension());
			entity.setImportantPicturePath(fileName);
		}
	}

	
	private void savePatentPicture(Patent p) throws IOException {
		if (p.getImportantPatentPicture() != null && p.getImportantPicturePath() != null) {
			File f = new File(this.patentPictureFolder, p.getImportantPicturePath());
			byte[] img = p.getImportantPatentPicture();
			FileUtils.writeByteArrayToFile(f, img);
		}
	}	

	//將圖片從檔案load進來並且存入Entity中
	private void loadImportantPicToEntity(Patent p) {
		try {
			File f = new File(this.patentPictureFolder, p.getImportantPicturePath());
			byte[] data = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
			p.setImportantPatentPicture(data);
		} catch (IOException | NullPointerException e) {
			log.warn("load image fail");
		}
	}
	
	public InputStream printReport(long id) throws JRException, IOException {
		// inputStream
		ServletContext context = ServletActionContext.getServletContext();
		String reportSource = context.getRealPath("/report/jasper/patent/patent.jasper");
		FileInputStream fis = new FileInputStream(reportSource);
		
		// outputStream
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		// parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("patentId", id);
		
		// session
		Session session = HibernateSessionFactory.getSession();
		SessionImpl sessionImpl = (SessionImpl) session; 
		Connection conn = sessionImpl.connection();
		
		// run
		JasperRunManager.runReportToPdfStream(fis, os, parameters, conn);
		return new ByteArrayInputStream(os.toByteArray());
	}
	
	public XSSFWorkbook exportRawData(PatentSearchModel model) {
		List<Patent> patentList = this.patentDao.listAll(model);
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("名稱");
			row.createCell(++c).setCellValue("專利權人");
			row.createCell(++c).setCellValue("發明人");
			row.createCell(++c).setCellValue("申請國別");
			row.createCell(++c).setCellValue("申請號");
			row.createCell(++c).setCellValue("申請日");
			row.createCell(++c).setCellValue("公開號");
			row.createCell(++c).setCellValue("公開日");
			row.createCell(++c).setCellValue("公告號");
			row.createCell(++c).setCellValue("公告日");
			row.createCell(++c).setCellValue("專利類別");
			row.createCell(++c).setCellValue("專利狀態");
			row.createCell(++c).setCellValue("專利家族");
			row.createCell(++c).setCellValue("國際分類號");
			row.createCell(++c).setCellValue("專利技術領域");
			row.createCell(++c).setCellValue("應用範圍/產業");
			row.createCell(++c).setCellValue("摘要");
		}
		
		// data part
		for (Patent p : patentList) {
			row = sheet.createRow(++r);
			c = -1;
			ExcelUtil.createNSetCellValue(row, ++c, p.getName());
			ExcelUtil.createNSetCellValue(row, ++c, p.getAssignee());
			ExcelUtil.createNSetCellValue(row, ++c, p.getInvertor());
			ExcelUtil.createNSetCellValue(row, ++c, p.getCountry().getCode());
			ExcelUtil.createNSetCellValue(row, ++c, p.getAppliactionNo());
			ExcelUtil.createNSetCellValue(row, ++c, p.getApplicationDate());
			ExcelUtil.createNSetCellValue(row, ++c, p.getOpenNo());
			ExcelUtil.createNSetCellValue(row, ++c, p.getOpenDate());
			ExcelUtil.createNSetCellValue(row, ++c, p.getPublicationNo());
			ExcelUtil.createNSetCellValue(row, ++c, p.getPublicationDate());
			ExcelUtil.createNSetCellValue(row, ++c, p.getCategory());
			ExcelUtil.createNSetCellValue(row, ++c, p.getPatentStatus());
			ExcelUtil.createNSetCellValue(row, ++c, p.getFamilyNo());
			ExcelUtil.createNSetCellValue(row, ++c, p.getIpc());
			ExcelUtil.createNSetCellValue(row, ++c, p.getTechField().getName());
			ExcelUtil.createNSetCellValue(row, ++c, p.getUsage());
			ExcelUtil.createNSetCellValue(row, ++c, p.getTechAbstract());
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	

}
