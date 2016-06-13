package iace.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import core.util.PagedList;
import iace.dao.DaoFactory;
import iace.dao.questionnaire.IQnrDao;
import iace.dao.questionnaire.IQnrTemplateDao;
import iace.entity.option.OptionCompanyLocation;
import iace.entity.option.OptionConsult;
import iace.entity.option.OptionCooperateMode;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionForQnr;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionHadTecSrc;
import iace.entity.option.OptionIndustry;
import iace.entity.option.OptionOrganizationClass;
import iace.entity.option.OptionOrganizationType;
import iace.entity.option.OptionSubject;
import iace.entity.option.OptionTrl;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;

public class QnrTemplateService extends BaseIaceService<QnrTable> {
	private IQnrTemplateDao qnrTemplateDao;
	private IQnrDao qnrDao;
	
	QnrTemplateService(IQnrTemplateDao dao, IQnrDao questionnaireDao) {
		super(dao);
		this.qnrTemplateDao = dao;
		this.qnrDao = questionnaireDao;
	}

	public PagedList<QnrTable> searchBy(int pageIndex, int pageSize, String qnrName) {
		return this.qnrTemplateDao.searchBy(pageIndex, pageSize, qnrName);
	}

	@Override
	public QnrTable get(Long id) {
		QnrTable entity = super.get(id);
		
		// setting option list for each question
		for (QnrTableColumn q : entity.getQuestionList()) {
			if (q.getInputType().equals(QnrTableColumn.INPUT_TYPE_SELECT) || 
				q.getInputType().equals(QnrTableColumn.INPUT_TYPE_CHECKBOX) ){
				List<OptionForQnr> list = new ArrayList<OptionForQnr>();
				String[] listSplit = StringUtils.split(q.getOptionListString(), ";");
				for (String option : listSplit) {
					list.add(new OptionForQnr(option, option));
				}
				q.setOptionList(list);
			} else if (q.getInputType().equals(QnrTableColumn.INPUT_TYPE_SELECT_OPTION) || 
				q.getInputType().equals(QnrTableColumn.INPUT_TYPE_CHECKBOX_OPTION) ){
				List<OptionForQnr> list = new ArrayList<OptionForQnr>();
				if (q.getFromOption().equals("OPT_COUNTRY")) {
					List<OptionCountry> optionList = DaoFactory.getOptionCountryDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_COM_LOCATION")) {
					List<OptionCompanyLocation> optionList = DaoFactory.getOptionCompanyLocationDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("CONSULTING")) {
					List<OptionConsult> optionList = DaoFactory.getOptionConsultDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_COOPERATE_MODE")) {
					List<OptionCooperateMode> optionList = DaoFactory.getOptionCooperateModeDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_GRB_DOMAIN")) {
					List<OptionGrbDomain> optionList = DaoFactory.getOptionGrbDomainDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_HAD_TEC_SRC")) {
					List<OptionHadTecSrc> optionList = DaoFactory.getOptionHadTecSrcDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_INDUSTRY")) {
					List<OptionIndustry> optionList = DaoFactory.getOptionIndustryDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_ORG_CLASS")) {
					List<OptionOrganizationClass> optionList = DaoFactory.getOptionOrganizationClassDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_ORG_TYPE")) {
					List<OptionOrganizationType> optionList = DaoFactory.getOptionOrganizationTypeDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_TRL")) {
					List<OptionTrl> optionList = DaoFactory.getOptionTrlDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				} else if (q.getFromOption().equals("OPT_SUBJECT")) {
					List<OptionSubject> optionList = DaoFactory.getOptionSubjectDao().listAll();
					optionList.forEach(v -> list.add(new OptionForQnr(v.getCode(), v.getName())));
				}
				q.setOptionList(list);
			}
		}
		
		return entity;
	}

	@Override
	public void create(QnrTable entity) throws IOException, SQLException {
		super.create(entity);
		this.qnrDao.createTable(entity);
	}

	@Override
	public void update(QnrTable entity) throws IOException, SQLException {
		this.delete(entity);
		this.create(entity);
	}

	@Override
	public void delete(QnrTable entity) throws IOException, SQLException {
		this.delete(entity.getId());
	}

	@Override
	public void delete(Long id) throws IOException, SQLException {
		QnrTable entity = super.dao.get(id);
		this.qnrDao.dropTable(entity);
		super.delete(id);
	}
	
	


	
}
