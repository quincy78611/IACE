package iace.dao.faq;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.faq.Faq;
import iace.entity.faq.FaqSearchModel;

public interface IFaqDao extends IBaseIaceDao<Faq> {

	public PagedList<Faq> searchBy(FaqSearchModel arg);

	public long queryTotalRecordsCount(FaqSearchModel arg);
}
