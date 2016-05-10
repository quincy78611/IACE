package iace.dao.techField;

import iace.dao.IBaseIaceDao;
import iace.entity.TechField;

public interface ITechFieldDao extends IBaseIaceDao<TechField> {
	public boolean isNameExist(String name);
	public TechField getByName(String name);
}
