package iace.action;

import java.util.List;

import core.util.PagedList;
import iace.entity.option.BaseOption;
import iace.entity.option.BaseOptionSearchModel;
import iace.service.option.BaseOptionService;

public class BaseOptionAction<OptionEntity extends BaseOption> extends BaseIaceAction {

	private static final long serialVersionUID = -3645672145100849569L;
	
	protected BaseOptionService<OptionEntity> optionService;
	
	protected List<OptionEntity> optionList;
	protected PagedList<OptionEntity> optionPagedList;

	protected BaseOptionSearchModel searchCondition = new BaseOptionSearchModel();
	
	protected long id;
	protected OptionEntity option;

	protected BaseOptionAction(String title, BaseOptionService<OptionEntity> optionService) {
		super.setTitle(title);
		this.optionService = optionService;
	}

	public String index() {
		try {
			this.optionList = this.optionService.listAll();
			this.optionPagedList = this.optionService.searchBy(this.searchCondition);
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
		if (super.validateNotBlankNLength(this.option.getCode(), 10, "option.code")){
			if (this.optionService.isCodeExist(this.option.getCode())) {
				this.addFieldError("option.code", "代碼已存在!");
			}
		}
		
		// name
		super.validateNotBlankNLength(this.option.getName(), 100, "option.name");
	}

	public String createSubmit() {
		String rtnStr;
		try {
			this.optionService.create(this.option);
			rtnStr = SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());			
			rtnStr = ERROR;
		}
		this.index();
		return rtnStr;
	}
	
	public String update() {
		try {
			this.option = this.optionService.get(this.id);			
			return INPUT;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			this.optionList = this.optionService.listAll();
			return ERROR;
		}
	}
	
	public void validateUpdateSubmit() {
		// name
		super.validateNotBlankNLength(this.option.getName(), 100, "option.name");
	}
	
	public String updateSubmit() {
		String rtnStr;
		try {			
			this.optionService.update(this.option);
			rtnStr = SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			rtnStr = INPUT;
		}
		this.index();
		return rtnStr;
	}
	
	public String deleteSubmit() {
		String rtnStr = SUCCESS;
		try {
			this.optionService.delete(this.id);
			rtnStr = SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			this.addActionError(e.getMessage());
			rtnStr = ERROR;
		}
		this.index();
		return rtnStr;
	}
	
	
	// =========================================================================
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OptionEntity getOption() {
		return option;
	}

	public void setOption(OptionEntity option) {
		this.option = option;
	}

	public List<OptionEntity> getOptionList() {
		return optionList;
	}

	public BaseOptionSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(BaseOptionSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<OptionEntity> getOptionPagedList() {
		return optionPagedList;
	}
	
	
	
	
	

}
