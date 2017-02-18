package iace.dao.customerService;

import iace.dao.BaseIaceDao;
import iace.entity.customerService.ContactUsManager;

public class ContactUsManagerDao extends BaseIaceDao<ContactUsManager> implements IContactUsManagerDao {

	public ContactUsManagerDao() {
		super(ContactUsManager.class);
	}

}
