package iace.dao.researchPlan;

import iace.dao.BaseIaceDao;
import iace.entity.RnDResult;

public class RnDResultDao extends BaseIaceDao<RnDResult> implements IRnDResultDao {

	public RnDResultDao() {
		super(RnDResult.class);
	}

}
