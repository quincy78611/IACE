package iace.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import iace.entity.qnrCooperateWay.QnrCooperateWay;
import iace.entity.qnrCooperateWay.QnrCooperateWayLinkModel;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;

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
			cell.setCellValue("學校代碼");
			
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
			cell.setCellValue(model.getSchool().getCode());
			
			cell = row.createCell(++c);
			cell.setCellValue(model.getSchool().getName());
			
			cell = row.createCell(++c);
			cell.setCellValue(urlPart0To3+"?encryptSchoolId="+model.getEncryptSchoolId());
			
			cell = row.createCell(++c);
			cell.setCellValue(urlPart4+"?encryptSchoolId="+model.getEncryptSchoolId());
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	
	public XSSFWorkbook exportQnrPart0To3ResultExcel(List<QnrCooperateWay> qnrList) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("學校代碼");
			row.createCell(++c).setCellValue("學校");
			row.createCell(++c).setCellValue("同意");
			row.createCell(++c).setCellValue("姓名");
			row.createCell(++c).setCellValue("Email");
			row.createCell(++c).setCellValue("Q0_1");
			row.createCell(++c).setCellValue("Q0_2");
			row.createCell(++c).setCellValue("Q0_3");
			row.createCell(++c).setCellValue("Q1_1");
			row.createCell(++c).setCellValue("Q1_2");
			row.createCell(++c).setCellValue("Q1_3");
			row.createCell(++c).setCellValue("Q1_4");
			row.createCell(++c).setCellValue("Q1_5");
			row.createCell(++c).setCellValue("Q1_6");
			row.createCell(++c).setCellValue("Q1_7");
			row.createCell(++c).setCellValue("Q1_8");
			row.createCell(++c).setCellValue("Q1_9");
			row.createCell(++c).setCellValue("Q1_10");
			row.createCell(++c).setCellValue("Q1_11");
			row.createCell(++c).setCellValue("Q1_12");
			row.createCell(++c).setCellValue("Q1_13");
			row.createCell(++c).setCellValue("Q1_14");
			row.createCell(++c).setCellValue("Q1_15");
			row.createCell(++c).setCellValue("Q2_1");
			row.createCell(++c).setCellValue("Q2_2");
			row.createCell(++c).setCellValue("Q2_3");
			row.createCell(++c).setCellValue("Q2_4");
			row.createCell(++c).setCellValue("Q2_5");
			row.createCell(++c).setCellValue("Q2_6");
			row.createCell(++c).setCellValue("Q2_7");
			row.createCell(++c).setCellValue("Q2_8");
			row.createCell(++c).setCellValue("Q2_9");
			row.createCell(++c).setCellValue("Q2_10");
			row.createCell(++c).setCellValue("Q3_1");
			row.createCell(++c).setCellValue("Q3_2");
			row.createCell(++c).setCellValue("Q3_3");
			row.createCell(++c).setCellValue("Q3_4");
			row.createCell(++c).setCellValue("Q3_5");
			row.createCell(++c).setCellValue("Q3_6");
			row.createCell(++c).setCellValue("Q3_7");
			row.createCell(++c).setCellValue("Q3_8");
			row.createCell(++c).setCellValue("Q3_9");
			row.createCell(++c).setCellValue("Q3_10");
			row.createCell(++c).setCellValue("Q3_11");
			row.createCell(++c).setCellValue("Q3_12");
			row.createCell(++c).setCellValue("Q3_13");
			row.createCell(++c).setCellValue("Q3_14");
		}
		
		for (QnrCooperateWay model : qnrList) {
			c = -1;
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue(model.getSchool().getCode());
			row.createCell(++c).setCellValue(model.getSchool().getName());
			row.createCell(++c).setCellValue(model.getAggreePDPL() ? "是" : "否");
			row.createCell(++c).setCellValue(model.getName());
			row.createCell(++c).setCellValue(model.getEmail());
			row.createCell(++c).setCellValue(model.getQ0_1());
			row.createCell(++c).setCellValue(model.getQ0_2());
			row.createCell(++c).setCellValue(model.getQ0_3());
			row.createCell(++c).setCellValue(model.getQ1_1());
			row.createCell(++c).setCellValue(model.getQ1_2());
			row.createCell(++c).setCellValue(model.getQ1_3());
			row.createCell(++c).setCellValue(model.getQ1_4());
			row.createCell(++c).setCellValue(model.getQ1_5());
			row.createCell(++c).setCellValue(model.getQ1_6());
			row.createCell(++c).setCellValue(model.getQ1_7());
			row.createCell(++c).setCellValue(model.getQ1_8());
			row.createCell(++c).setCellValue(model.getQ1_9());
			row.createCell(++c).setCellValue(model.getQ1_10());
			row.createCell(++c).setCellValue(model.getQ1_11());
			row.createCell(++c).setCellValue(model.getQ1_12());
			row.createCell(++c).setCellValue(model.getQ1_13());
			row.createCell(++c).setCellValue(model.getQ1_14());
			row.createCell(++c).setCellValue(model.getQ1_15());
			row.createCell(++c).setCellValue(model.getQ2_1());
			row.createCell(++c).setCellValue(model.getQ2_2());
			row.createCell(++c).setCellValue(model.getQ2_3());
			row.createCell(++c).setCellValue(model.getQ2_4());
			row.createCell(++c).setCellValue(model.getQ2_5());
			row.createCell(++c).setCellValue(model.getQ2_6());
			row.createCell(++c).setCellValue(model.getQ2_7());
			row.createCell(++c).setCellValue(model.getQ2_8());
			row.createCell(++c).setCellValue(model.getQ2_9());
			row.createCell(++c).setCellValue(model.getQ2_10());
			row.createCell(++c).setCellValue(model.getQ3_1());
			row.createCell(++c).setCellValue(model.getQ3_2());
			row.createCell(++c).setCellValue(model.getQ3_3());
			row.createCell(++c).setCellValue(model.getQ3_4());
			row.createCell(++c).setCellValue(model.getQ3_5());
			row.createCell(++c).setCellValue(model.getQ3_6());
			row.createCell(++c).setCellValue(model.getQ3_7());
			row.createCell(++c).setCellValue(model.getQ3_8());
			row.createCell(++c).setCellValue(model.getQ3_9());
			row.createCell(++c).setCellValue(model.getQ3_10());
			row.createCell(++c).setCellValue(model.getQ3_11());
			row.createCell(++c).setCellValue(model.getQ3_12());
			row.createCell(++c).setCellValue(model.getQ3_13());
			row.createCell(++c).setCellValue(model.getQ3_14());
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	
	public XSSFWorkbook exportQnrPart4ResultExcel(List<QnrCooperateWayMerit> qnrList) {
		//TODO
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row			
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("學校代碼");
			row.createCell(++c).setCellValue("學校");
			row.createCell(++c).setCellValue("年度");
			row.createCell(++c).setCellValue("企業委託研究-計畫件數");
			row.createCell(++c).setCellValue("企業委託研究-總經費(NT$萬)");
			row.createCell(++c).setCellValue("獲證專利-獲證件數(國內)");
			row.createCell(++c).setCellValue("獲證專利-獲證件數(國外)");
			row.createCell(++c).setCellValue("專利授權-廠商家數");
			row.createCell(++c).setCellValue("專利授權-授權收入");
			row.createCell(++c).setCellValue("技術移轉-件數");
			row.createCell(++c).setCellValue("技術移轉-廠商家數");
			row.createCell(++c).setCellValue("技術移轉-技轉收入");
			row.createCell(++c).setCellValue("研發活動主管單位-預算");
			row.createCell(++c).setCellValue("研發活動主管單位-年平均受雇全職人數");
			row.createCell(++c).setCellValue("產學合作主管單位-預算");
			row.createCell(++c).setCellValue("產學合作主管單位-年平均受雇全職人數");
			row.createCell(++c).setCellValue("技轉中心-政府補助經費");
			row.createCell(++c).setCellValue("技轉中心-受雇人數");
			row.createCell(++c).setCellValue("育成中心-政府補助經費");
			row.createCell(++c).setCellValue("育成中心-受雇人數");
			row.createCell(++c).setCellValue("育成中心-進駐家數");
			row.createCell(++c).setCellValue("育成中心-育成中心回饋收入");
			row.createCell(++c).setCellValue("育成中心-本校師生創業進駐家數");
		}
		
		for (QnrCooperateWayMerit model : qnrList) {
			c = -1;
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue(model.getSchool().getCode());
			row.createCell(++c).setCellValue(model.getSchool().getName());
			row.createCell(++c).setCellValue(model.getYear());
			super.setCellValue(row.createCell(++c), model.getP1_1_1());
			super.setCellValue(row.createCell(++c), model.getP1_1_2());
			super.setCellValue(row.createCell(++c), model.getP1_2_1());
			super.setCellValue(row.createCell(++c), model.getP1_2_2());
			super.setCellValue(row.createCell(++c), model.getP1_3_1());
			super.setCellValue(row.createCell(++c), model.getP1_3_2());
			super.setCellValue(row.createCell(++c), model.getP1_4_1());
			super.setCellValue(row.createCell(++c), model.getP1_4_2());
			super.setCellValue(row.createCell(++c), model.getP1_4_3());
			super.setCellValue(row.createCell(++c), model.getP2_1_1());
			super.setCellValue(row.createCell(++c), model.getP2_1_2());
			super.setCellValue(row.createCell(++c), model.getP2_2_1());
			super.setCellValue(row.createCell(++c), model.getP2_2_2());
			super.setCellValue(row.createCell(++c), model.getP2_3_1());
			super.setCellValue(row.createCell(++c), model.getP2_3_2());
			super.setCellValue(row.createCell(++c), model.getP2_4_1());
			super.setCellValue(row.createCell(++c), model.getP2_4_2());
			super.setCellValue(row.createCell(++c), model.getP2_4_3());
			super.setCellValue(row.createCell(++c), model.getP2_4_4());
			super.setCellValue(row.createCell(++c), model.getP2_4_5());
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
}
