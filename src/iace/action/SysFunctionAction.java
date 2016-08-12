package iace.action;	

import java.util.List;

import iace.entity.sys.SysFunction;
import iace.service.ServiceFactory;
import iace.service.SysFunctionService;

public class SysFunctionAction extends BaseIaceAction {

	private static final long serialVersionUID = 3156967541347923716L;

	private SysFunctionService sysFunctionService = ServiceFactory.getSysFunctionService();
	
	private List<SysFunction> sysFunctionList;
	
	private long id;
	private SysFunction sysFunction;
	
	public SysFunctionAction() {
		super.setTitle("系統功能");
	}
	
	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.sysFunctionList = this.sysFunctionService.listAll();
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public String createSubmit() {
		try {
			this.sysFunctionService.create(this.sysFunction);
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
			this.sysFunction = this.sysFunctionService.get(this.id);
			if (this.sysFunction == null) {
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
		try {
			this.sysFunctionService.update(this.sysFunction);			
			this.addActionMessage("UPDATE SUCCESS!");
			return index();
		} catch (Exception e) {
			log.error("", e);
			super.addActionError(e.getMessage());
			return ERROR;
		}
	}

	public List<SysFunction> getSysFunctionList() {
		return sysFunctionList;
	}

	public void setSysFunctionList(List<SysFunction> sysFunctionList) {
		this.sysFunctionList = sysFunctionList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SysFunction getSysFunction() {
		return sysFunction;
	}

	public void setSysFunction(SysFunction sysFunction) {
		this.sysFunction = sysFunction;
	}
	
	

}
