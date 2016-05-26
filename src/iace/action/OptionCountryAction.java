package iace.action;

import iace.entity.option.OptionCountry;
import iace.service.ServiceFactory;

public class OptionCountryAction extends BaseOptionAction<OptionCountry> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2721532543075491233L;

	public OptionCountryAction() {
		super("專利國別 代碼", ServiceFactory.getOptionCountryService());
	}

}
