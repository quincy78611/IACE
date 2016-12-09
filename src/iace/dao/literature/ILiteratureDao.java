package iace.dao.literature;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.literature.Literature;
import iace.entity.literature.LiteratureSearchModel;

public interface ILiteratureDao extends IBaseIaceDao<Literature> {
	
	public Literature getByOid(Long oid);
	public List<Long> listAllOid();
	public PagedList<Literature> searchBy(LiteratureSearchModel arg);
	public long queryTotalRecordsCount(LiteratureSearchModel arg);
	public List<Literature> sampleForHomePage(String category);
}
