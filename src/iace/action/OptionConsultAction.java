package iace.action;

import iace.entity.option.OptionConsult;
import iace.service.ServiceFactory;

public class OptionConsultAction extends BaseOptionAction<OptionConsult> {

	private static final long serialVersionUID = -1590752292817885845L;

	public OptionConsultAction() {
		super("諮詢類型 代碼", ServiceFactory.getOptionConsultService());
		// TODO Auto-generated constructor stub
	}

}
