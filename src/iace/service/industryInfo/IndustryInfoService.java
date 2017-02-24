package iace.service.industryInfo;

import java.util.List;

import core.util.PagedList;
import iace.dao.industryInfo.IIndustryInfoDao;
import iace.entity.industryInfo.IndustryInfo;
import iace.entity.industryInfo.IndustryInfoSearchModel;
import iace.service.BaseIaceService;

public class IndustryInfoService extends BaseIaceService<IndustryInfo> {
	private IIndustryInfoDao dao;
	
	public IndustryInfoService(IIndustryInfoDao dao) {
		super(dao);
		this.dao = dao;
	}
	
	public PagedList<IndustryInfo> searchBy(IndustryInfoSearchModel arg) {
		return this.dao.searchBy(arg);
	}

	public List<IndustryInfo> sampleForHomePage(String category) {
		return this.dao.sampleForHomePage(category);
	}
	
	public List<IndustryInfo> sampleForEpaper() {
		return this.dao.sampleForEpaper();
	}
}
