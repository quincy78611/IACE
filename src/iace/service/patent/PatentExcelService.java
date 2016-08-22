package iace.service.patent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import iace.entity.option.OptionTrl;
import iace.entity.patent.Patent;
import iace.entity.patent.PatentPicture;
import iace.entity.patent.TechField;
import iace.service.BaseExcelService;

public class PatentExcelService extends BaseExcelService {
	
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
		super.currentSheet = wb.getSheetAt(0);	
		Map<Integer, PatentPicture> pics =  getPatnetPictures(super.currentSheet);		
		List<Patent> patentList = new ArrayList<Patent>();
		for (int r = 1; r <= super.currentSheet.getLastRowNum(); r++) {
			int c = -1;
			try {
				//TODO validate
				Patent p = new Patent();
				p.setName(getCell(r, ++c).getStringCellValue());
				p.setAssignee(getCell(r, ++c).getStringCellValue());
				p.setInvertor(getCell(r, ++c).getStringCellValue());
				p.setCountryByCode(getCell(r, ++c).getStringCellValue());
				p.setAppliactionNo(getCell(r, ++c).getStringCellValue());
				Date applicationDate = getCell(r, ++c).getDateCellValue();
				p.setApplicationDate(new java.sql.Date(applicationDate.getTime()));
				p.setOpenNo(getCell(r, ++c).getStringCellValue());
				Date openDate = getCell(r, ++c).getDateCellValue();
				p.setOpenDate(openDate == null ? null : new java.sql.Date(openDate.getTime()));
				p.setPublicationNo(getCell(r, ++c).getStringCellValue());
				Date publicationDate = getCell(r, ++c).getDateCellValue();
				p.setPublicationDate(publicationDate == null ? null : new java.sql.Date(publicationDate.getTime()));
				p.setCategory(getCell(r, ++c).getStringCellValue());
				p.setPatentStatus(getCell(r, ++c).getStringCellValue());
				p.setFamilyNo(getCell(r, ++c).getStringCellValue());
				p.setIpc(getCell(r, ++c).getStringCellValue());
				TechField tf = new TechField();
				tf.setName(getCell(r, ++c).getStringCellValue());
				p.setTechField(tf);
				p.setUsage(getCell(r, ++c).getStringCellValue());
				p.setTechAbstract(getCell(r, ++c).getStringCellValue());
//				p.setImportantPictureCode(getCell(r, ++c).getStringCellValue());
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
