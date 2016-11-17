package iace.dao.about;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.about.About;
import iace.entity.about.AboutSearchModel;

public interface IAboutDao extends IBaseIaceDao<About> {
	
	public PagedList<About> searchBy(AboutSearchModel arg);
	
	public long queryTotalRecordsCount(AboutSearchModel arg);
	
}
