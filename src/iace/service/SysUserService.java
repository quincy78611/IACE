package iace.service;

import iace.dao.sys.ISysUserDao;
import iace.entity.sys.SysUser;

public class SysUserService extends BaseIaceService<SysUser> {
	
	private ISysUserDao dao;
	
	protected SysUserService(ISysUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean loginCheck(String account, String password) {
		return this.dao.isExist(account, password);
	}
	
	public SysUser getBy(String account, String password) {
		return this.dao.getBy(account, password);
	}
	
}
