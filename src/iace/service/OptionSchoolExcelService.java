package iace.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import iace.entity.option.OptionSchool;

public class OptionSchoolExcelService extends BaseExcelService {

	public List<OptionSchool> excelToOptionSchoolList(File file) throws IOException {
		List<OptionSchool> optionSchoolList = new ArrayList<OptionSchool>();
		XSSFWorkbook wb = getXlsxFile(file);
		super.currentSheet = wb.getSheetAt(0);	
		for (int r = 1; r <= super.currentSheet.getLastRowNum(); r++) {
			int c = -1;
			try {
				OptionSchool option = new OptionSchool();
				option.setCode(getCell(r, ++c).getStringCellValue().trim());
				option.setName(getCell(r, ++c).getStringCellValue().trim());
				if (option.getCode().length() == 4) {
					optionSchoolList.add(option);
				}				
			} catch (Exception e) {
				String msg = String.format("第 %d 列第 %d 欄資料有問題", r+1, c+1);
				throw new IllegalArgumentException(msg);
			}
		}
		
		return optionSchoolList;
	}
	
}
