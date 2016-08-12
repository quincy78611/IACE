package iace.dao.sys;

import iace.dao.BaseIaceDao;
import iace.entity.sys.SysFunction;

public class SysFunctionDao extends BaseIaceDao<SysFunction> implements ISysFunctionDao {

	public SysFunctionDao() {
		super(SysFunction.class);
	}

}
