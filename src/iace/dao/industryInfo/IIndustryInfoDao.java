package iace.dao.industryInfo;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.industryInfo.IndustryInfo;
import iace.entity.industryInfo.IndustryInfoSearchModel;

public interface IIndustryInfoDao extends IBaseIaceDao<IndustryInfo> {
	public PagedList<IndustryInfo> searchBy(IndustryInfoSearchModel arg);
	
	public long queryTotalRecordsCount(IndustryInfoSearchModel arg);
}
