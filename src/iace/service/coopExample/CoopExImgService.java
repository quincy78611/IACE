package iace.service.coopExample;

import iace.dao.IBaseIaceDao;
import iace.entity.coopExample.CoopExImg;
import iace.service.BaseIaceService;

public class CoopExImgService extends BaseIaceService<CoopExImg> {
	
	public CoopExImgService(IBaseIaceDao<CoopExImg> dao) {
		super(dao);
	}

}
