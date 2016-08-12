package iace.service;

import iace.dao.IBaseIaceDao;
import iace.entity.sys.SysFunction;

public class SysFunctionService extends BaseIaceService<SysFunction> {

	SysFunctionService(IBaseIaceDao<SysFunction> dao) {
		super(dao);
	}

}
