package iace.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import core.service.BaseService;
import core.util.PagedList;
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
	
	private String patentPictureFolder;
	
	PatentService(IPatentDao patentDao, ITechFieldDao techFieldDao, 
			IOptionDao<OptionCountry> optionCountryDao,
			IOptionDao<OptionTrl> optionTrlDao) {
		this.patentDao = patentDao;
		this.techFieldDao = techFieldDao;
		this.optionCountryDao = optionCountryDao;
		this.optionTrlDao = optionTrlDao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.patentPictureFolder = prop.getProperty("patentPicFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}
	
	public List<Patent> listAll() {
		// TODO 處理圖片的問題
		return this.patentDao.listAll();
	}
	
	public List<Patent> searchBy(String name, String appNo, String country, long techFieldId) {
		TechField techField = this.techFieldDao.get(techFieldId);
		// TODO 處理圖片的問題
		return this.patentDao.searchBy(name, appNo, country, techField);
	}
	
	public PagedList<Patent> searchBy(int pageIndex, int pageSize, String name, String appNo, String country, long techFieldId){
		TechField techField = this.techFieldDao.get(techFieldId);
		// TODO 處理圖片的問題
		return this.patentDao.searchBy(pageIndex, pageSize, name, appNo, country, techField);
	}
	
	@Override
	public Patent get(Long id) {
		// TODO 處理圖片的問題
		return this.patentDao.get(id);
	}

	@Override
	public void create(Patent entity) throws IOException {
		getOrInsertOptionCountry(entity);
		getOrInsertTechField(entity);
		setPatentImportantPicturePath(entity);
		
		// TODO 處理圖片的問題
		this.patentDao.create(entity);
	}
	
	public List<String> createAll(List<Patent> entities) {
		// entity設值
		for (Patent p : entities) {
			getOrInsertOptionCountry(p);
			getOrInsertTechField(p);
			setPatentImportantPicturePath(p);
		}
		
		List<String> errMsgs = new ArrayList<String>();
		Set<String> ukSet = this.patentDao.getUKs();		
		for (int i=0;i<entities.size();i++) {
			Patent p = entities.get(i);
			//必填欄位與資料長度檢核
			for (String msg : p.validate()) {
				errMsgs.add("第"+(i+1)+"筆:"+msg);
			}			
			
			// 檢查唯一
			String uk = String.format("%s-%d", p.getAppliactionNo(), p.getTechField().getId());
			if (ukSet.contains(uk)) {
				String errMsg = String.format("第%d筆資料已存在", i+1);
				errMsgs.add(errMsg);
			} else {
				ukSet.add(uk);
			}
		}		
		if (errMsgs.size() > 0) {
			return errMsgs;
		}

		// 儲存圖片 ---------------------------------------------------------------
		for (int i=0;i<entities.size();i++) {
			try {
				Patent p = entities.get(i);
				p.getImportantPatentPicture().save(this.patentPictureFolder, p.getImportantPicturePath());
			} catch (IOException e) {
				String errMsg = String.format("第%d列圖片儲存失敗", i+1);
				log.warn(errMsg, e);
				errMsgs.add(errMsg);
			}
		}	
		
		if (errMsgs.size() > 0) {
			return errMsgs;
		}
		//----------------------------------------------------------------------
		this.patentDao.createAll(entities);
		return errMsgs;
	}

	@Override
	public void update(Patent entity) {		
		getOrInsertOptionCountry(entity);
		getOrInsertTechField(entity);
		// TODO 處理圖片的問題
		this.patentDao.update(entity);		
	}

	@Override
	public void delete(Patent entity) {
		String imagePath = entity.getImportantPicturePath();
		this.patentDao.delete(entity.getId());
		// TODO 處理圖片的問題
		log.warn("need to implement delete important image code!");
	}
	
	private void getOrInsertOptionCountry(Patent entity) {
		if (this.optionCountryDao.isCodeExist(entity.getCountry()) == false) {
			OptionCountry oc = new OptionCountry();
			oc.setCode(entity.getCountry());
			oc.setName(entity.getCountry());
			this.optionCountryDao.create(oc);
			entity.setCountry(oc.getCode());
		}
	}
	
	private void getOrInsertTechField(Patent entity) {
		if (this.techFieldDao.isNameExist(entity.getTechField().getName())) {
			TechField tf = this.techFieldDao.getByName(entity.getTechField().getName());
			entity.setTechField(tf);
		} else {
			TechField tf = new TechField();
			tf.setName(entity.getTechField().getName());		
			this.techFieldDao.create(tf);
			entity.setTechField(tf);
		}
	}

	private void setPatentImportantPicturePath(Patent entity) {
		if (entity.getImportantPatentPicture() != null) {
			String fileName = String.format("%s-%d.%s", 
					entity.getAppliactionNo(),
					entity.getTechField().getId(),
					entity.getImportantPatentPicture().getFileExtension());
			entity.setImportantPicturePath(fileName);
		}
	}
	
	public boolean checkUK(Patent entity) {
		return this.patentDao.checkUK(entity);
	}
	

}
