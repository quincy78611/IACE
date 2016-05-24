package iace.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.service.BaseService;
import core.util.CloseableTool;
import iace.entity.ResearchPlan;
import iace.entity.RnDResult;

public class ResearchPlanExcelService {
	protected static Logger log = Logger.getLogger(BaseService.class);
	
	private XSSFWorkbook getXlsxFile(File file) throws IOException {
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
	
	public List<ResearchPlan> excelToResearchPlans(File file) throws IOException {
		XSSFWorkbook wb = getXlsxFile(file);
		XSSFSheet sheet = wb.getSheetAt(0);
		Map<String, ResearchPlan> researchPlanMap = new LinkedHashMap<String, ResearchPlan>();
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {
			int c = -1;
			try {
				XSSFRow row = sheet.getRow(r);
				
				//TODO validate
				
				ResearchPlan rp = new ResearchPlan();
				rp.setYear((int) row.getCell(++c).getNumericCellValue());
				rp.setPlanNo(row.getCell(++c).getStringCellValue());
				rp.setName(row.getCell(++c).getStringCellValue());
				rp.setManager(row.getCell(++c).getStringCellValue());				
				rp.setGrbDomains(row.getCell(++c).getRawValue().split(";"));
				rp.setKeyword(row.getCell(++c).getStringCellValue());
				rp.setTrlCode(row.getCell(++c).getStringCellValue());
				rp.setProjkey(row.getCell(++c).getStringCellValue());
				rp.setGrb05Id(row.getCell(++c).getRawValue());
				
				RnDResult rnd = new RnDResult();
				rnd.setName(row.getCell(++c).getStringCellValue());
				rnd.setDescriptoin(row.getCell(++c).getStringCellValue());
				rnd.setTrlCode(row.getCell(++c).getStringCellValue());
				rnd.setTrlDesc(row.getCell(++c).getStringCellValue());
				
				String key = rp.getPlanNo();
				if (researchPlanMap.containsKey(key)) {
					rp = researchPlanMap.get(key);
//					rnd.setResearchPlan(rp);
					rp.addRndResults(rnd);
				} else {
					//rnd.setResearchPlan(rp);
					rp.addRndResults(rnd);
					researchPlanMap.put(key, rp);
				} 			
			} catch (Exception e) {
				log.warn("", e);
				String msg = String.format("第 %d 列第 %d 欄資料有問題: %s", r+1, c+1, e.getMessage());
				throw new IllegalArgumentException(msg);
			}
		}			
		
		List<ResearchPlan> researchPlanList = new ArrayList<ResearchPlan>();
		researchPlanMap.forEach((k,v) -> researchPlanList.add(v));
		researchPlanList.forEach(v -> log.debug(v));
		return researchPlanList;
	}

}
