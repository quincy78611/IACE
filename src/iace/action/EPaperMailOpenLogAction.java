package iace.action;

import iace.entity.ePaper.EPaperMailOpenLog;
import iace.service.ServiceFactory;
import iace.service.ePaper.EPaperMailOpenLogService;

public class EPaperMailOpenLogAction extends BaseIaceAction {
	private static final long serialVersionUID = 2794952978789122305L;
	
	private EPaperMailOpenLogService epaperMailOpenLogService = ServiceFactory.getEpaperMailOpenLogService();
	
	private long epaperId;
	private String email;
	
	public String openEpaperMail() {
		try {
			EPaperMailOpenLog entity = new EPaperMailOpenLog();
			entity.setEpaperId(this.epaperId);
			entity.setEmail(this.email);
			this.epaperMailOpenLogService.create(entity);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public long getEpaperId() {
		return epaperId;
	}

	public void setEpaperId(long epaperId) {
		this.epaperId = epaperId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
