package iace.action;

import iace.entity.option.OptionHadTecSrc;
import iace.service.ServiceFactory;

public class OptionHadTecSrcAction extends BaseOptionAction<OptionHadTecSrc> {

	private static final long serialVersionUID = -4940834274203353623L;

	public OptionHadTecSrcAction() {
		super("企業已有技術來源 代碼", ServiceFactory.getOptionHadTecSrcService());
	}

}
