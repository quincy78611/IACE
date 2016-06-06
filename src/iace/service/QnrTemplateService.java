package iace.service;

import iace.dao.IBaseIaceDao;
import iace.entity.questionnaire.QnrTable;

public class QnrTemplateService extends BaseIaceService<QnrTable> {
	private IBaseIaceDao<QnrTable> qnrTemplateDao;
	
	QnrTemplateService(IBaseIaceDao<QnrTable> dao) {
		super(dao);
		this.qnrTemplateDao = dao;
	}

}
