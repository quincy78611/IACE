package iace.dao.activity;

import iace.dao.BaseIaceDao;
import iace.entity.activity.ActivityVideo;

public class ActivityVideoDao extends BaseIaceDao<ActivityVideo> implements IActivityVideoDao {

	public ActivityVideoDao() {
		super(ActivityVideo.class);
	}

}
