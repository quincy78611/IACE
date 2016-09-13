package iace.service.researchPlan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import iace.dao.option.IOptionGrbDomainDao;
import iace.dao.researchPlan.ITechnologyDao;
import iace.entity.option.OptionGrbDomain;
import iace.entity.researchPlan.Technology;
import iace.service.BaseIaceService;

public class TechnologyService extends BaseIaceService<Technology> {
	
	private ITechnologyDao technologyDao;
	private IOptionGrbDomainDao optionGrbDomainDao;
	
	public TechnologyService(ITechnologyDao technologyDao, IOptionGrbDomainDao optionGrbDomainDao) {
		super(technologyDao);
		this.technologyDao = technologyDao;
		this.optionGrbDomainDao = optionGrbDomainDao;
	}
	
	public List<Technology> sampleForHomePage(){
		List<Technology> tecList = new ArrayList<Technology>();
		
		List<OptionGrbDomain> grbList = this.optionGrbDomainDao.listForResearchPlan();
		Random ran = new Random();
		for (int i=0; i<3; i++) {
			OptionGrbDomain grb = grbList.remove(ran.nextInt(grbList.size()));
			List<Long> tecIds = this.technologyDao.getAllId(grb.getId());
			long tecId = tecIds.get(ran.nextInt(tecIds.size()));
			tecList.add(this.technologyDao.get(tecId));
		}
		
		return tecList;
	}
}
