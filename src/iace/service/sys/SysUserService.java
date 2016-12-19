package iace.service.sys;

import core.util.PagedList;
import iace.dao.sys.ISysUserDao;
import iace.entity.sys.SysUser;
import iace.entity.sys.SysUserSearchModel;
import iace.service.BaseIaceService;

public class SysUserService extends BaseIaceService<SysUser> {
	
	private ISysUserDao dao;
	
	public SysUserService(ISysUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean loginCheck(String account, String password) {
		return this.dao.isExist(account, password);
	}
	
	public SysUser getBy(String account, String password) {
		return this.dao.getBy(account, password);
	}
	
	public PagedList<SysUser> searchBy(SysUserSearchModel arg) {
		return this.dao.searchBy(arg);
	}
}
