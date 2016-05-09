package iace.action;

import iace.entity.option.OptionOrganizationClass;
import iace.service.ServiceFactory;

public class OptionOrganizationClassAction extends BaseOptionAction<OptionOrganizationClass> {

	private static final long serialVersionUID = -7160150208890021131L;

	public OptionOrganizationClassAction() {
		super("單位類別 代碼", ServiceFactory.getOptionOrganizationClassService());
	}
}
