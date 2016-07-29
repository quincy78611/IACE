package iace.dao.enterpriseNeed;

import iace.dao.BaseIaceDao;
import iace.entity.enterpriseNeed.EnterpriseInfo;

public class EnterpriseInfoDao extends BaseIaceDao<EnterpriseInfo> implements IEnterpriseInfoDao {

	public EnterpriseInfoDao() {
		super(EnterpriseInfo.class);
	}


}
