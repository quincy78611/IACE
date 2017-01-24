package iace.dao.enterpriseNeed;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.enterpriseNeed.EnterpriseRequireTech;
import iace.entity.enterpriseNeed.EnterpriseRequireTechSearchModel;

public interface IEnterpriseRequireTechDao extends IBaseIaceDao<EnterpriseRequireTech> {
	public PagedList<EnterpriseRequireTech> searchBy(EnterpriseRequireTechSearchModel arg);

	public long queryTotalRecordsCount(EnterpriseRequireTechSearchModel arg);
}
