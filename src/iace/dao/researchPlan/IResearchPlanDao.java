package iace.dao.researchPlan;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.ResearchPlanSearchModel;

public interface IResearchPlanDao extends IBaseIaceDao<ResearchPlan> {
	
	public PagedList<ResearchPlan> searchBy(ResearchPlanSearchModel arg);
	
	public List<ResearchPlan> listAll(ResearchPlanSearchModel arg);
	
	public boolean planNoExist(String planNo);
	
	public ResearchPlan getByPlanNo(String planNo);
	
	public List<Integer> getYearList();

	public long queryTotalRecordsCount(ResearchPlanSearchModel arg);
	
	public List<ResearchPlan> sampleForEpaper();
}
