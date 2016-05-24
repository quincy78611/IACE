package iace.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.service.BaseService;
import core.util.CloseableTool;
import iace.entity.Patent;
import iace.entity.PatentPicture;
import iace.entity.TechField;
import iace.entity.option.OptionTrl;

public class PatentExcelService {
	protected static Logger log = LogManager.getLogger(BaseService.class);	
	
	private XSSFWorkbook getXlsxFile(File file) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			return wb;
		} catch (IOException e) {
			throw e;
		} finally {
			CloseableTool.close(fis);
		}
	}
	
	private Map<Integer, PatentPicture> getPatnetPictures(XSSFSheet sheet) {
		XSSFDrawing draw = sheet.createDrawingPatriarch();
		List<XSSFShape> pics = draw.getShapes();
		Map<Integer, PatentPicture> patentPics = new HashMap<Integer, PatentPicture>();
		for (XSSFShape s : pics) {
			PatentPicture pp = new PatentPicture((XSSFPicture)s);
			patentPics.put(pp.getRow1(), pp);
		}
		return patentPics;
	}
	
	public List<Patent> excelToPatents(File file) throws IOException {
		XSSFWorkbook wb = getXlsxFile(file);
		XSSFSheet sheet = wb.getSheetAt(0);	
		Map<Integer, PatentPicture> pics =  getPatnetPictures(sheet);		
		List<Patent> patentList = new ArrayList<Patent>();
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
			int c = -1;
			try {
				XSSFRow row = sheet.getRow(r);
				Patent p = new Patent();
				p.setName(row.getCell(++c).getStringCellValue());
				p.setAssignee(row.getCell(++c).getStringCellValue());
				p.setInvertor(row.getCell(++c).getStringCellValue());
				p.setCountry(row.getCell(++c).getStringCellValue());
				p.setAppliactionNo(row.getCell(++c).getStringCellValue());
				p.setApplicationDate(row.getCell(++c).getDateCellValue());
				p.setOpenNo(row.getCell(++c).getStringCellValue());
				p.setOpenDate(row.getCell(++c).getDateCellValue());
				p.setPublicationNo(row.getCell(++c, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				p.setPublicationDate(row.getCell(++c, Row.CREATE_NULL_AS_BLANK).getDateCellValue());
				p.setCategory(row.getCell(++c).getStringCellValue());
				p.setPatentStatus(row.getCell(++c).getStringCellValue());
				p.setFamilyNo(row.getCell(++c).getStringCellValue());
				p.setIpc(row.getCell(++c).getStringCellValue());
				TechField tf = new TechField();
				tf.setName(row.getCell(++c).getStringCellValue());
				p.setTechField(tf);
				p.setUsage(row.getCell(++c, Row.CREATE_NULL_AS_BLANK).getStringCellValue());
				p.setTechAbstract(row.getCell(++c).getStringCellValue());
//				p.setImportantPictureCode(row.getCell(++c).getStringCellValue());
				p.setImportantPatentPicture(pics.get(r).getData());
				p.setImportantPatentPictureExtension(pics.get(r).getFileExtension());
				p.setTrl(new OptionTrl());
				
				patentList.add(p);
			} catch (Exception e) {
				String msg = String.format("第 %d 列第 %d 欄資料有問題", r+1, c+1);
				throw new IllegalArgumentException(msg);
			}
		}
		return patentList;
	}


}
