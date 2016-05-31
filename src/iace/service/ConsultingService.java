package iace.service;

import core.util.PagedList;
import iace.dao.consulting.IConsultingDao;
import iace.entity.Consulting;

public class ConsultingService extends BaseIaceService<Consulting> {
	private IConsultingDao consultingDao;
	
	ConsultingService(IConsultingDao dao) {
		super(dao);
		this.consultingDao = dao;
	}
	
	public PagedList<Consulting> searchBy(int pageIndex, int pageSize, String name, String organization) {
		PagedList<Consulting> res = this.consultingDao.searchBy(pageIndex, pageSize, name, organization);
		return res;
	}
}
