package iace.dao.sys;

import iace.dao.IBaseIaceDao;
import iace.entity.sys.SysParameter;

public interface ISysParameterDao extends IBaseIaceDao<SysParameter> {
	public SysParameter getByKey(String key);
}
