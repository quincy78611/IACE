package iace.service.option;

import iace.dao.option.IOptionDao;
import iace.entity.option.OptionHadTecSrc;

public class OptionHadTecSrcService extends BaseOptionService<OptionHadTecSrc> {

	public OptionHadTecSrcService(IOptionDao<OptionHadTecSrc> dao) {
		super(dao, OptionHadTecSrc.class);
	}

}
