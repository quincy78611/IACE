package iace.service.sys;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iace.dao.sys.ISysFunctionDao;
import iace.dao.sys.ISysRoleDao;
import iace.entity.sys.SysAuth;
import iace.entity.sys.SysFunction;
import iace.entity.sys.SysRole;
import iace.service.BaseIaceService;

public class SysRoleService extends BaseIaceService<SysRole> {
	
	private ISysFunctionDao sysFunctionDao;

	public SysRoleService(ISysRoleDao dao, ISysFunctionDao sysFunctionDao) {
		super(dao);
		this.sysFunctionDao = sysFunctionDao;
	}

	@Override
	public void create(SysRole entity) throws IOException, SQLException {
		List<SysFunction> sysFunctionList = this.sysFunctionDao.listAll();
		Map<Long, SysFunction> sysFunctionMap = new HashMap<Long, SysFunction>();
		sysFunctionList.forEach(v -> sysFunctionMap.put(v.getId(), v));		
		for (SysAuth auth : entity.getAuthList()) {
			auth.setSysRole(entity);
			auth.setSysFunction(sysFunctionMap.get(auth.getSysFunction().getId()));
			auth.create();
		}		
		
		super.create(entity);
	}

	@Override
	public void update(SysRole entity) throws IOException, SQLException {
		List<SysFunction> sysFunctionList = this.sysFunctionDao.listAll();
		Map<Long, SysFunction> sysFunctionMap = new HashMap<Long, SysFunction>();
		sysFunctionList.forEach(v -> sysFunctionMap.put(v.getId(), v));		
		for (SysAuth auth : entity.getAuthList()) {
			auth.setSysRole(entity);
			auth.setSysFunction(sysFunctionMap.get(auth.getSysFunction().getId()));
			auth.update();
		}
		
		super.update(entity);
	}
	
	

}
