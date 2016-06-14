package iace.dao.questionnaire;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.questionnaire.QnrTable;

public interface IQnrTemplateDao extends IBaseIaceDao<QnrTable> {
	public PagedList<QnrTable> searchBy(int pageIndex, int pageSize, String qnrName);
	public boolean isQnrNameExist(String name);
	
	/**
	 * check is "name" exist except the record with "id"
	 * @param name
	 * @param id 
	 * @return
	 */
	public boolean isQnrNameExistExcept(String name, long id);
}
