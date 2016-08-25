package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionOrganizationClass;

public class OptionOrganizationClassService extends BaseOptionService<OptionOrganizationClass> {

	public OptionOrganizationClassService(IOptionDao<OptionOrganizationClass> dao) {
		super(dao, OptionOrganizationClass.class);
	}

}
