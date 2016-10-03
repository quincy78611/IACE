package iace.service.researchPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.ExcelUtil;
import core.util.PagedList;
import iace.dao.option.IOptionDao;
import iace.dao.researchPlan.IResearchPlanDao;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionTrl;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.ResearchPlanSearchModel;
import iace.entity.researchPlan.Technology;
import iace.service.BaseIaceService;

public class ResearchPlanService extends BaseIaceService<ResearchPlan> {

	private IResearchPlanDao researchPlanDao;
	private IOptionDao<OptionGrbDomain> optionGrbDomainDao;
	private IOptionDao<OptionTrl> optionTrlDao;
	
	public ResearchPlanService(IResearchPlanDao researchPlanDao, 
			IOptionDao<OptionGrbDomain> optionGrbDomainDao, 
			IOptionDao<OptionTrl> optionTrlDao) {
		super(researchPlanDao);
		this.researchPlanDao = researchPlanDao;
		this.optionGrbDomainDao = optionGrbDomainDao;
		this.optionTrlDao = optionTrlDao;
	}
	
	public PagedList<ResearchPlan> searchBy(ResearchPlanSearchModel arg) {
		PagedList<ResearchPlan> res = this.researchPlanDao.searchBy(arg);
		return res;
	}
	
	public List<String> createAll(List<ResearchPlan> entities) {
		List<String> errMsgs = new ArrayList<String>();
		Map<String, OptionGrbDomain> grbDomains = this.optionGrbDomainDao.mapAll();
		Map<String, OptionTrl> trls = this.optionTrlDao.mapAll();
		for (ResearchPlan rp : entities) {
			rp.getTechnologies().forEach(t -> t.create());
			try {				
				boolean planExist = this.researchPlanDao.planNoExist(rp.getPlanNo());
				if (planExist) {
					validateTechnology(rp.getTechnologies(), trls);
					ResearchPlan rpOrigin = this.researchPlanDao.getByPlanNo(rp.getPlanNo());
					rpOrigin.addTechnology(rp.getTechnologies());
					rp.getTechnologies().forEach(v -> v.setResearchPlan(rpOrigin));
					this.researchPlanDao.update(rpOrigin);
				} else {
					validateResearchPlan(rp, grbDomains, trls);
					rp.getTechnologies().forEach(v -> v.setResearchPlan(rp));
					this.researchPlanDao.create(rp);
				}				
			} catch (Exception e) {
				log.warn("", e);
				errMsgs.add(e.getMessage());
			}
		}
		return errMsgs;
	}
	
	private void validateResearchPlan(ResearchPlan rp, Map<String, OptionGrbDomain> grbDomains, Map<String, OptionTrl> trls) {
		//TODO validate some field
		
		if (rp.getGrbDomainCodes() != null) {
			for (String code : rp.getGrbDomainCodes()) {
				if (grbDomains.containsKey(code) == false) {
					String msg = "GrbDomain: ["+code+"] isn't a legal code!";
					throw new IllegalArgumentException(msg);								
				}				
			}
		}
		if (rp.getTrl() != null && trls.containsKey(rp.getTrl().getCode()) == false) {
			String msg = "trlCode: ["+rp.getTrl().getCode()+"] doesn't exit!";
			throw new IllegalArgumentException(msg);
		}
		validateTechnology(rp.getTechnologies(), trls);
	}
	
	private void validateTechnology(List<Technology> technologies, Map<String, OptionTrl> trls) {
		if (technologies != null) {
			for (Technology tech:technologies) {
				//TODO validate some field
				
				
				if (tech.getOptionTrlCodes() != null) {
					for (String code : tech.getOptionTrlCodes()) {
						if (trls.containsKey(code) == false) {
							String msg = "trlCode: ["+code+"] doesn't exit!";
							throw new IllegalArgumentException(msg);
						}
					}
				}

			}			
		}
	}

	public XSSFWorkbook exportRawData(ResearchPlanSearchModel arg) {
		List<ResearchPlan> list = this.researchPlanDao.listAll(arg);
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("年度");
			row.createCell(++c).setCellValue("計畫編號");
			row.createCell(++c).setCellValue("計畫名稱");
			row.createCell(++c).setCellValue("計畫主持人");
			row.createCell(++c).setCellValue("研究領域");
			row.createCell(++c).setCellValue("關鍵字");
			row.createCell(++c).setCellValue("計畫發展階段");
			row.createCell(++c).setCellValue("GRB計畫編號");
			row.createCell(++c).setCellValue("成果報告ID");
			row.createCell(++c).setCellValue("技術名稱");
			row.createCell(++c).setCellValue("技術簡述");
			row.createCell(++c).setCellValue("技術發展階段");
			row.createCell(++c).setCellValue("技術發展階段說明");
		}		
		
		// data part
		for (ResearchPlan rp : list) {
			if (rp.getTechnologies() == null || rp.getTechnologies().size() == 0) {
				row = sheet.createRow(++r);
				c = -1;
				ExcelUtil.createNSetCellValue(row, ++c, rp.getYear());
				ExcelUtil.createNSetCellValue(row, ++c, rp.getPlanNo());
				ExcelUtil.createNSetCellValue(row, ++c, rp.getName());
				ExcelUtil.createNSetCellValue(row, ++c, rp.getManager());
				ExcelUtil.createNSetCellValue(row, ++c, rp.grbDomainCodeString());
				ExcelUtil.createNSetCellValue(row, ++c, rp.getKeyword());
				ExcelUtil.createNSetCellValue(row, ++c, rp.getTrlCode());
				ExcelUtil.createNSetCellValue(row, ++c, rp.getProjkey());
				ExcelUtil.createNSetCellValue(row, ++c, rp.getGrb05Id());
			} else {
				for (Technology t : rp.getTechnologies()) {
					row = sheet.createRow(++r);
					c = -1;
					ExcelUtil.createNSetCellValue(row, ++c, rp.getYear());
					ExcelUtil.createNSetCellValue(row, ++c, rp.getPlanNo());
					ExcelUtil.createNSetCellValue(row, ++c, rp.getName());
					ExcelUtil.createNSetCellValue(row, ++c, rp.getManager());
					ExcelUtil.createNSetCellValue(row, ++c, rp.grbDomainCodeString());
					ExcelUtil.createNSetCellValue(row, ++c, rp.getKeyword());
					ExcelUtil.createNSetCellValue(row, ++c, rp.getTrlCode());
					ExcelUtil.createNSetCellValue(row, ++c, rp.getProjkey());
					ExcelUtil.createNSetCellValue(row, ++c, rp.getGrb05Id());
					ExcelUtil.createNSetCellValue(row, ++c, t.getName());
					ExcelUtil.createNSetCellValue(row, ++c, t.getDescriptoin());
					ExcelUtil.createNSetCellValue(row, ++c, t.getOptionTrlCodesString());
					ExcelUtil.createNSetCellValue(row, ++c, t.getTrlDesc());
				}
			}
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
	
	public List<Integer> getYearList() {
		return this.researchPlanDao.getYearList();
	}
}
