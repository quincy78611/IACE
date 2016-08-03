package iace.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import core.service.BaseService;
import core.util.PagedList;
import iace.dao.option.IOptionDao;
import iace.dao.patent.IPatentDao;
import iace.dao.techField.ITechFieldDao;
import iace.entity.option.OptionCountry;
import iace.entity.patent.Patent;
import iace.entity.patent.PatentSearchModel;
import iace.entity.patent.TechField;

public class PatentService extends BaseService<Patent, Long> {

	private IPatentDao patentDao;
	private ITechFieldDao techFieldDao;
	private IOptionDao<OptionCountry> optionCountryDao;
	
	private String patentPictureFolder;
	
	PatentService(IPatentDao patentDao, ITechFieldDao techFieldDao, 
			IOptionDao<OptionCountry> optionCountryDao) {
		this.patentDao = patentDao;
		this.techFieldDao = techFieldDao;
		this.optionCountryDao = optionCountryDao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.patentPictureFolder = prop.getProperty("patentPicFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}

	@Deprecated
	public List<Patent> listAll() {
		List<Patent> res = this.patentDao.listAll();
		for (Patent p : res) {
			loadImportantPicToEntity(p);
		}
		return res;
	}
	
	@Deprecated
	public List<Patent> searchBy(String name, String appNo, String country, long techFieldId) {
		TechField techField = this.techFieldDao.get(techFieldId);
		List<Patent> res = this.patentDao.searchBy(name, appNo, country, techField);
		for (Patent p : res) {
			loadImportantPicToEntity(p);
		}
		return res;
	}
	
	public PagedList<Patent> searchBy(int pageIndex, int pageSize, String name, String appNo, String country, long techFieldId){
		TechField techField = this.techFieldDao.get(techFieldId);
		PagedList<Patent> res = this.patentDao.searchBy(pageIndex, pageSize, name, appNo, country, techField);
		for (Patent p : res.getList()) {
			loadImportantPicToEntity(p);
		}
		return res;
	}
	
	public PagedList<Patent> searchBy(PatentSearchModel model) {
		PagedList<Patent> res = this.patentDao.searchBy(model);
		for (Patent p : res.getList()) {
			loadImportantPicToEntity(p);
		}
		return res;
	}
	
	@Override
	public Patent get(Long id) {
		Patent p = this.patentDao.get(id);
		loadImportantPicToEntity(p);
		return p;
	}

	@Override
	public void create(Patent entity) throws IOException {
		setOptionCountryToEntity(entity);
		getOrInsertTechField(entity);
		setPatentImportantPicturePath(entity);
		try {
			savePatentPicture(entity);
		} catch (IOException e) {
			throw new IllegalArgumentException("儲存圖片失敗!");
		}
		this.patentDao.create(entity);
	}
	
	public List<String> createAll(List<Patent> entities) {
		// entity設值
		for (Patent p : entities) {
			setOptionCountryToEntity(p);
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
				savePatentPicture(entities.get(i));
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
		setOptionCountryToEntity(entity);
		getOrInsertTechField(entity);
		setPatentImportantPicturePath(entity);
		try {
			savePatentPicture(entity);
		} catch (IOException e) {
			throw new IllegalArgumentException("儲存圖片失敗!");
		}
		this.patentDao.update(entity);		
	}

	@Override
	public void delete(Patent entity) {
		String imagePath = entity.getImportantPicturePath();
		this.patentDao.delete(entity.getId());
		File f = new File(this.patentPictureFolder, imagePath);
		if (f.exists()) f.delete();		
	}	

	@Override
	public void delete(Long id) throws IOException {
		Patent entity = this.patentDao.get(id);
		delete(entity);
	}
		
	public boolean checkUK(Patent entity) {
		return this.patentDao.checkUK(entity);
	}
	
	private void setOptionCountryToEntity(Patent entity) {		
		if (this.optionCountryDao.isCodeExist(entity.getCountry().getCode())) {
			OptionCountry opt = this.optionCountryDao.getByCode(entity.getCountry().getCode());
			entity.setCountry(opt);
		}
		else {
			throw new IllegalArgumentException("國別代碼:「"+entity.getCountry().getCode()+"」不存在");
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
			entity.getImportantPatentPictureExtension());
			entity.setImportantPicturePath(fileName);
		}
	}

	
	private void savePatentPicture(Patent p) throws IOException {
		if (p.getImportantPatentPicture() != null && p.getImportantPicturePath() != null) {
			File f = new File(this.patentPictureFolder, p.getImportantPicturePath());
			byte[] img = p.getImportantPatentPicture();
			FileUtils.writeByteArrayToFile(f, img);
		}
	}	

	//將圖片從檔案load進來並且存入Entity中
	private void loadImportantPicToEntity(Patent p) {
		try {
			File f = new File(this.patentPictureFolder, p.getImportantPicturePath());
			byte[] data = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
			p.setImportantPatentPicture(data);
		} catch (IOException | NullPointerException e) {
			log.warn("load image fail");
		}
	}

}
