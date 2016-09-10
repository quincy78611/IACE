package iace.dao.consulting;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.consulting.Consulting;
import iace.entity.consulting.ConsultingSearchModel;

public interface IConsultingDao extends IBaseIaceDao<Consulting> {

	public PagedList<Consulting> searchBy(int pageIndex, int pageSize, String name, String organization);
	
	public PagedList<Consulting> searchBy(ConsultingSearchModel model);
	
	public List<Consulting> listAll(ConsultingSearchModel model);
}
