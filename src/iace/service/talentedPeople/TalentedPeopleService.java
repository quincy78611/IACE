package iace.service.talentedPeople;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

import core.util.PagedList;
import iace.dao.option.IOptionGrbDomainDao;
import iace.dao.talentedPeople.ITalentedPeopleDao;
import iace.entity.option.OptionGrbDomain;
import iace.entity.talentedPeople.TalentedPeople;
import iace.entity.talentedPeople.TalentedPeopleSearchModel;
import iace.service.BaseIaceService;

public class TalentedPeopleService extends BaseIaceService<TalentedPeople> {

	private ITalentedPeopleDao talentedPeopleDao;
	private IOptionGrbDomainDao optionGrbDomainDao;
	
	public TalentedPeopleService(ITalentedPeopleDao dao, IOptionGrbDomainDao optionGrbDomainDao) {
		super(dao);
		this.talentedPeopleDao = dao;
		this.optionGrbDomainDao = optionGrbDomainDao;
	}

	public PagedList<TalentedPeople> searchBy(TalentedPeopleSearchModel arg) {
		return talentedPeopleDao.searchBy(arg);
	}

	@Override
	public void create(TalentedPeople entity) throws IOException, SQLException {
		setHeadShot(entity);
		setDomain(entity);
		super.create(entity);
	}

	@Override
	public void update(TalentedPeople entity) throws IOException, SQLException {
		setHeadShot(entity);
		setDomain(entity);
		super.update(entity);
	}
	
	/**
	 * If there is new upload headShot, load it from temp file and set it's byte[] to entity
	 * @param entity
	 */
	private void setHeadShot(TalentedPeople entity) {
		try {
			if (entity.hasUploadFile()) {
				Path p = Paths.get(entity.getUploadheadShot().getAbsolutePath());
				byte[] data = Files.readAllBytes(p);
				entity.setHeadShot(data);
			}
		} catch (IOException | NullPointerException e) {
			log.warn("load image fail");
		}
	}
	
	private void setDomain(TalentedPeople entity) {
		ArrayList<OptionGrbDomain> domains = new ArrayList<OptionGrbDomain>();
		for (long id : entity.getDomainsId()) {
			OptionGrbDomain domain = this.optionGrbDomainDao.get(id);
			domains.add(domain);
		}
		entity.setDomains(domains);
	}
	
}
