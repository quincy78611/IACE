package iace.dao.talentedPeople;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;

public interface ITalentedPeopleDao extends IBaseIaceDao<TalentedPeople> {
	
	public PagedList<TalentedPeople> searchBy(TalentedPeopleSearchModel arg);
	
}
