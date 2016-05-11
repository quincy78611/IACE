package iace.service;

import java.util.List;

import core.service.BaseService;
import iace.dao.option.IOptionDao;
import iace.dao.patent.IPatentDao;
import iace.dao.techField.ITechFieldDao;
import iace.entity.Patent;
import iace.entity.TechField;
import iace.entity.option.OptionCountry;
import iace.entity.option.OptionTrl;

public class PatentService extends BaseService<Patent, Long> {

	private IPatentDao patentDao;
	private ITechFieldDao techFieldDao;
	private IOptionDao<OptionCountry> optionCountryDao;
	private IOptionDao<OptionTrl> optionTrlDao;
	
	PatentService(IPatentDao patentDao, ITechFieldDao techFieldDao, 
			IOptionDao<OptionCountry> optionCountryDao,
			IOptionDao<OptionTrl> optionTrlDao) {
		this.patentDao = patentDao;
		this.techFieldDao = techFieldDao;
		this.optionCountryDao = optionCountryDao;
		this.optionTrlDao = optionTrlDao;
	}
	
	public List<Patent> listAll() {
		return this.patentDao.listAll();
	}
	
	public List<Patent> searchBy(String name, String appNo, String country, long techFieldId) {
		TechField techField = this.techFieldDao.get(techFieldId);
		return this.patentDao.searchBy(name, appNo, country, techField);
	}
	
	@Override
	public Patent get(Long id) {
		return this.patentDao.get(id);
	}

	@Override
	public void create(Patent entity) {
		getOrInsertOptionCountry(entity);
		getOrInsertTechField(entity);
		// TODO Auto-generated method stub
		this.patentDao.create(entity);
	}

	@Override
	public void update(Patent entity) {		
		getOrInsertOptionCountry(entity);
		getOrInsertTechField(entity);
		// TODO Auto-generated method stub
		this.patentDao.update(entity);		
	}

	@Override
	public void delete(Patent entity) {
		String imagePath = entity.getImportantPicturePath();
		this.patentDao.delete(entity.getId());
		//TODO delete important image
		log.warn("need to implement delete important image code!");
	}
	
	private void getOrInsertOptionCountry(Patent entity) {
		if (this.optionCountryDao.isCodeExist(entity.getCountry()) == false) {
			OptionCountry oc = new OptionCountry();
//			oc.create();
			oc.setCode(entity.getCountry());
			oc.setName(entity.getCountry());
			this.optionCountryDao.create(oc);
		}
	}
	
	private void getOrInsertTechField(Patent entity) {
		if (this.techFieldDao.isNameExist(entity.getTechField().getName())) {
			TechField tf = this.techFieldDao.getByName(entity.getTechField().getName());
			entity.setTechField(tf);
		} else {
			TechField tf = new TechField();
//			tf.create();
			tf.setName(entity.getTechField().getName());		
			this.techFieldDao.create(tf);
			entity.setTechField(tf);
		}
	}

	public boolean checkUK(Patent entity) {
		return this.patentDao.checkUK(entity);
	}

}
