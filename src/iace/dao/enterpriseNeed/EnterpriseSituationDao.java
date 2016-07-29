package iace.dao.enterpriseNeed;

import iace.dao.BaseIaceDao;
import iace.entity.enterpriseNeed.EnterpriseSituation;

public class EnterpriseSituationDao extends BaseIaceDao<EnterpriseSituation> implements IEnterpriseSituationDao {

	public EnterpriseSituationDao() {
		super(EnterpriseSituation.class);
	}


}
