package iace.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import iace.entity.ResearchPlan;
import iace.entity.Technology;

public class ResearchPlanExcelService extends BaseExcelService {
	
	public List<ResearchPlan> excelToResearchPlans(File file) throws IOException {
		XSSFWorkbook wb = getXlsxFile(file);
		super.currentSheet = wb.getSheetAt(0);
		Map<String, ResearchPlan> researchPlanMap = new LinkedHashMap<String, ResearchPlan>();
		for (int r = 1; r <= super.currentSheet.getLastRowNum(); r++) {
			int c = -1;
			try {
				//TODO validate
				ResearchPlan rp = new ResearchPlan();
				{					
					rp.setYear((int) getCell(r, ++c).getNumericCellValue());
					rp.setPlanNo(getCell(r, ++c).getStringCellValue());
					rp.setName(getCell(r, ++c).getStringCellValue());
					rp.setManager(getCell(r, ++c).getStringCellValue());
					String grbDomains = readStringFromNumericOrStringCell(getCell(r, ++c));
					rp.setGrbDomainCodes(Arrays.asList(StringUtils.split(grbDomains, ";")));
					rp.setKeyword(getCell(r, ++c).getStringCellValue());
					String rawTrl = readStringFromNumericOrStringCell(getCell(r, ++c));
					String trlCode = rawTrl.startsWith("TRL") ? rawTrl : "TRL"+rawTrl;
					rp.setTrlCode(trlCode);
					rp.setProjkey(getCell(r, ++c).getStringCellValue());
					rp.setGrb05Id(getCell(r, ++c).getRawValue());
				}

				Technology tec = new Technology();
				{					
					tec.setName(getCell(r, ++c).getStringCellValue());
					tec.setDescriptoin(getCell(r, ++c).getStringCellValue());
					String trlCell = readStringFromNumericOrStringCell(getCell(r, ++c));				
					List<String> trlCodes = Arrays.asList(StringUtils.split(trlCell, ";"));
					for (int i=0;i<trlCodes.size();i++) {
						if (trlCodes.get(i).startsWith("TRL") == false) {
							trlCodes.set(i, "TRL"+trlCodes.get(i));
						}
					}
					tec.setOptionTrlCodes(trlCodes);
					tec.setTrlDesc(getCell(r, ++c).getStringCellValue());
				}
				
				String key = rp.getPlanNo();
				if (researchPlanMap.containsKey(key)) {
					rp = researchPlanMap.get(key);
					rp.addTechnology(tec);
				} else {
					rp.addTechnology(tec);
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
	
	private String readStringFromNumericOrStringCell(XSSFCell cell) {
		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_NUMERIC:
			return String.valueOf((int)cell.getNumericCellValue());
		case XSSFCell.CELL_TYPE_STRING:
			return String.valueOf(cell.getStringCellValue());			
		default:
			throw new IllegalArgumentException("Not Accept this type: "+cell.getCellType());
		}
	}

}
