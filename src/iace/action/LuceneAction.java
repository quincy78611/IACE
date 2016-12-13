package iace.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import core.util.PagedList;
import iace.entity.coopExample.CoopEx;
import iace.entity.incubationCenter.IncubationCenter;
import iace.entity.literature.Literature;
import iace.entity.option.BaseOption;
import iace.entity.patent.Patent;
import iace.entity.researchPlan.Technology;
import iace.entity.talentedPeople.TalentedPeople;
import iace.service.ServiceFactory;
import iace.service.lucene.LuceneIndexService;
import lucene.integrationSearch.IntegrationSearchModel;
import lucene.integrationSearch.IntegrationSearchResult;

public class LuceneAction extends BaseIaceAction {

	private static final long serialVersionUID = 7669450808497278799L;

	private LuceneIndexService luceneIndexService = ServiceFactory.getLuceneIndexService();
	
	private IntegrationSearchModel searchCondition = new IntegrationSearchModel();
	private PagedList<IntegrationSearchResult> pagedList;
	
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
			this.luceneIndexService.rebuildIndex();
			Date d2 = new Date();
			long spendSec = (d2.getTime()-d1.getTime())/1000;
			this.addActionMessage("索引重建完成!用時"+spendSec+"秒.");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String integrationSearchInit() {
		return SUCCESS;
	}
	
	public void validateIntegrationSearch() {
		super.validateNotBlank(this.searchCondition.getSearchText(), "searchCondition.searchText");
	}
	
	public String integrationSearch() {
		try {
			this.pagedList = this.luceneIndexService.integrationSearch(this.searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
		
	}

	public IntegrationSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(IntegrationSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public PagedList<IntegrationSearchResult> getPagedList() {
		return pagedList;
	}

	public void setPagedList(PagedList<IntegrationSearchResult> pagedList) {
		this.pagedList = pagedList;
	}
	
	public List<BaseOption> getClassList() {
		List<BaseOption> list = new ArrayList<BaseOption>();
		list.add(new BaseOption(Technology.class.getName(), "研發成果"));
		list.add(new BaseOption(Patent.class.getName(), "專利資料"));
		list.add(new BaseOption(TalentedPeople.class.getName(), "產學人才"));
		list.add(new BaseOption(CoopEx.class.getName(), "合作案例"));
		list.add(new BaseOption(Literature.class.getName(), "法規/文獻"));
		list.add(new BaseOption(IncubationCenter.class.getName(), "育成中心"));
		list.add(new BaseOption("OTHER", "其他"));
		return list;
	}
}
