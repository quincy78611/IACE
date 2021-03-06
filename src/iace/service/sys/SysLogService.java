package iace.service.sys;

import java.util.List;

import core.util.PagedList;
import iace.dao.sys.ISysLogDao;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysLogSearchModel;
import iace.service.BaseIaceService;

public class SysLogService extends BaseIaceService<SysLog> {

	private ISysLogDao sysLogDao;
	
	public SysLogService(ISysLogDao dao) {
		super(dao);
		this.sysLogDao = dao;
	}

	public PagedList<SysLog> searchBy(SysLogSearchModel arg) {
		return this.sysLogDao.searchBy(arg);
	}
	
	public List<String> getNamespaceList() {
		return this.sysLogDao.getNamespaceList();
	}
	
	public List<String> getActionNameList(String namespace){
		return this.sysLogDao.getActionNameList(namespace);
	}
	

}
