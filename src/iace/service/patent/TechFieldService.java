package iace.service.patent;

import iace.dao.techField.ITechFieldDao;
import iace.entity.patent.TechField;
import iace.service.BaseIaceService;

public class TechFieldService extends BaseIaceService<TechField> {	

	public TechFieldService(ITechFieldDao techFieldDao) {
		super(techFieldDao);
	}
}
