package iace.action;

import iace.entity.option.OptionIndustryClass;
import iace.service.ServiceFactory;

public class OptionIndustryClassAction extends BaseOptionAction<OptionIndustryClass> {

	private static final long serialVersionUID = 2221745785478589174L;

	public OptionIndustryClassAction() {
		super("產業類別 代碼", ServiceFactory.getOptionIndustryClassService());
	}

}
