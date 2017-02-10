package iace.service.coopExample;

import iace.dao.coopExample.ICoopExVideoDao;
import iace.entity.coopExample.CoopExVideo;
import iace.service.BaseIaceService;

public class CoopExVideoService extends BaseIaceService<CoopExVideo> {
	
	public CoopExVideoService(ICoopExVideoDao dao) {
		super(dao);
	}

}
