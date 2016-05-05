package iace.action;

import java.util.List;

import core.action.BaseAction;
import iace.entity.OptionIndustry;
import iace.service.OptionIndustryService;
import iace.service.ServiceFactory;

public class OptionIndustryAction extends BaseAction {

	private static final long serialVersionUID = 4664782363730088425L;

	private OptionIndustryService optionIndustryService = ServiceFactory.getOptionIndustryService();

	private List<OptionIndustry> optionIndustryList;

	private long id;
	private OptionIndustry optionIndustry;

	public String index() {
		try {
			this.optionIndustryList = this.optionIndustryService.listAll();
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}		
	}

	public String create() {
		return INPUT;
	}

	public void validateCreateSubmit() {
		// code
		if (super.validateNotBlankNLength(this.optionIndustry.getCode(), 10, "optionIndustry.code")){
			if (this.optionIndustryService.isCodeExist(this.optionIndustry.getCode())) {
				this.addFieldError("optionIndustry.code", "代碼已存在!");
			}
		}
		
		// name
		super.validateNotBlankNLength(this.optionIndustry.getName(), 100, "optionIndustry.name");
	}

	public String createSubmit() {
		try {
			this.optionIndustryService.create(this.optionIndustry);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}		
	}
	
	public String update() {
		try {
			this.optionIndustry = this.optionIndustryService.get(this.id);			
			return INPUT;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public void validateUpdateSubmit() {
		// name
		super.validateNotBlankNLength(this.optionIndustry.getName(), 100, "optionIndustry.name");
	}
	
	public String updateSubmit() {
		try {			
			this.optionIndustryService.update(this.optionIndustry);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String delete() {
		try {
			this.optionIndustry = this.optionIndustryService.get(this.id);			
			return INPUT;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return ERROR;
		}
	}
	
	public String deleteSubmit() {
		try {			
			this.optionIndustryService.delete(this.optionIndustry);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	//==========================================================================

	public List<OptionIndustry> getOptionIndustryList() {
		return optionIndustryList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OptionIndustry getOptionIndustry() {
		return optionIndustry;
	}

	public void setOptionIndustry(OptionIndustry optionIndustry) {
		this.optionIndustry = optionIndustry;
	}

}
