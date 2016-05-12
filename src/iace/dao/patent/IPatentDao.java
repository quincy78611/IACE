package iace.dao.patent;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.Patent;
import iace.entity.TechField;

public interface IPatentDao extends IBaseIaceDao<Patent> {

	public boolean checkUK(Patent entity);
	
	public List<Patent> searchBy(String name, String appNo, String country, TechField techField);
	public PagedList<Patent> searchBy(int pageIndex, int pageSize, String name, String appNo, String country, TechField techField);
	

}
