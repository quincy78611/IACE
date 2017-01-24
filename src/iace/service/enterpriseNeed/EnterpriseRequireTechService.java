package iace.service.enterpriseNeed;

import core.util.PagedList;
import iace.dao.enterpriseNeed.IEnterpriseRequireTechDao;
import iace.entity.enterpriseNeed.EnterpriseRequireTech;
import iace.entity.enterpriseNeed.EnterpriseRequireTechSearchModel;
import iace.service.BaseIaceService;

public class EnterpriseRequireTechService extends BaseIaceService<EnterpriseRequireTech> {

	private IEnterpriseRequireTechDao dao;
	
	public EnterpriseRequireTechService(IEnterpriseRequireTechDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public PagedList<EnterpriseRequireTech> searchBy(EnterpriseRequireTechSearchModel arg) {
		return this.dao.searchBy(arg);
	}

}
