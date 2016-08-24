package iace.action;

import iace.entity.option.OptionDomain;
import iace.service.ServiceFactory;

public class OptionDomainAction extends BaseOptionAction<OptionDomain> {

	private static final long serialVersionUID = 3468285399188684247L;

	public OptionDomainAction() {
		super("領域 代碼", ServiceFactory.getOptionDomainService());
	}

}
