package iace.dao.customerService;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.customerService.ContactUs;
import iace.entity.customerService.ContactUsSearchModel;

public interface IContactUsDao extends IBaseIaceDao<ContactUs> {
	public PagedList<ContactUs> searchBy(ContactUsSearchModel arg);
	
	public long queryTotalRecordsCount(ContactUsSearchModel arg);
}
