package iace.action;

import iace.entity.option.OptionIndustry;
import iace.service.ServiceFactory;

public class OptionIndustryAction extends BaseOptionAction<OptionIndustry> {

	private static final long serialVersionUID = 4664782363730088425L;
	
	public OptionIndustryAction() {
		super("產業/領域別 代碼", ServiceFactory.getOptionIndustryService());
	}



}
