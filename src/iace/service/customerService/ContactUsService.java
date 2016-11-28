package iace.service.customerService;

import core.util.PagedList;
import iace.dao.customerService.IContactUsDao;
import iace.entity.customerService.ContactUs;
import iace.entity.customerService.ContactUsSearchModel;
import iace.service.BaseIaceService;

public class ContactUsService extends BaseIaceService<ContactUs> {

	private IContactUsDao dao;
	
	public ContactUsService(IContactUsDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public PagedList<ContactUs> searchBy(ContactUsSearchModel arg) {
		return this.dao.searchBy(arg);
	}

}
