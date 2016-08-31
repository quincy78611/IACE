package iace.action;

import iace.entity.option.OptionSysNamespace;
import iace.service.ServiceFactory;

public class OptionSysNamespaceAction extends BaseOptionAction<OptionSysNamespace> {

	private static final long serialVersionUID = -6017205464261483663L;

	public OptionSysNamespaceAction() {
		super("系統Namespace 代碼", ServiceFactory.getOptionSysNamespaceService());
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
