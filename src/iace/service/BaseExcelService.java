package iace.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.CloseableTool;

public abstract class BaseExcelService {
	protected static Logger log = Logger.getLogger(BaseExcelService.class);
	
	protected XSSFSheet currentSheet;
	
	protected XSSFWorkbook getXlsxFile(File file) throws IOException {
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
	
	/**
	 * 
	 * @param r row index. start with 0
	 * @param c column index. start with 0
	 * @return
	 */
	protected XSSFCell getCell(int r, int c) {
		XSSFRow row = this.currentSheet.getRow(r);
		XSSFCell cell = row.getCell(c, Row.CREATE_NULL_AS_BLANK);
		return cell;		
	}
	
	protected XSSFCell setCellValue(XSSFCell cell, String value) {
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
		return cell;
	}
	
	XSSFCell setCellValue(XSSFCell cell, Integer value) {
		if (value != null) {
			cell.setCellValue(value);
		} else {
			cell.setCellValue("");
		}
		return cell;
	}

}
