package iace.dao.talentedPeople;

import iace.dao.BaseIaceDao;
import iace.entity.talentedPeople.TalentedPeopleRdResult;

public class TalentedPeopleRdResultDao extends BaseIaceDao<TalentedPeopleRdResult> implements ITalentedPeopleRdResultDao {

	public TalentedPeopleRdResultDao() {
		super(TalentedPeopleRdResult.class);
	}

}
