package iace.service.researchPlan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import iace.dao.option.IOptionGrbDomainDao;
import iace.dao.researchPlan.ITechnologyDao;
import iace.entity.option.OptionGrbDomain;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.Technology;
import iace.service.BaseIaceService;

public class TechnologyService extends BaseIaceService<Technology> {
	
	private ITechnologyDao technologyDao;
	private IOptionGrbDomainDao optionGrbDomainDao;
	
	private String grbDomainPicFolder;
	
	public TechnologyService(ITechnologyDao technologyDao, IOptionGrbDomainDao optionGrbDomainDao) {
		super(technologyDao);
		this.technologyDao = technologyDao;
		this.optionGrbDomainDao = optionGrbDomainDao;
		
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			this.grbDomainPicFolder = prop.getProperty("grbDomainPicFolder");
		} catch (IOException e) {
			log.fatal("", e);			
		}
	}
	
	public List<Technology> sampleForHomePage() {
		List<Technology> tecList = new ArrayList<Technology>();
		
		List<OptionGrbDomain> grbList = this.optionGrbDomainDao.listForResearchPlan();
		Random ran = new Random();
		for (int i=0; i<3; i++) {
			OptionGrbDomain grb = grbList.remove(ran.nextInt(grbList.size()));
			List<Long> tecIds = this.technologyDao.getAllId(grb.getId());
			long tecId = tecIds.get(ran.nextInt(tecIds.size()));
			tecList.add(this.technologyDao.get(tecId));
		}
		
		// set grb domain image
		for (Technology tec : tecList) {
			ResearchPlan rp = tec.getResearchPlan();
			for (OptionGrbDomain grb : rp.getGrbDomains()) {
				if (grb.getForResearchPlan() != null && grb.getForResearchPlan() == true ) {
					File f = new File(this.grbDomainPicFolder, grb.getCode()+".jpg");
					byte[] imgData = loadImg(f);
					rp.setDomainImg(imgData);
					break;
				}
			};
		}
		
		return tecList;
	}
	
	public List<Technology> sampleForHomePageV2() {
		return this.technologyDao.sampleForHomePage();
	}
	
	private byte[] loadImg(File f) {
		try {
			byte[] data = Files.readAllBytes(Paths.get(f.getAbsolutePath()));
			return data;
		} catch (IOException | NullPointerException e) {
			log.warn("load image fail");
			return null;
		}
	}
}
