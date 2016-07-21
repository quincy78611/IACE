package iace.action;

import iace.entity.option.School;
import iace.service.ServiceFactory;

public class SchoolAction extends BaseOptionAction<School> {

	private static final long serialVersionUID = 3938291958134335193L;

	public SchoolAction() {
		super("學校 代碼", ServiceFactory.getSchoolService());
	}

}
