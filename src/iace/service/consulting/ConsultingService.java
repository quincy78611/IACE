package iace.service.consulting;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.ExcelUtil;
import core.util.PagedList;
import iace.dao.consulting.IConsultingDao;
import iace.entity.consulting.Consulting;
import iace.entity.consulting.ConsultingSearchModel;
import iace.service.BaseIaceService;

public class ConsultingService extends BaseIaceService<Consulting> {
	private IConsultingDao consultingDao;
	
	public ConsultingService(IConsultingDao dao) {
		super(dao);
		this.consultingDao = dao;
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
}
