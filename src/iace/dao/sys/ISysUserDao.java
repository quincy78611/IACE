package iace.dao.sys;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.sys.SysUser;
import iace.entity.sys.SysUserSearchModel;

public interface ISysUserDao extends IBaseIaceDao<SysUser> {

	public SysUser getBy(String account, String password);
	
	public boolean isExist(String account, String password);
	
	public boolean isAccountExist(String account);
	
	public PagedList<SysUser> searchBy(SysUserSearchModel arg);
	
	public long queryTotalRecordsCount(SysUserSearchModel arg);
}
