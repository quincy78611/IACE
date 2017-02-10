package iace.service.coopExample;

import iace.dao.coopExample.ICoopExAttachFileDao;
import iace.entity.coopExample.CoopExAttachFile;
import iace.service.BaseIaceService;

public class CoopExAttachFileService extends BaseIaceService<CoopExAttachFile> {
	
	public CoopExAttachFileService(ICoopExAttachFileDao dao) {
		super(dao);
	}

}
