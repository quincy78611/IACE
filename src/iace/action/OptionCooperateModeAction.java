package iace.action;

import iace.entity.option.OptionCooperateMode;
import iace.service.ServiceFactory;

public class OptionCooperateModeAction extends BaseOptionAction<OptionCooperateMode> {

	private static final long serialVersionUID = 7806086005560451703L;

	public OptionCooperateModeAction() {
		super("合作模式 代碼", ServiceFactory.getOptionCooperateModeService());
	}

}
