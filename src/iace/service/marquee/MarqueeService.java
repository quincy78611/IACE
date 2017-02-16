package iace.service.marquee;

import iace.dao.marquee.IMarqueeDao;
import iace.entity.marquee.Marquee;
import iace.service.BaseIaceService;

public class MarqueeService extends BaseIaceService<Marquee> {

	public MarqueeService(IMarqueeDao dao) {
		super(dao);
	}

}
