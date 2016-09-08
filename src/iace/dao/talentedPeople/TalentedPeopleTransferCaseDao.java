package iace.dao.talentedPeople;

import iace.dao.BaseIaceDao;
import iace.entity.talentedPeople.TalentedPeopleTransferCase;

public class TalentedPeopleTransferCaseDao extends BaseIaceDao<TalentedPeopleTransferCase> implements ITalentedPeopleTransferCaseDao {

	public TalentedPeopleTransferCaseDao() {
		super(TalentedPeopleTransferCase.class);
	}

}
