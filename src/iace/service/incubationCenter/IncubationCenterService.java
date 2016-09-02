package iace.service.incubationCenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.PagedList;
import iace.dao.incubationCenter.IIncubationCenterDao;
import iace.entity.BaseBatchImportResult;
import iace.entity.incubationCenter.IncubationCenter;
import iace.entity.incubationCenter.IncubationCenterSearchModel;
import iace.service.BaseIaceService;

public class IncubationCenterService extends BaseIaceService<IncubationCenter> {

	private IIncubationCenterDao dao;
	
	public IncubationCenterService(IIncubationCenterDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public PagedList<IncubationCenter> searchBy(IncubationCenterSearchModel arg) {
		return this.dao.searchBy(arg);
	}

	public BaseBatchImportResult<IncubationCenter> batchImport(File file) throws IOException {
		BaseBatchImportResult<IncubationCenter> res = new BaseBatchImportResult<IncubationCenter>();
		
		try (FileInputStream fis = new FileInputStream(file);){
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
				int c = -1;
				XSSFRow row = sheet.getRow(r);
				XSSFCell cell;
				try {
					IncubationCenter entity = new IncubationCenter();
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSeqNo(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSchoolCode(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setAttribute(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSchoolNameCh(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setOrgNameCh(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSchoolNameEn(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setOrgNameEn(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setBossName(cell.getStringCellValue().trim());
					
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setBossTitle(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setAddress(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setTel(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setEmail(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setUrl(cell.getStringCellValue().trim());

					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setOrgHistory(cell.getStringCellValue().trim());

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
}
