package iace.service.talentedPeople;

import java.io.IOException;
import java.sql.SQLException;

import iace.dao.option.IOptionDao;
import iace.dao.talentedPeople.ITalentedPeopleRdResultDao;
import iace.entity.option.OptionCountry;
import iace.entity.talentedPeople.TalentedPeopleRdResult;
import iace.service.BaseIaceService;

public class TalentedPeopleRdResultService extends BaseIaceService<TalentedPeopleRdResult> {

	private ITalentedPeopleRdResultDao talentedPeopleRdResultDao;
	private IOptionDao<OptionCountry> optionCountryDao;
	
	public TalentedPeopleRdResultService(
			ITalentedPeopleRdResultDao talentedPeopleRdResultDao,
			IOptionDao<OptionCountry> optionCountryDao) {
		super(talentedPeopleRdResultDao);
		this.talentedPeopleRdResultDao = talentedPeopleRdResultDao;
		this.optionCountryDao = optionCountryDao;
	}

	@Override
	public void create(TalentedPeopleRdResult entity) throws IOException, SQLException {
		OptionCountry oc = this.optionCountryDao.get(entity.getOptionCountry().getId());
		entity.setOptionCountry(oc);
		this.talentedPeopleRdResultDao.create(entity);
	}
	
	@Override
	public void update(TalentedPeopleRdResult entity) throws IOException, SQLException {
		OptionCountry oc = this.optionCountryDao.get(entity.getOptionCountry().getId());
		entity.setOptionCountry(oc);
		this.talentedPeopleRdResultDao.update(entity);
	}
	
}
