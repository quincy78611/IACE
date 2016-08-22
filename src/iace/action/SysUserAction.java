package iace.action;

import java.util.List;

import org.hibernate.exception.JDBCConnectionException;

import iace.entity.sys.SysRole;
import iace.entity.sys.SysUser;
import iace.interceptor.SessionInterceptor;
import iace.service.ServiceFactory;
import iace.service.sys.SysRoleService;
import iace.service.sys.SysUserService;

public class SysUserAction extends BaseIaceAction {
	private static final long serialVersionUID = 8080624976522044142L;

	private SysUserService sysUserService = ServiceFactory.getSysUserService();
	private SysRoleService sysRoleService = ServiceFactory.getSysRoleService();

	private List<SysUser> sysUserList;

	private long id;
	private SysUser sysUser;

	private List<SysRole> sysRoleList;

	public SysUserAction() {
		super.setTitle("系統使用者");
	}

	public String init() {
		return index();
	}

	public String index() {
		try {
			this.sysUserList = this.sysUserService.listAll();
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String create() {
		try {
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String createSubmit() {
		try {
			SysRole role = this.sysRoleService.get(this.sysUser.getSysRole().getId());
			this.sysUser.setSysRole(role);
			this.sysUserService.create(this.sysUser);
			return index();
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.sysUser = this.sysUserService.get(this.id);
			if (this.sysUser == null) {
				super.addActionError("找不到選擇的資料紀錄!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String updateSubmit() {
		try{
			SysRole role = this.sysRoleService.get(this.sysUser.getSysRole().getId());
			this.sysUser.setSysRole(role);
			this.sysUserService.update(this.sysUser);
			this.addActionMessage("UPDATE SUCCESS!");
			return index();
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String login() {
		return SUCCESS;
	}

	public String loginSubmit() {
		try {
			if (this.sysUserService.loginCheck(this.sysUser.getAccount(), this.sysUser.getPassword())) {
				this.sysUser = this.sysUserService.getBy(this.sysUser.getAccount(), this.sysUser.getPassword());
//				ActionContext actionContext = ActionContext.getContext();
//				actionContext.getSession().put(LoginInterceptor.SESSION_KEY_SYS_USER, this.sysUser);
				super.session.put(SessionInterceptor.SESSION_KEY_SYS_USER, this.sysUser);
				
				return SUCCESS;
			} else {
				super.addActionError("帳號或密碼錯誤!");
				return INPUT;
			}
		} catch (JDBCConnectionException e) {
			log.error("資料庫連線錯誤，請重新嘗試!", e);
			super.addActionError("資料庫連線錯誤，請重新嘗試!");
			return INPUT;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public String logout() {
		try {
			this.session.clear();
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;			
		}
	}

	public List<SysUser> getSysUserList() {
		return sysUserList;
	}

	public void setSysUserList(List<SysUser> sysUserList) {
		this.sysUserList = sysUserList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public List<SysRole> getSysRoleList() {
		if (sysRoleList == null) {
			sysRoleList = this.sysRoleService.listAll();
		}
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
