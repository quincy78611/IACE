package iace.service;

import iace.dao.techField.ITechFieldDao;
import iace.entity.TechField;

public class TechFieldService extends BaseIaceService<TechField> {	

	TechFieldService(ITechFieldDao techFieldDao) {
		super(techFieldDao);
	}
}
