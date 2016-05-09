package iace.action;

import iace.entity.option.OptionOrganizationType;
import iace.service.ServiceFactory;

public class OptionOrganizationTypeAction extends BaseOptionAction<OptionOrganizationType> {

	private static final long serialVersionUID = -5259440076363990693L;

	public OptionOrganizationTypeAction() {
		super("單位類型 代碼", ServiceFactory.getOptionOrganizationTypeService());
	}

}
