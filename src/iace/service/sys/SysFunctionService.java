package iace.service.sys;

import iace.dao.IBaseIaceDao;
import iace.entity.sys.SysFunction;
import iace.service.BaseIaceService;

public class SysFunctionService extends BaseIaceService<SysFunction> {

	public SysFunctionService(IBaseIaceDao<SysFunction> dao) {
		super(dao);
	}

}
