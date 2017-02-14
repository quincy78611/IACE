package iace.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import core.util.PagedList;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysLogSearchModel;
import iace.entity.sys.SysUser;
import iace.service.ServiceFactory;
import iace.service.sys.SysLogService;
import iace.service.sys.SysUserService;

public class SysLogAction extends BaseIaceAction {

	private static final long serialVersionUID = 444347145788274098L;

	private SysLogService sysLogService = ServiceFactory.getSysLogService();
	private SysUserService sysUserService = ServiceFactory.getSysUserService();
	
	private List<SysUser> sysUserList;
	private Map<String, String> actionNames;
	
	private SysLogSearchModel searchCondition = new SysLogSearchModel();
	private PagedList<SysLog> sysLogPagedList;
	
	private long id;
	private SysLog sysLog;
	
	public SysLogAction() {
		super.setTitle("系統LOG");
	}
	
	public String init() {
		return SUCCESS;
	}
	
	public String index() {
		try {
			this.sysLogPagedList = this.sysLogService.searchBy(this.searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			this.sysLog = this.sysLogService.get(this.id);
			if (this.sysLog == null) {
				super.addActionError("找不到資料");
				return INPUT;
			}
			
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String getActionNameMap() {
		try {
			settingActionNameMap();
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	private void settingActionNameMap() {
		List<String> actionNameList = this.sysLogService.getActionNameList(this.searchCondition.getNamespace());
		this.actionNames = new TreeMap<String, String>();
		for (String actionName:actionNameList) {
			if (SysLog.actionNames.containsKey(actionName)) {
				this.actionNames.put(actionName, SysLog.actionNames.get(actionName));
			}
		}
	}

	// =========================================================================
	
	public SysLogSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(SysLogSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysLog getSysLog() {
		return sysLog;
	}

	public void setSysLog(SysLog sysLog) {
		this.sysLog = sysLog;
	}

	public List<SysUser> getSysUserList() {
		if (sysUserList == null) {
			sysUserList = this.sysUserService.listAll();
		}
		return sysUserList;
	}

	public Map<String, String> getActionNames() {
		if (this.actionNames == null) {
			settingActionNameMap();
		}
		return actionNames;
	}

	public PagedList<SysLog> getSysLogPagedList() {
		return sysLogPagedList;
	}

}
