package iace.service.faq;

import java.util.List;

import core.util.PagedList;
import iace.dao.faq.IFaqDao;
import iace.entity.faq.Faq;
import iace.entity.faq.FaqSearchModel;
import iace.service.BaseIaceService;

public class FaqService extends BaseIaceService<Faq> {

	private IFaqDao faqDao;
	
	public FaqService(IFaqDao dao) {
		super(dao);
		this.faqDao = dao;
	}

	public PagedList<Faq> searchBy(FaqSearchModel arg) {
		return this.faqDao.searchBy(arg);
	}
	
	public List<Faq> sampleForEpaper() {
		return this.faqDao.sampleForEpaper();
	}
}
