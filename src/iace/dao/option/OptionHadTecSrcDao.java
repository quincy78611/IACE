package iace.dao.option;

import iace.entity.option.OptionHadTecSrc;

public class OptionHadTecSrcDao extends BaseOptionDao<OptionHadTecSrc> {

	public OptionHadTecSrcDao() {
		super(OptionHadTecSrc.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
