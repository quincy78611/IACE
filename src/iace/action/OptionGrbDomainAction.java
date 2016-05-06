package iace.action;

import iace.entity.option.OptionGrbDomain;
import iace.service.ServiceFactory;

public class OptionGrbDomainAction extends BaseOptionAction<OptionGrbDomain> {

	private static final long serialVersionUID = -2988474940241823912L;

	public OptionGrbDomainAction() {
		super("GRB領域別 代碼", ServiceFactory.getOptionGrbDomainService());
		// TODO Auto-generated constructor stub
	}

}
