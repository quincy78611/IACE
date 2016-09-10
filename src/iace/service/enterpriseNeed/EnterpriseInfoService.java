package iace.service.enterpriseNeed;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.util.ExcelUtil;
import core.util.PagedList;
import iace.dao.enterpriseNeed.IEnterpriseInfoDao;
import iace.dao.option.IOptionDao;
import iace.entity.enterpriseNeed.EnterpriseAcademiaCoop;
import iace.entity.enterpriseNeed.EnterpriseInfo;
import iace.entity.enterpriseNeed.EnterpriseNeedSearchModel;
import iace.entity.enterpriseNeed.EnterpriseRequireTech;
import iace.entity.enterpriseNeed.EnterpriseSituation;
import iace.entity.option.OptionCompanyLocation;
import iace.entity.option.OptionCooperateMode;
import iace.entity.option.OptionDomain;
import iace.entity.option.OptionHadTecSrc;
import iace.service.BaseIaceService;

public class EnterpriseInfoService extends BaseIaceService<EnterpriseInfo> {

	private IEnterpriseInfoDao enterpriseInfoDao;
	private IOptionDao<OptionDomain> optIndustryDao;
	private IOptionDao<OptionCompanyLocation> optCompanyLocationDao;
	private IOptionDao<OptionHadTecSrc> optHadTecSrcDao;
	private IOptionDao<OptionCooperateMode> optCooperateModeDao;
	
	public EnterpriseInfoService(IEnterpriseInfoDao dao, 
			IOptionDao<OptionDomain> optIndustryDao,
			IOptionDao<OptionCompanyLocation> optCompanyLocationDao, 
			IOptionDao<OptionHadTecSrc> optHadTecSrcDao, 
			IOptionDao<OptionCooperateMode> optCooperateModeDao) {
		super(dao);
		this.enterpriseInfoDao = dao;
		this.optIndustryDao = optIndustryDao;
		this.optCompanyLocationDao = optCompanyLocationDao;
		this.optHadTecSrcDao = optHadTecSrcDao;
		this.optCooperateModeDao = optCooperateModeDao;
	}
		
	public PagedList<EnterpriseInfo> searchBy(EnterpriseNeedSearchModel searchCondition) {
		return this.enterpriseInfoDao.searchBy(searchCondition);
	}
	
	@Override
	public void create(EnterpriseInfo entity) throws IOException, SQLException {
		setOptionIndustryList(entity);
		setOptionCompanyLocation(entity);		
		
		EnterpriseRequireTech requireTech = entity.getEnterpriseRequireTech();
		if (requireTech == null) {
			requireTech = new EnterpriseRequireTech();
			entity.setEnterpriseRequireTech(requireTech);
		}
		setPhase1(requireTech);
		requireTech.create();
		requireTech.setEnterpriseInfo(entity);

		EnterpriseSituation situation = entity.getEnterpriseSituation();
		if (situation == null) {
			situation = new EnterpriseSituation();
			entity.setEnterpriseSituation(situation);
		}
		setHadTecSrc(situation);
		setCoopMode(situation);
		situation.create();
		situation.setEnterpriseInfo(entity);
		
		EnterpriseAcademiaCoop academiaCoop = entity.getEnterpriseAcademiaCoop();
		if (academiaCoop == null) {
			academiaCoop = new EnterpriseAcademiaCoop();
			entity.setEnterpriseAcademiaCoop(academiaCoop);
		}
		academiaCoop.create();
		academiaCoop.setEnterpriseInfo(entity);
		
		super.create(entity);
	}
	
	@Override
	public void update(EnterpriseInfo entity) throws IOException, SQLException {
		setOptionIndustryList(entity);
		setOptionCompanyLocation(entity);		

		EnterpriseRequireTech requireTech = entity.getEnterpriseRequireTech();
		setPhase1(requireTech);
		requireTech.update();
		requireTech.setEnterpriseInfo(entity);

		EnterpriseSituation situation = entity.getEnterpriseSituation();
		setHadTecSrc(situation);
		setCoopMode(situation);
		situation.update();
		situation.setEnterpriseInfo(entity);
		
		EnterpriseAcademiaCoop academiaCoop = entity.getEnterpriseAcademiaCoop();
		academiaCoop.update();
		academiaCoop.setEnterpriseInfo(entity);
		
		super.update(entity);
	}
	
	private void setOptionCompanyLocation(EnterpriseInfo entity) {
		if (entity.getOptionCompanyLocation() != null) {
			long optId = entity.getOptionCompanyLocation().getId();
			OptionCompanyLocation opt = this.optCompanyLocationDao.get(optId);
			entity.setOptionCompanyLocation(opt);
		}
	}
	
	private void setOptionIndustryList(EnterpriseInfo entity) {
		if (entity.getOptionDomainList() != null) {
			List<OptionDomain> newList = new ArrayList<OptionDomain>();
			for (OptionDomain opt : entity.getOptionDomainList()) {
				newList.add(this.optIndustryDao.get(opt.getId()));
			}
			entity.setOptionDomainList(newList);
		}
	}
	
	private void setPhase1(EnterpriseRequireTech entity) {
		if (entity.getPhase1() != null) {
			long optId = entity.getPhase1().getId();
			OptionDomain opt = this.optIndustryDao.get(optId);
			entity.setPhase1(opt);
		}
	}
	
	private void setHadTecSrc(EnterpriseSituation entity) {
		if (entity.getOptionHadTecSrc() != null) {
			long optId = entity.getOptionHadTecSrc().getId();
			OptionHadTecSrc opt = this.optHadTecSrcDao.get(optId);
			entity.setOptionHadTecSrc(opt);
		}
	}
	
	private void setCoopMode(EnterpriseSituation entity) {
		if (entity.getOptionCooperateMode() != null) {
			long optId = entity.getOptionCooperateMode().getId();
			OptionCooperateMode opt = this.optCooperateModeDao.get(optId);
			entity.setOptionCooperateMode(opt);
		}
	}

	public XSSFWorkbook exportRawData(EnterpriseNeedSearchModel model) {
		List<EnterpriseInfo> list = this.enterpriseInfoDao.listAllForExport(model);
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		int r = -1;
		int c = -1;
		XSSFRow row = null;
		
		{//title row
			row = sheet.createRow(++r);
			row.createCell(++c).setCellValue("企業名稱");
			row.createCell(++c).setCellValue("統一編號");
			row.createCell(++c).setCellValue("公司目前產品/服務項目");
			row.createCell(++c).setCellValue("(一階)領域");
			row.createCell(++c).setCellValue("(二階)發展方向");
			row.createCell(++c).setCellValue("(三階)應用端");
			row.createCell(++c).setCellValue("公司地址");
			row.createCell(++c).setCellValue("負責人姓名");
			row.createCell(++c).setCellValue("負責人職稱");
			row.createCell(++c).setCellValue("負責人電話");
			row.createCell(++c).setCellValue("負責人Email");
			row.createCell(++c).setCellValue("受訪人姓名");
			row.createCell(++c).setCellValue("受訪人職稱");
			row.createCell(++c).setCellValue("受訪人電話");
			row.createCell(++c).setCellValue("受訪人Email");
			row.createCell(++c);
			row.createCell(++c).setCellValue("(一階)領域");
			row.createCell(++c).setCellValue("(二階)發展方向");
			row.createCell(++c).setCellValue("(三階)應用端");
			row.createCell(++c).setCellValue("已經探詢過的單位");
			row.createCell(++c).setCellValue("訪談日期");
			row.createCell(++c).setCellValue("記錄人");
			row.createCell(++c);
			row.createCell(++c).setCellValue("1.企業已有技術來源");
			row.createCell(++c).setCellValue("1.比例");
			row.createCell(++c).setCellValue("2-1.是否有過跟法人機構技術合作(含技轉)的經驗");
			row.createCell(++c).setCellValue("2-2.合作題目為何");
			row.createCell(++c).setCellValue("2-3.請闡述與法人機構技轉的優點");
			row.createCell(++c).setCellValue("2-3.請闡述與法人機構技轉的缺點");
			row.createCell(++c).setCellValue("3-1.是否有過跟學界技術合作的經驗");
			row.createCell(++c).setCellValue("3-2.合作題目為何");
			row.createCell(++c).setCellValue("3-3.對於跟學界合作的意願");
			row.createCell(++c).setCellValue("3-4.請闡述與學界技術合作的優點");
			row.createCell(++c).setCellValue("3-4.請闡述與學界技術合作的缺點");
			row.createCell(++c).setCellValue("3-5.與學校合作，是否有特定的合作模式");
			row.createCell(++c).setCellValue("3-6.若以新創方式合作，是否有特定主題或方向");
			row.createCell(++c).setCellValue("4.是否有其他合作對象");
			row.createCell(++c);
			row.createCell(++c).setCellValue("5-1傾向與哪些學校進行技術合作");
			row.createCell(++c).setCellValue("5-2.目前公司是否有其他的產學合作案正在進行中？主題");
			row.createCell(++c).setCellValue("5-3.手上是否已有想合作的學校對象？我們可以協助鏈結。主題");
			row.createCell(++c).setCellValue("6.其他/對於科技部「運用法人鏈結產學合作計畫」有何建議？");
		}
		
		for (EnterpriseInfo e : list) {
			row = sheet.createRow(++r);
			c = -1;
			ExcelUtil.createNSetCellValue(row, ++c, e.getName());
			ExcelUtil.createNSetCellValue(row, ++c, e.getCompanyId());
			ExcelUtil.createNSetCellValue(row, ++c, e.getMainProduct());
			ExcelUtil.createNSetCellValue(row, ++c, e.getOptionDomainSysLogString());
			ExcelUtil.createNSetCellValue(row, ++c, e.getPhase2());
			ExcelUtil.createNSetCellValue(row, ++c, e.getPhase3());
			ExcelUtil.createNSetCellValue(row, ++c, e.getAddress());
			ExcelUtil.createNSetCellValue(row, ++c, e.getPersonInChargeName());
			ExcelUtil.createNSetCellValue(row, ++c, e.getPersonInChargeJobtitle());
			ExcelUtil.createNSetCellValue(row, ++c, e.getPersonInChargePhone());
			ExcelUtil.createNSetCellValue(row, ++c, e.getPersonInChargeEmail());
			ExcelUtil.createNSetCellValue(row, ++c, e.getIntervieweeName());
			ExcelUtil.createNSetCellValue(row, ++c, e.getIntervieweeJobtitle());
			ExcelUtil.createNSetCellValue(row, ++c, e.getIntervieweePhone());
			ExcelUtil.createNSetCellValue(row, ++c, e.getIntervieweeEmail());
			row.createCell(++c);
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseRequireTech().getPhase1DomainName());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseRequireTech().getPhase2());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseRequireTech().getPhase3());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseRequireTech().getInquiredOrg());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseRequireTech().getInterviewDate());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseRequireTech().getRecordBy());
			row.createCell(++c);
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getHadTecSrc());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getHadTecSrcRation());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getHasComCoopExp());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getCoopTopic());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getCoopPros());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getCoopCons());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getHasAcademiaCoopExp());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getAcademiaTopic());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getAcademiaIntention());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getAcademiaPros());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getAcademiaCons());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getCooperateMode());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getSpecificTopic());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseSituation().getOtherCoopTarget());
			row.createCell(++c);
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseAcademiaCoop().getCoopSchool());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseAcademiaCoop().getCurrentCoopProjectTopic());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseAcademiaCoop().getWantedCoopSchoolTopic());
			ExcelUtil.createNSetCellValue(row, ++c, e.getEnterpriseAcademiaCoop().getSuggestion());
		}
		
		for (int i=0; i<=c; i++) {
			sheet.autoSizeColumn(i);
		}
		sheet.createFreezePane(0, 1);
		
		return wb;
	}
}
