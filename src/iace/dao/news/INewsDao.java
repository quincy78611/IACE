package iace.dao.news;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.news.News;
import iace.entity.news.NewsSearchModel;

public interface INewsDao extends IBaseIaceDao<News> {
	
	public PagedList<News> searchBy(NewsSearchModel arg);
	
	public long queryTotalRecordsCount(NewsSearchModel arg);
}
