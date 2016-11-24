package iace.dao.activity;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.activity.Activity;
import iace.entity.activity.ActivitySearchModel;

public interface IActivityDao extends IBaseIaceDao<Activity> {
	public PagedList<Activity> searchBy(ActivitySearchModel arg);
	
	public long queryTotalRecordsCount(ActivitySearchModel arg);
}
