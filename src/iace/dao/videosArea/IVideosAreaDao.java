package iace.dao.videosArea;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.videosArea.VideosArea;
import iace.entity.videosArea.VideosAreaSearchModel;

public interface IVideosAreaDao extends IBaseIaceDao<VideosArea> {
	
	public PagedList<VideosArea> searchBy(VideosAreaSearchModel arg);
	public long queryTotalRecordsCount(VideosAreaSearchModel arg);
	
}
