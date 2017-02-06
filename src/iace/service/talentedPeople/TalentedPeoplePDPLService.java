package iace.service.talentedPeople;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.ExcelUtil;
import iace.dao.talentedPeople.ITalentedPeoplePDPLDao;
import iace.entity.talentedPeople.TalentedPeoplePDPL;
import iace.service.BaseIaceService;

public class TalentedPeoplePDPLService extends BaseIaceService<TalentedPeoplePDPL> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private ITalentedPeoplePDPLDao dao;
	
	public TalentedPeoplePDPLService(ITalentedPeoplePDPLDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public TalentedPeoplePDPL getByTalentedPeopleID(long talentedPeopleID) {
		return this.dao.getByTalentedPeopleID(talentedPeopleID);
	}

	public XSSFWorkbook exportList() {
		List<TalentedPeoplePDPL> list = this.dao.listAll();
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		//title row
		{
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("id");
			row.createCell(++c).setCellValue("姓名");
			row.createCell(++c).setCellValue("同意個資收集");
			row.createCell(++c).setCellValue("IP");
			row.createCell(++c).setCellValue("最後更新時間");
		}
		
		// data part
		for (TalentedPeoplePDPL pdpl : list) {
			if (pdpl.getAgreePDPL() != null) {
				row = sheet.createRow(++r);
				c = -1;
				ExcelUtil.createNSetCellValue(row, ++c, pdpl.getTalentedPeople().getId());
				ExcelUtil.createNSetCellValue(row, ++c, pdpl.getTalentedPeople().getNameCh());
				ExcelUtil.createNSetCellValue(row, ++c, pdpl.getAgreePDPL());
				ExcelUtil.createNSetCellValue(row, ++c, pdpl.getIp());
				ExcelUtil.createNSetCellValue(row, ++c, sdf.format(pdpl.getUpdateTime()));
			}
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	
	public boolean isIndexing(long talentedPeopleId) {
		TalentedPeoplePDPL pdpl = this.getByTalentedPeopleID(talentedPeopleId);
		if (pdpl == null || pdpl.getAgreePDPL() == null || pdpl.getAgreePDPL() == false) {
			return false;
		} else {
			return true;
		}
	}
}
