package iace.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import iace.entity.qnrCooperateWay.QnrCooperateWayLinkModel;

public class QnrCooperateWayExcelService extends BaseExcelService {

	public XSSFWorkbook exportQnrLinksExcel(List<QnrCooperateWayLinkModel> qnrCooperateWayLinks, String urlPart0To3, String urlPart4) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		XSSFCell cell = null;
		
		{//title row
			row = sheet.createRow(++r);
			
			cell = row.createCell(++c);
			cell.setCellValue("學校");
			
			cell = row.createCell(++c);
			cell.setCellValue("前三部分連結");
			
			cell = row.createCell(++c);
			cell.setCellValue("第四部份連結");
		}
		
		for (QnrCooperateWayLinkModel model : qnrCooperateWayLinks) {
			c = -1;
			row = sheet.createRow(++r);
			
			cell = row.createCell(++c);
			cell.setCellValue(model.getSchool().getName());
			
			cell = row.createCell(++c);
			cell.setCellValue(urlPart0To3+"?encryptSchoolId="+model.getEncryptSchoolId());
			
			cell = row.createCell(++c);
			cell.setCellValue(urlPart4+"?encryptSchoolId="+model.getEncryptSchoolId());
		}
		
		for (int i=0; i<3; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	
}
