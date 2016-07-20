package iace.dao.consulting;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.Consulting;
import iace.entity.ConsultingSearchModel;

public interface IConsultingDao extends IBaseIaceDao<Consulting> {

	public PagedList<Consulting> searchBy(int pageIndex, int pageSize, String name, String organization);
	
	public PagedList<Consulting> searchBy(ConsultingSearchModel model);
}
