package iace.dao.patent;

import java.util.List;
import java.util.Set;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.patent.Patent;
import iace.entity.patent.PatentSearchModel;
import iace.entity.patent.TechField;

public interface IPatentDao extends IBaseIaceDao<Patent> {

	/**
	 * 檢查appliactionNo和techField合起來是否唯一
	 */
	public boolean checkUK(Patent entity);
	
	/**
	 * 
	 * @return Map<ID, UK>
	 * UK = col1,col2,....
	 */
	public Set<String> getUKs();
	
	public List<Patent> searchBy(String name, String appNo, String country, TechField techField);
	public PagedList<Patent> searchBy(int pageIndex, int pageSize, String name, String appNo, String country, TechField techField);
	public PagedList<Patent> searchBy(PatentSearchModel model);
	
	public void createAll(List<Patent> entities);
}
