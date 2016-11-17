package iace.service.about;

import core.util.PagedList;
import iace.dao.about.IAboutDao;
import iace.entity.about.About;
import iace.entity.about.AboutSearchModel;
import iace.service.BaseIaceService;

public class AboutService extends BaseIaceService<About> {

	private IAboutDao aboutDao;
	
	public AboutService(IAboutDao dao) {
		super(dao);
		this.aboutDao = dao;
	}

	public PagedList<About> searchBy(AboutSearchModel arg) {
		return this.aboutDao.searchBy(arg);
	}
}
