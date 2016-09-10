package iace.dao.enterpriseNeed;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.enterpriseNeed.EnterpriseInfo;
import iace.entity.enterpriseNeed.EnterpriseNeedSearchModel;

public interface IEnterpriseInfoDao extends IBaseIaceDao<EnterpriseInfo> {
	public PagedList<EnterpriseInfo> searchBy(EnterpriseNeedSearchModel searchCondition);
	
	public List<EnterpriseInfo> listAllForExport(EnterpriseNeedSearchModel arg);
}
