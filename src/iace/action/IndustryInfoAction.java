package iace.action;

import java.util.List;

import core.util.PagedList;
import iace.entity.industryInfo.IndustryInfo;
import iace.entity.industryInfo.IndustryInfoSearchModel;
import iace.entity.option.BaseOption;
import iace.service.ServiceFactory;
import iace.service.industryInfo.IndustryInfoService;

public class IndustryInfoAction extends BaseIaceAction {

	private static final long serialVersionUID = 6797243583037760865L;

	private IndustryInfoService industryInfoService = ServiceFactory.getIndustryInfoService();
	
	private IndustryInfoSearchModel searchCondition = new IndustryInfoSearchModel();
	private PagedList<IndustryInfo> industryInfoPagedList;
	
	private long id;
	private IndustryInfo industryInfo;
	
	private List<BaseOption> categoryList = IndustryInfo.getCategoryList();
	
	public IndustryInfoAction() {
		super.setTitle("產業情報");
	}

	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.industryInfoPagedList = this.industryInfoService.searchBy(this.searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	//==========================================================================
	
	public IndustryInfoSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(IndustryInfoSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IndustryInfo getIndustryInfo() {
		return industryInfo;
	}

	public void setIndustryInfo(IndustryInfo industryInfo) {
		this.industryInfo = industryInfo;
	}

	public PagedList<IndustryInfo> getIndustryInfoPagedList() {
		return industryInfoPagedList;
	}

	public List<BaseOption> getCategoryList() {
		return categoryList;
	}
	
	
}
