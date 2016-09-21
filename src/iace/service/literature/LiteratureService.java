package iace.service.literature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.PagedList;
import iace.dao.literature.ILiteratureDao;
import iace.entity.BaseBatchImportResult;
import iace.entity.literature.Literature;
import iace.entity.literature.LiteratureSearchModel;
import iace.service.BaseIaceService;

public class LiteratureService extends BaseIaceService<Literature> {

	private ILiteratureDao dao;

	public LiteratureService(ILiteratureDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public PagedList<Literature> searchBy(LiteratureSearchModel arg) {
		return this.dao.searchBy(arg);
	}

	public BaseBatchImportResult<Literature> batchImport(File file) throws IOException {
		BaseBatchImportResult<Literature> res = new BaseBatchImportResult<Literature>();
		List<Long> oidList = this.dao.listAllOid();

		try (FileInputStream fis = new FileInputStream(file);) {
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
				int c = -1;
				XSSFRow row = sheet.getRow(r);
				XSSFCell cell;
				try {
					Literature entity = null;

					// ID
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					long oid = Long.valueOf(cell.getStringCellValue().trim());
					if (oidList.contains(oid)) {
						entity = this.dao.getByOid(oid);
					} else {
						entity = new Literature();
					}
					entity.setOid(oid);
					// ----------------------------------------------------------

					// 中文題名
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setTitleC(cell.getStringCellValue().trim());

					// 外文題名
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setTitleF(cell.getStringCellValue().trim());

					// 作者(中文)
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setAuthorC(cell.getStringCellValue().trim());

					// 作者(外文)
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setAuthorF(cell.getStringCellValue().trim());

					// 作者服務機構
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setOrg(cell.getStringCellValue().trim());

					// 中文關鍵詞
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setKeywordC(cell.getStringCellValue().trim());

					// 外文關鍵詞
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setKeywordF(cell.getStringCellValue().trim());

					// 語文
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setLanguage(cell.getStringCellValue().trim());

					// 原始中文摘要
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSummary(cell.getStringCellValue().trim());

					// 原始外文摘要
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSummaryF(cell.getStringCellValue().trim());

					// 連結網址
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setLinkUrl(cell.getStringCellValue().trim());

					// 卷期頁碼(頁數)
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setPagination(cell.getStringCellValue().trim());

					// 出版年
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setPublishYear(cell.getStringCellValue().trim());

					// 指導教授
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setAdvisor(cell.getStringCellValue().trim());

					// 論文出版年月
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setPublicationDate(cell.getStringCellValue().trim());

					// 學位
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setDegree(cell.getStringCellValue().trim());

					// 系所別
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setDepartment(cell.getStringCellValue().trim());

					// 畢業學校中文校名
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setGraduateSchoolC(cell.getStringCellValue().trim());

					// 期刊名稱
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setJournalName(cell.getStringCellValue().trim());

					// 資料來源
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setSource(cell.getStringCellValue().trim());

					// 領域分類
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setCodeIndustryId(cell.getStringCellValue().trim());

					// 探討國別
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setCountry(cell.getStringCellValue().trim());

					// 資料分類(文獻/法規政策)
					cell = row.getCell(++c);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					entity.setCategory(cell.getStringCellValue().trim());

					// ----------------------------------------------------------
					if (oidList.contains(entity.getOid())) {
						this.dao.update(entity);
						res.addRecordToUpdateList(entity);
					} else {
						this.dao.create(entity);
						res.addRecordToInsertList(entity);
					}
				} catch (Exception e) {
					String msg = String.format("第 %d 列第 %d 欄資料有問題! %s", r + 1, c + 1, e.getMessage());
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
