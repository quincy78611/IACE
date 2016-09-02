package iace.dao.incubationCenter;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.incubationCenter.IncubationCenter;
import iace.entity.incubationCenter.IncubationCenterSearchModel;

public interface IIncubationCenterDao extends IBaseIaceDao<IncubationCenter> {

	public PagedList<IncubationCenter> searchBy(IncubationCenterSearchModel arg);
}
