package iace.action;

import iace.entity.option.OptionSysAction;
import iace.service.ServiceFactory;

public class OptionSysActionAction extends BaseOptionAction<OptionSysAction> {

	private static final long serialVersionUID = 7600372902647174685L;

	public OptionSysActionAction() {
		super("系統Action 代碼", ServiceFactory.getOptionSysActionService());
	}

	@Override
	public void validateCreateSubmit() {
		// code
		if (super.validateNotBlankNLength(this.option.getCode(), 100, "option.code")) {
			if (this.optionService.isCodeExist(this.option.getCode())) {
				this.addFieldError("option.code", "代碼已存在!");
			}
		}

		// name
		super.validateNotBlankNLength(this.option.getName(), 500, "option.name");
	}

	@Override
	public void validateUpdateSubmit() {
		super.validateNotBlankNLength(this.option.getCode(), 100, "option.code");
		super.validateNotBlankNLength(this.option.getName(), 500, "option.name");
	}
}
