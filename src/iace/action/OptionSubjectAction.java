package iace.action;

import java.util.List;

import iace.entity.option.OptionSubject;
import iace.service.ServiceFactory;
import iace.service.option.OptionSubjectService;

public class OptionSubjectAction extends BaseOptionAction<OptionSubject> {

	private static final long serialVersionUID = -1765966501424209615L;

	private OptionSubjectService optionSubjectService = ServiceFactory.getOptionSubjectService();

	private int ajaxSearchLv;
	private String ajaxSearchParentCode;

	private String searchLv1Code;
	private String searchLv2Code;
	private String searchLv3Code;

	private List<OptionSubject> optionSubjectList;

	public OptionSubjectAction() {
		super("科技部學門 代碼", ServiceFactory.getOptionSubjectService());
	}
	
	@Override
	public String index() {
		//TODO 等確定代碼結構之後再來做
		return super.index();
	}

	public String getOptionSubjectListAjax() {
		try {
			this.optionSubjectList = this.optionSubjectService.listSpecificLv(this.ajaxSearchLv, this.ajaxSearchParentCode);
			return SUCCESS;
		} catch (Exception e) {
			log.error("", e);
			return ERROR;
		}
	}

	public int getAjaxSearchLv() {
		return ajaxSearchLv;
	}

	public void setAjaxSearchLv(int ajaxSearchLv) {
		this.ajaxSearchLv = ajaxSearchLv;
	}

	public String getAjaxSearchParentCode() {
		return ajaxSearchParentCode;
	}

	public void setAjaxSearchParentCode(String ajaxSearchParentCode) {
		this.ajaxSearchParentCode = ajaxSearchParentCode;
	}

	public String getSearchLv1Code() {
		return searchLv1Code;
	}

	public void setSearchLv1Code(String searchLv1Code) {
		this.searchLv1Code = searchLv1Code;
	}

	public String getSearchLv2Code() {
		return searchLv2Code;
	}

	public void setSearchLv2Code(String searchLv2Code) {
		this.searchLv2Code = searchLv2Code;
	}

	public String getSearchLv3Code() {
		return searchLv3Code;
	}

	public void setSearchLv3Code(String searchLv3Code) {
		this.searchLv3Code = searchLv3Code;
	}

	public List<OptionSubject> getOptionSubjectList() {
		return optionSubjectList;
	}

	public List<OptionSubject> getOptionSubjectLv1List() {
		return this.optionSubjectService.listSpecificLv(1, "");
	}

}
