package iace.dao.news;

import iace.dao.BaseIaceDao;
import iace.entity.news.NewsAttach;

public class NewsAttachDao extends BaseIaceDao<NewsAttach> implements INewsAttachDao {

	public NewsAttachDao() {
		super(NewsAttach.class);
	}

}
