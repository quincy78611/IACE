package iace.dao.researchPlan;

import iace.dao.BaseIaceDao;
import iace.entity.Technology;

public class TechnologyDao extends BaseIaceDao<Technology> implements ITechnologyDao {

	public TechnologyDao() {
		super(Technology.class);
	}

}
