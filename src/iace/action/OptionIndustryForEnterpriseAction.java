package iace.action;

import iace.entity.option.OptionIndustryForEnterprise;
import iace.service.ServiceFactory;

public class OptionIndustryForEnterpriseAction extends BaseOptionAction<OptionIndustryForEnterprise> {

	private static final long serialVersionUID = 3468285399188684247L;

	public OptionIndustryForEnterpriseAction() {
		super("企業需求單 產業類別 (一階)領域", ServiceFactory.getOptionIndustryForEnterpriseService());
		// TODO Auto-generated constructor stub
	}

}
