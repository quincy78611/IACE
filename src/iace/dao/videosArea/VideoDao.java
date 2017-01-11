package iace.dao.videosArea;

import iace.dao.BaseIaceDao;
import iace.entity.videosArea.Video;

public class VideoDao extends BaseIaceDao<Video> implements IVideoDao {

	public VideoDao() {
		super(Video.class);
	}

}
