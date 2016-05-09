package iace.action;

import iace.entity.option.OptionTrl;
import iace.service.ServiceFactory;

public class OptionTrlAction extends BaseOptionAction<OptionTrl> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3179616363076753446L;

	public OptionTrlAction() {
		super("發展階段 代碼", ServiceFactory.getOptionTrlService());
	}

}
