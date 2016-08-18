package iace.action;

import java.util.List;

import iace.entity.coopExample.CoopEx;
import iace.service.ServiceFactory;
import iace.service.coopExample.CoopExService;

public class CoopExAction extends BaseIaceAction {

	private static final long serialVersionUID = -1853598489369057497L;
	
	private CoopExService coopExService = ServiceFactory.getCoopExService();
	
	private List<CoopEx> coopExList;
	
	private long id; 
	private CoopEx coopEx;
	

	public CoopExAction() {
		super.setTitle("產學合作案例");
	}

	public String index() {
		try {
			this.coopExList = this.coopExService.listAll();
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		super.validateNotBlankNLength(this.coopEx.getProjName(), 1000, "coopEx.projName");
	}
	
	public String createSubmit() {
		try {
			this.coopExService.create(this.coopEx);
			super.addActionMessage("CREATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public List<CoopEx> getCoopExList() {
		return coopExList;
	}

	public void setCoopExList(List<CoopEx> coopExList) {
		this.coopExList = coopExList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CoopEx getCoopEx() {
		return coopEx;
	}

	public void setCoopEx(CoopEx coopEx) {
		this.coopEx = coopEx;
	}
	
	
}
