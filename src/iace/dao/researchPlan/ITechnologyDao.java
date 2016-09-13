package iace.dao.researchPlan;

import java.util.List;

import iace.dao.IBaseIaceDao;
import iace.entity.researchPlan.Technology;

public interface ITechnologyDao extends IBaseIaceDao<Technology> {
	public List<Long> getAllId(Long researchPlanGrbId);
}
