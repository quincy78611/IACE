package iace.action;

import iace.entity.option.OptionCompanyLocation;
import iace.service.ServiceFactory;

public class OptionCompanyLocationAction extends BaseOptionAction<OptionCompanyLocation> {
	private static final long serialVersionUID = 6122716250012642837L;

	public OptionCompanyLocationAction() {
		super("公司地域別 代碼", ServiceFactory.getOptionCompanyLocationService());
	}

}
