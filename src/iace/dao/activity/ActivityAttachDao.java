package iace.dao.activity;

import iace.dao.BaseIaceDao;
import iace.entity.activity.ActivityAttach;

public class ActivityAttachDao extends BaseIaceDao<ActivityAttach> implements IActivityAttachDao {

	public ActivityAttachDao() {
		super(ActivityAttach.class);
	}

}
