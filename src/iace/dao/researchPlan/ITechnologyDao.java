package iace.dao.researchPlan;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.researchPlan.Technology;
import iace.entity.researchPlan.TechnologySearchModel;

public interface ITechnologyDao extends IBaseIaceDao<Technology> {
	public List<Long> getAllId(Long researchPlanGrbId);
	
	public PagedList<Technology> searchBy(TechnologySearchModel arg);
	
	public long queryTotalRecordsCount(TechnologySearchModel arg);
	
	public List<Technology> sampleForHomePage();
}
