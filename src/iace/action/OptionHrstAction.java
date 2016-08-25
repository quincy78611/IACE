package iace.action;

import iace.entity.option.OptionHrst;
import iace.service.ServiceFactory;

public class OptionHrstAction extends BaseOptionAction<OptionHrst> {
	
	private static final long serialVersionUID = -584437131267596164L;

	public OptionHrstAction() {
		super("HRST專長 代碼", ServiceFactory.getOptionHrstService());
	}

}
