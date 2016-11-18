package iace.dao.sys;

import iace.dao.IBaseIaceDao;
import iace.entity.sys.SysUser;

public interface ISysUserDao extends IBaseIaceDao<SysUser> {

	public SysUser getBy(String account, String password);
	
	public boolean isExist(String account, String password);
	
	public boolean isAccountExist(String account);
}
