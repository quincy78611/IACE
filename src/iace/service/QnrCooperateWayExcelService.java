package iace.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import iace.entity.option.OptionSchool;
import iace.entity.qnrCooperateWay.QnrCooperateWay;
import iace.entity.qnrCooperateWay.QnrCooperateWayMerit;

public class QnrCooperateWayExcelService extends BaseExcelService {

	public XSSFWorkbook exportQnrLinksExcel(List<OptionSchool> schools, String qnrUrl) {
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
			cell.setCellValue("問卷連結");
		}
		
		for (OptionSchool school : schools) {
			c = -1;
			row = sheet.createRow(++r);
			
			cell = row.createCell(++c);
			cell.setCellValue(school.getCode());
			
			cell = row.createCell(++c);
			cell.setCellValue(school.getName());
			
			cell = row.createCell(++c);
			cell.setCellValue(qnrUrl+"?encryptSchoolId="+school.getEncryptedId());
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	
	public XSSFWorkbook exportQnrResulotExcel(List<QnrCooperateWay> qnrList) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("填寫時間");
			row.createCell(++c).setCellValue("學校代碼");
			row.createCell(++c).setCellValue("學校");
			row.createCell(++c).setCellValue("同意");
			row.createCell(++c).setCellValue("姓名");
			row.createCell(++c).setCellValue("身分證號");
			row.createCell(++c).setCellValue("Email");
			row.createCell(++c).setCellValue("地址");
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
			
			for (int year : QnrCooperateWayMerit.YEARS) {
				row.createCell(++c).setCellValue(year+"企業委託研究-計畫件數");
				row.createCell(++c).setCellValue(year+"企業委託研究-總經費(NT$萬)");
				row.createCell(++c).setCellValue(year+"獲證專利-獲證件數(國內)");
				row.createCell(++c).setCellValue(year+"獲證專利-獲證件數(國外)");
				row.createCell(++c).setCellValue(year+"專利授權-廠商家數");
				row.createCell(++c).setCellValue(year+"專利授權-授權收入");
				row.createCell(++c).setCellValue(year+"技術移轉-件數");
				row.createCell(++c).setCellValue(year+"技術移轉-廠商家數");
				row.createCell(++c).setCellValue(year+"技術移轉-技轉收入");
				row.createCell(++c).setCellValue(year+"研發活動主管單位-預算");
				row.createCell(++c).setCellValue(year+"研發活動主管單位-年平均受雇全職人數");
				row.createCell(++c).setCellValue(year+"產學合作主管單位-預算");
				row.createCell(++c).setCellValue(year+"產學合作主管單位-年平均受雇全職人數");
				row.createCell(++c).setCellValue(year+"技轉中心-政府補助經費");
				row.createCell(++c).setCellValue(year+"技轉中心-受雇人數");
				row.createCell(++c).setCellValue(year+"育成中心-政府補助經費");
				row.createCell(++c).setCellValue(year+"育成中心-受雇人數");
				row.createCell(++c).setCellValue(year+"育成中心-進駐家數");
				row.createCell(++c).setCellValue(year+"育成中心-育成中心回饋收入");
				row.createCell(++c).setCellValue(year+"育成中心-本校師生創業進駐家數");
			}
		}//title row END		
		
		// data part
		for (QnrCooperateWay qnr : qnrList) {
			c = -1;
			row = sheet.createRow(++r);
			super.setCellValue(row.createCell(++c), qnr.getCreateTime());
			row.createCell(++c).setCellValue(qnr.getSchool().getCode());
			row.createCell(++c).setCellValue(qnr.getSchool().getName());
			row.createCell(++c).setCellValue(qnr.getAggreePDPL() ? "是" : "否");
			row.createCell(++c).setCellValue(qnr.getName());
			row.createCell(++c).setCellValue(qnr.getApplicantId());
			row.createCell(++c).setCellValue(qnr.getEmail());
			row.createCell(++c).setCellValue(qnr.getAddress());
			row.createCell(++c).setCellValue(qnr.getQ0_1());
			row.createCell(++c).setCellValue(qnr.getQ0_2());
			row.createCell(++c).setCellValue(qnr.getQ0_3());
			row.createCell(++c).setCellValue(qnr.getQ1_1());
			row.createCell(++c).setCellValue(qnr.getQ1_2());
			row.createCell(++c).setCellValue(qnr.getQ1_3());
			row.createCell(++c).setCellValue(qnr.getQ1_4());
			row.createCell(++c).setCellValue(qnr.getQ1_5());
			row.createCell(++c).setCellValue(qnr.getQ1_6());
			row.createCell(++c).setCellValue(qnr.getQ1_7());
			row.createCell(++c).setCellValue(qnr.getQ1_8());
			row.createCell(++c).setCellValue(qnr.getQ1_9());
			row.createCell(++c).setCellValue(qnr.getQ1_10());
			row.createCell(++c).setCellValue(qnr.getQ1_11());
			row.createCell(++c).setCellValue(qnr.getQ1_12());
			row.createCell(++c).setCellValue(qnr.getQ1_13());
			row.createCell(++c).setCellValue(qnr.getQ1_14());
			row.createCell(++c).setCellValue(qnr.getQ1_15());
			row.createCell(++c).setCellValue(qnr.getQ2_1());
			row.createCell(++c).setCellValue(qnr.getQ2_2());
			row.createCell(++c).setCellValue(qnr.getQ2_3());
			row.createCell(++c).setCellValue(qnr.getQ2_4());
			row.createCell(++c).setCellValue(qnr.getQ2_5());
			row.createCell(++c).setCellValue(qnr.getQ2_6());
			row.createCell(++c).setCellValue(qnr.getQ2_7());
			row.createCell(++c).setCellValue(qnr.getQ2_8());
			row.createCell(++c).setCellValue(qnr.getQ2_9());
			row.createCell(++c).setCellValue(qnr.getQ2_10());
			row.createCell(++c).setCellValue(qnr.getQ3_1());
			row.createCell(++c).setCellValue(qnr.getQ3_2());
			row.createCell(++c).setCellValue(qnr.getQ3_3());
			row.createCell(++c).setCellValue(qnr.getQ3_4());
			row.createCell(++c).setCellValue(qnr.getQ3_5());
			row.createCell(++c).setCellValue(qnr.getQ3_6());
			row.createCell(++c).setCellValue(qnr.getQ3_7());
			row.createCell(++c).setCellValue(qnr.getQ3_8());
			row.createCell(++c).setCellValue(qnr.getQ3_9());
			row.createCell(++c).setCellValue(qnr.getQ3_10());
			row.createCell(++c).setCellValue(qnr.getQ3_11());
			row.createCell(++c).setCellValue(qnr.getQ3_12());
			row.createCell(++c).setCellValue(qnr.getQ3_13());
			row.createCell(++c).setCellValue(qnr.getQ3_14());
			
			for (QnrCooperateWayMerit merit : qnr.getQnrCooperateWayMerits()) {
				super.setCellValue(row.createCell(++c), merit.getP1_1_1());
				super.setCellValue(row.createCell(++c), merit.getP1_1_2());
				super.setCellValue(row.createCell(++c), merit.getP1_2_1());
				super.setCellValue(row.createCell(++c), merit.getP1_2_2());
				super.setCellValue(row.createCell(++c), merit.getP1_3_1());
				super.setCellValue(row.createCell(++c), merit.getP1_3_2());
				super.setCellValue(row.createCell(++c), merit.getP1_4_1());
				super.setCellValue(row.createCell(++c), merit.getP1_4_2());
				super.setCellValue(row.createCell(++c), merit.getP1_4_3());
				super.setCellValue(row.createCell(++c), merit.getP2_1_1());
				super.setCellValue(row.createCell(++c), merit.getP2_1_2());
				super.setCellValue(row.createCell(++c), merit.getP2_2_1());
				super.setCellValue(row.createCell(++c), merit.getP2_2_2());
				super.setCellValue(row.createCell(++c), merit.getP2_3_1());
				super.setCellValue(row.createCell(++c), merit.getP2_3_2());
				super.setCellValue(row.createCell(++c), merit.getP2_4_1());
				super.setCellValue(row.createCell(++c), merit.getP2_4_2());
				super.setCellValue(row.createCell(++c), merit.getP2_4_3());
				super.setCellValue(row.createCell(++c), merit.getP2_4_4());
				super.setCellValue(row.createCell(++c), merit.getP2_4_5());
			}
		}
		
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
}
