package iace.action;

import iace.entity.option.OptionSubject;
import iace.service.ServiceFactory;

public class OptionSubjectAction extends BaseOptionAction<OptionSubject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1765966501424209615L;

	public OptionSubjectAction() {
		super("科技部學門 代碼", ServiceFactory.getOptionSubjectService());
	}
}
