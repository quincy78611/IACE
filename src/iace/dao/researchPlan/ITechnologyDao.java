package iace.dao.researchPlan;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.BaseSearchModel;
import iace.entity.researchPlan.Technology;

public interface ITechnologyDao extends IBaseIaceDao<Technology> {
	public List<Long> getAllId(Long researchPlanGrbId);
	
	public PagedList<Technology> searchBy(BaseSearchModel arg);
	
	public long queryTotalRecordsCount();
	
	public List<Technology> sampleForHomePage();
}
