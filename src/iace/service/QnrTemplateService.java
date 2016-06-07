package iace.service;

import java.io.IOException;

import core.util.PagedList;
import iace.dao.questionnaire.IQnrTemplateDao;
import iace.entity.questionnaire.QnrTable;

public class QnrTemplateService extends BaseIaceService<QnrTable> {
	private IQnrTemplateDao qnrTemplateDao;
	
	QnrTemplateService(IQnrTemplateDao dao) {
		super(dao);
		this.qnrTemplateDao = dao;
	}

	public PagedList<QnrTable> searchBy(int pageIndex, int pageSize, String qnrName) {
		return this.qnrTemplateDao.searchBy(pageIndex, pageSize, qnrName);
	}

	@Override
	public void create(QnrTable entity) throws IOException {
		super.create(entity);
		// TODO create relative QNR table
	}

	@Override
	public void update(QnrTable entity) throws IOException {
		this.delete(entity);
		this.create(entity);
	}

	@Override
	public void delete(QnrTable entity) throws IOException {
		this.delete(entity.getId());
	}

	@Override
	public void delete(Long id) throws IOException {
		super.delete(id);
		// TODO remove relative QNR table
	}
	
	


	
}
