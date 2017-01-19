package iace.service.talentedPeople;

import iace.dao.talentedPeople.ITalentedPeopleMainProjectDao;
import iace.entity.talentedPeople.TalentedPeopleMainProject;
import iace.service.BaseIaceService;

public class TalentedPeopleMainProjectService extends BaseIaceService<TalentedPeopleMainProject> {
	
	public TalentedPeopleMainProjectService(ITalentedPeopleMainProjectDao dao) {
		super(dao);
	}
	
	
}
