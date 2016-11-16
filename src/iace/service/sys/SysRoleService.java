package iace.service.sys;

import iace.dao.sys.ISysRoleDao;
import iace.entity.sys.SysRole;
import iace.service.BaseIaceService;

public class SysRoleService extends BaseIaceService<SysRole> {
	
	public SysRoleService(ISysRoleDao dao) {
		super(dao);
	}

	@Override
	public SysRole get(Long id) {
		SysRole role = super.get(id);
		return role;
	}
}
