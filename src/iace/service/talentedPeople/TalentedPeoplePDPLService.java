package iace.service.talentedPeople;

import iace.dao.talentedPeople.ITalentedPeoplePDPLDao;
import iace.entity.talentedPeople.TalentedPeoplePDPL;
import iace.service.BaseIaceService;

public class TalentedPeoplePDPLService extends BaseIaceService<TalentedPeoplePDPL> {

	private ITalentedPeoplePDPLDao dao;
	
	public TalentedPeoplePDPLService(ITalentedPeoplePDPLDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public TalentedPeoplePDPL getByTalentedPeopleID(long talentedPeopleID) {
		return this.dao.getByTalentedPeopleID(talentedPeopleID);
	}

}
