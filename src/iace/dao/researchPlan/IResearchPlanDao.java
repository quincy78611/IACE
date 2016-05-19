package iace.dao.researchPlan;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.ResearchPlan;
import iace.entity.ResearchPlanSearchModel;

public interface IResearchPlanDao extends IBaseIaceDao<ResearchPlan> {
	public PagedList<ResearchPlan> searchBy(ResearchPlanSearchModel arg);
	
	public boolean planNoExist(String planNo);
	
	public ResearchPlan getByPlanNo(String planNo);
}
