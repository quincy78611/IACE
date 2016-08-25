package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionCompanyLocation;

public class OptionCompanyLocationService extends BaseOptionService<OptionCompanyLocation> {

	public OptionCompanyLocationService(IOptionDao<OptionCompanyLocation> dao) {
		super(dao, OptionCompanyLocation.class);
	}

}
