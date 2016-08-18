package iace.dao.coopExample;

import iace.dao.BaseIaceDao;
import iace.entity.coopExample.CoopEx;

public class CoopExDao extends BaseIaceDao<CoopEx> implements ICoopExDao {

	public CoopExDao() {
		super(CoopEx.class);
	}

	
}
