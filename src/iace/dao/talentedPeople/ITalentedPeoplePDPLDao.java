package iace.dao.talentedPeople;

import iace.dao.IBaseIaceDao;
import iace.entity.talentedPeople.TalentedPeoplePDPL;

public interface ITalentedPeoplePDPLDao extends IBaseIaceDao<TalentedPeoplePDPL> {
	public TalentedPeoplePDPL getByTalentedPeopleID(long talentedPeopleID);
}
