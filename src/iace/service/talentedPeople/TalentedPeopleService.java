package iace.service.talentedPeople;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
import iace.dao.option.IOptionGrbDomainDao;
import iace.dao.talentedPeople.ITalentedPeopleDao;
import iace.dao.talentedPeople.ITalentedPeopleMainProjectDao;
import iace.dao.talentedPeople.ITalentedPeopleRdResultDao;
import iace.dao.talentedPeople.ITalentedPeopleTransferCaseDao;
import iace.entity.BaseBatchImportResult;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionGrbDomain;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleMainProject;
import iace.entity.talentedPeople.TalentedPeopleRdResult;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;
import iace.entity.talentedPeople.TalentedPeopleTransferCase;
import iace.service.BaseIaceService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class TalentedPeopleService extends BaseIaceService<TalentedPeople> {

	private ITalentedPeopleDao talentedPeopleDao;
	private ITalentedPeopleRdResultDao talentedPeopleRdResultDao;
	private ITalentedPeopleTransferCaseDao talentedPeopleTransferCaseDao;
	private ITalentedPeopleMainProjectDao talentedPeopleMainProjectDao;
	private IOptionGrbDomainDao optionGrbDomainDao;
	private IOptionDao<OptionCountry> optionCountryDao;
	
	public TalentedPeopleService(
			ITalentedPeopleDao dao, 
			ITalentedPeopleRdResultDao talentedPeopleRdResultDao,
			ITalentedPeopleTransferCaseDao talentedPeopleTransferCaseDao,
			ITalentedPeopleMainProjectDao talentedPeopleMainProjectDao,
			IOptionGrbDomainDao optionGrbDomainDao, 
			IOptionDao<OptionCountry> optionCountryDao) {
		super(dao);
		this.talentedPeopleDao = dao;
		this.talentedPeopleRdResultDao = talentedPeopleRdResultDao;
		this.talentedPeopleTransferCaseDao = talentedPeopleTransferCaseDao;
		this.talentedPeopleMainProjectDao = talentedPeopleMainProjectDao;
		this.optionGrbDomainDao = optionGrbDomainDao;
		this.optionCountryDao = optionCountryDao;
	}

	public PagedList<TalentedPeople> searchBy(TalentedPeopleSearchModel arg) {
		return talentedPeopleDao.searchBy(arg);
	}

	@Override
	public void create(TalentedPeople entity) throws IOException, SQLException {
		setHeadShot(entity);
		setDomain(entity);
		super.create(entity);
	}

	@Override
	public void update(TalentedPeople entity) throws IOException, SQLException {
		setHeadShot(entity);
		setDomain(entity);
		
		for (TalentedPeopleRdResult rdResult : entity.getRdResults()) {
			rdResult.setTalentedPeople(entity);
			OptionCountry oc = this.optionCountryDao.get(rdResult.getOptionCountry().getId());
			rdResult.setOptionCountry(oc);
			if (rdResult.getId() <= 0) {
				rdResult.create();
			} else {
				rdResult.update();
			}
		}
		for (TalentedPeopleTransferCase transferCase : entity.getTransferCases()) {
			transferCase.setTalentedPeople(entity);
			if (transferCase.getId() <= 0) {
				transferCase.create();
			} else {
				transferCase.update();
			}
		}
		for (TalentedPeopleMainProject mainProject : entity.getMainProjects()) {
			mainProject.setTalentedPeople(entity);
			if (mainProject.getId() <= 0) {
				mainProject.create();
			} else {
				mainProject.update();
			}
		}

		TalentedPeople entityO = this.talentedPeopleDao.get(entity.getId());
		deleteChildFromDb(entity, entityO);
		
		this.talentedPeopleDao.update(entity);
	}
	
	/**
	 * If there is new upload headShot, load it from temp file and set it's byte[] to entity
	 * @param entity
	 */
	private void setHeadShot(TalentedPeople entity) {
		try {
			if (entity.hasUploadFile()) {
				Path p = Paths.get(entity.getUploadheadShot().getAbsolutePath());
				byte[] data = Files.readAllBytes(p);
				entity.setHeadShot(data);
			}
		} catch (IOException | NullPointerException e) {
			log.warn("load image fail");
		}
	}
	
	private void setDomain(TalentedPeople entity) {
		ArrayList<OptionGrbDomain> domains = new ArrayList<OptionGrbDomain>();
		for (long id : entity.getDomainsId()) {
			OptionGrbDomain domain = this.optionGrbDomainDao.get(id);
			domains.add(domain);
		}
		entity.setDomains(domains);
	}
	
	private void deleteChildFromDb(TalentedPeople entity, TalentedPeople entityO) {
		Set<Long> rdResultIdSet = new HashSet<Long>();
		for (TalentedPeopleRdResult rdr : entity.getRdResults()) {
			if (rdr.getId() > 0) {
				rdResultIdSet.add(rdr.getId());
			}
		}
		for (TalentedPeopleRdResult rdr : entityO.getRdResults()) {
			if (rdResultIdSet.contains(rdr.getId()) == false) {
				this.talentedPeopleRdResultDao.delete(rdr);
			}
		}
		
		Set<Long> transferCaseIdSet = new HashSet<Long>();
		for (TalentedPeopleTransferCase tc : entity.getTransferCases()) {
			if (tc.getId() > 0) {
				transferCaseIdSet.add(tc.getId());
			}
		}
		for (TalentedPeopleTransferCase tc : entityO.getTransferCases()) {
			if (transferCaseIdSet.contains(tc.getId()) == false) {
				this.talentedPeopleTransferCaseDao.delete(tc);
			}
		}
		
		Set<Long> mainProjectIdSet = new HashSet<Long>();
		for (TalentedPeopleMainProject mp : entity.getMainProjects()) {
			if (mp.getId() > 0) {
				mainProjectIdSet.add(mp.getId());
			}
		}
		for (TalentedPeopleMainProject mp : entityO.getMainProjects()) {
			if (mainProjectIdSet.contains(mp.getId()) == false) {
				this.talentedPeopleMainProjectDao.delete(mp);
			}
		}
	}
	
	public BaseBatchImportResult<TalentedPeople> batchImport(File file) throws IOException {
		BaseBatchImportResult<TalentedPeople> res = new BaseBatchImportResult<TalentedPeople>();
		Map<String, OptionGrbDomain> grbDomainMap = this.optionGrbDomainDao.mapAll();
		
		try (FileInputStream fis = new FileInputStream(file);){
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			for (int r = 3; r <= sheet.getLastRowNum(); r++) {
				int c = 1;
				XSSFRow row = sheet.getRow(r);
				XSSFCell cell;
				try {
					TalentedPeople entity = new TalentedPeople();
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setNameCh(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setNameEn(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setGender(cell.getStringCellValue().trim().equals("1") ? "男" : "女");
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String expYearStr = cell.getStringCellValue().trim();
					if (StringUtils.isNumeric(expYearStr)) {
						entity.setExpYear(Integer.valueOf(expYearStr));
					}
					
					
					cell = row.getCell(++c); // 領域別 不用紀錄
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String domainCodesStr = cell.getStringCellValue().trim().replace(";", "；");
					String[] domainCodes = StringUtils.split(domainCodesStr, "；");
					if (domainCodes.length > 0) {
						for (String code : domainCodes) {
							entity.addDomain(grbDomainMap.get(code));
						}
					}
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setTel(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setEmail(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setWorkOrg(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setJob(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setUrl(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSpecialty(cell.getStringCellValue().trim());
					
					this.dao.create(entity);
					res.addRecordToInsertList(entity);
				} catch (Exception e) {
					String msg = String.format("第 %d 列第 %d 欄資料有問題! %s", r+1, c+1, e.getMessage());
					res.addErrMsg(msg);
					log.error(msg, e);					
				}
			}
		} catch (IOException e) {
			throw e;
		}
		
		return res;
	}
	
	public XSSFWorkbook exportRawData(TalentedPeopleSearchModel arg) {
		List<TalentedPeople> list = this.talentedPeopleDao.listAll(arg);
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("中文姓名");
			row.createCell(++c).setCellValue("英文姓名");
			row.createCell(++c).setCellValue("性別");
			row.createCell(++c).setCellValue("產學經驗(年)");
			row.createCell(++c).setCellValue("領域別");
			row.createCell(++c).setCellValue("次領域");
			row.createCell(++c).setCellValue("聯絡電話");
			row.createCell(++c).setCellValue("e-mail");
			row.createCell(++c).setCellValue("現職單位");
			row.createCell(++c).setCellValue("現職職位");
			row.createCell(++c).setCellValue("網站連結");
			row.createCell(++c).setCellValue("合作專長");
		}
		
		// data part
		for (TalentedPeople tp : list) {
			row = sheet.createRow(++r);
			c = -1;
			ExcelUtil.createNSetCellValue(row, ++c, tp.getNameCh());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getNameEn());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getGender());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getExpYear());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getMainDomainCodeString());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getSubDomainCodeString());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getTel());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getEmail());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getWorkOrg());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getJob());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getUrl());
			ExcelUtil.createNSetCellValue(row, ++c, tp.getSpecialty());
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
		String reportSource = context.getRealPath("/report/jasper/talentedPeople/talentedPeople.jasper");
		FileInputStream fis = new FileInputStream(reportSource);
		
		// outputStream
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		// parameters
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TALENTED_PEOPLE_ID", id);
		
		// session
		Session session = HibernateSessionFactory.getSession();
		SessionImpl sessionImpl = (SessionImpl) session; 
		Connection conn = sessionImpl.connection();
		
		// run
		JasperRunManager.runReportToPdfStream(fis, os, parameters, conn);
		return new ByteArrayInputStream(os.toByteArray());
	}
}
