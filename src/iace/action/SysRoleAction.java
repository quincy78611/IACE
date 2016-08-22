package iace.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iace.entity.sys.SysAuth;
import iace.entity.sys.SysFunction;
import iace.entity.sys.SysRole;
import iace.service.ServiceFactory;
import iace.service.sys.SysFunctionService;
import iace.service.sys.SysRoleService;

public class SysRoleAction extends BaseIaceAction {

	private static final long serialVersionUID = -2427947003851670636L;
	
	private SysRoleService sysRoleService = ServiceFactory.getSysRoleService();
	private SysFunctionService sysFunctionService = ServiceFactory.getSysFunctionService();
	
	private List<SysRole> sysRoleList;
	
	private long id;
	private SysRole sysRole;
	
	public SysRoleAction() {
		super.setTitle("系統角色");
	}

	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.sysRoleList = this.sysRoleService.listAll();
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String create() {
		try {
			List<SysFunction> sysFunctionList = this.sysFunctionService.listAll();
			this.sysRole = new SysRole();
			List<SysAuth> authList = new ArrayList<SysAuth>();			
			for (SysFunction func : sysFunctionList) {
				SysAuth auth = new SysAuth();
				auth.setSysRole(this.sysRole);
				auth.setSysFunction(func);
				auth.setEnable(false);
				authList.add(auth);
			}
			this.sysRole.setAuthList(authList);
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String createSubmit() {
		try {
			this.sysRoleService.create(this.sysRole);
			this.addActionMessage("CREATE SUCCESS!");
			return index();
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.sysRole = this.sysRoleService.get(this.id);
			if (this.sysRole == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			} 
			
			// 取得全部功能便轉換成Map
			Map<Long, SysFunction> sysFunctionMap = new HashMap<Long, SysFunction>();
			List<SysFunction> sysFunctionList = this.sysFunctionService.listAll();
			sysFunctionList.forEach(func -> sysFunctionMap.put(func.getId(), func));

			// 從Map移除已存在功能
			for (SysAuth auth : this.sysRole.getAuthList()) {
				long key = auth.getSysFunction().getId();
				if (sysFunctionMap.containsKey(key) ) {
					sysFunctionMap.remove(key);
				}
			}
			
			// 用map new出缺少的auth並加入sysRole中
			for (SysFunction func : sysFunctionMap.values()) {
				SysAuth auth = new SysAuth();
				auth.create();
				auth.setSysRole(this.sysRole);
				auth.setSysFunction(func);
				auth.setEnable(false);
				this.sysRole.getAuthList().add(auth);
			}			
			
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateSubmit() {
		try {
			this.sysRoleService.update(sysRole);
			this.addActionMessage("UPDATE SUCCESS!");
			return index();
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	
	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}
	
}
