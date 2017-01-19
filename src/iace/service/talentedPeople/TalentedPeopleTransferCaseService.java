package iace.service.talentedPeople;

import iace.dao.talentedPeople.ITalentedPeopleTransferCaseDao;
import iace.entity.talentedPeople.TalentedPeopleTransferCase;
import iace.service.BaseIaceService;

public class TalentedPeopleTransferCaseService extends BaseIaceService<TalentedPeopleTransferCase> {

	public TalentedPeopleTransferCaseService(ITalentedPeopleTransferCaseDao dao) {
		super(dao);
	}
}
