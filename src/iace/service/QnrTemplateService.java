package iace.service;

import java.io.IOException;
import java.sql.SQLException;

import core.util.PagedList;
import iace.dao.questionnaire.IQnrTemplateDao;
import iace.dao.questionnaire.IQuestionnaireDao;
import iace.entity.questionnaire.QnrTable;

public class QnrTemplateService extends BaseIaceService<QnrTable> {
	private IQnrTemplateDao qnrTemplateDao;
	private IQuestionnaireDao questionnaireDao;
	
	QnrTemplateService(IQnrTemplateDao dao, IQuestionnaireDao questionnaireDao) {
		super(dao);
		this.qnrTemplateDao = dao;
		this.questionnaireDao = questionnaireDao;
	}

	public PagedList<QnrTable> searchBy(int pageIndex, int pageSize, String qnrName) {
		return this.qnrTemplateDao.searchBy(pageIndex, pageSize, qnrName);
	}

	@Override
	public void create(QnrTable entity) throws IOException, SQLException {
		super.create(entity);
		this.questionnaireDao.createTable(entity);
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
		this.questionnaireDao.dropTable(entity);
		super.delete(id);
	}
	
	


	
}
