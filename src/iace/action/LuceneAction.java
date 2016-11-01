package iace.action;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import iace.service.ServiceFactory;
import iace.service.lucene.IndexInitService;

public class LuceneAction extends BaseIaceAction {

	private static final long serialVersionUID = 7669450808497278799L;

	private IndexInitService indexInitService = ServiceFactory.getIndexInitService();
	
	public LuceneAction() {
		super.setTitle("Lucene全文檢索");
	}

	public String init() {
		String sysRoleName = super.getCurrentSysUser().getSysRole().getName();
		if (StringUtils.equals(sysRoleName, "系統開發人員") == false) {
			super.addActionError("沒有權限");
			return INPUT;
		}	
		
		return SUCCESS;
	}
	
	public String rebuildIndex() {
		String sysRoleName = super.getCurrentSysUser().getSysRole().getName();
		if (StringUtils.equals(sysRoleName, "系統開發人員") == false) {
			super.addActionError("沒有權限");
			return INPUT;
		}
		
		try {
			Date d1 = new Date();
			this.indexInitService.rebuildIndex();
			Date d2 = new Date();
			long spendSec = (d2.getTime()-d1.getTime())/1000;
			this.addActionMessage("索引重建完成!用時"+spendSec+"秒.");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
}
