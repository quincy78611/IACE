package core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static InputStream workbookToInputStream(XSSFWorkbook wb) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		return new ByteArrayInputStream(baos.toByteArray());
	}
	
	public static XSSFCell createNSetCellValue(XSSFRow row, int columnIndex, Boolean value) {
		XSSFCell cell = row.createCell(columnIndex);
		if (value != null) {
			cell.setCellValue(value);
		}
		return cell;
	}

	public static XSSFCell createNSetCellValue(XSSFRow row, int columnIndex, Calendar value) {
		XSSFCell cell = row.createCell(columnIndex);
		if (value != null) {
			cell.setCellValue(value);
		}
		return cell;
	}
	
	public static XSSFCell createNSetCellValue(XSSFRow row, int columnIndex, Date value) {
		XSSFCell cell = row.createCell(columnIndex);
		if (value != null) {
			cell.setCellValue(value);
		}
		return cell;
	}
	
	public static XSSFCell createNSetCellValue(XSSFRow row, int columnIndex, Double value) {
		XSSFCell cell = row.createCell(columnIndex);
		if (value != null) {
			cell.setCellValue(value);
		}
		return cell;
	}
	
	public static XSSFCell createNSetCellValue(XSSFRow row, int columnIndex, Integer value) {
		XSSFCell cell = row.createCell(columnIndex);
		if (value != null) {
			cell.setCellValue(value);
		}
		return cell;
	}	
	
	public static XSSFCell createNSetCellValue(XSSFRow row, int columnIndex, String value) {
		XSSFCell cell = row.createCell(columnIndex);
		if (value != null) {
			cell.setCellValue(value);
		}
		return cell;
	}
	
	
}
