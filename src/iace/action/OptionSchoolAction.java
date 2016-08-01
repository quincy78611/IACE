package iace.action;

import iace.entity.option.OptionSchool;
import iace.service.ServiceFactory;

public class OptionSchoolAction extends BaseOptionAction<OptionSchool> {

	private static final long serialVersionUID = 3938291958134335193L;

	public OptionSchoolAction() {
		super("學校 代碼", ServiceFactory.getSchoolService());
	}

}
