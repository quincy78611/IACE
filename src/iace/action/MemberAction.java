package iace.action;

import java.util.List;

import core.util.PagedList;
import iace.entity.member.Member;
import iace.entity.member.MemberSearchModel;
import iace.entity.option.BaseOption;
import iace.entity.option.OptionDomain;
import iace.entity.option.OptionIndustry;
import iace.service.ServiceFactory;
import iace.service.member.MemberService;

public class MemberAction extends BaseIaceAction {
	private static final long serialVersionUID = 1548716018154460586L;

	private MemberService memberService = ServiceFactory.getMemberService(); 
	
	private MemberSearchModel searchCondition = new MemberSearchModel();
	private PagedList<Member> memberPagedList;
	
	private long id;
	private Member member;
	
	private List<BaseOption> industryList;
	private List<OptionIndustry> optIndustryList;
	private List<BaseOption> jobTypeList;
	private List<OptionDomain> optDomainList;
	
	public MemberAction() {
		super.setTitle("會員");
	}

	public String init() {
		return SUCCESS;
	}
	
	public String index() {
		try {
			this.memberPagedList = this.memberService.searchBy(this.searchCondition);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			this.member = this.memberService.get(this.id);
			if (this.member == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public void validateCreateSubmit() {
		if (super.validateNotBlankNLength(this.member.getAccount(), 50, "member.account")) {
			if (this.memberService.isAccountExist(this.member.getAccount())) {
				super.addFieldError("member.account", "帳號已存在");
			}
		}
		super.validateTextLength(this.member.getPassword(), 6, 20, "member.password");
		if (super.validateNotBlank(this.member.getEmail(), "member.email")) {
			super.validateEmail(this.member.getEmail(), "member.email");
		}
		super.validateNotBlankNLength(this.member.getName(), 50, "member.name");		
	}
	
	public String createSubmit() {
		try {
			this.memberService.create(this.member);
			super.addActionMessage("CREATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.member = this.memberService.get(this.id);
			if (this.member == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public void validateUpdateSubmit() {
		super.validateTextLength(this.member.getPassword(), 6, 20, "member.password");
		if (super.validateNotBlank(this.member.getEmail(), "member.email")) {
			super.validateEmail(this.member.getEmail(), "member.email");
		}
		super.validateNotBlankNLength(this.member.getName(), 50, "member.name");		
	}
	
	public String updateSubmit() {
		try {
			this.memberService.update(this.member, super.getCurrentSysUser(), false, super.getSysLog());
			super.addActionMessage("UPDATE SUCCESS");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String delete() {
		try {
			this.member = this.memberService.get(this.id);
			if (this.member == null) {
				super.addActionError("找不到資料!");
				return INPUT;
			}
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String deleteSubmit() {
		try {
			this.memberService.delete(this.id, false, super.getSysLog());
			super.addActionMessage("DELETE SUCCESS");
			return index();
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	// =========================================================================
	
	public MemberSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(MemberSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public PagedList<Member> getMemberPagedList() {
		return memberPagedList;
	}

	public List<BaseOption> getIndustryList() {
		if (industryList == null) {
			industryList = Member.getIndustryList();
		}
		return industryList;
	}

	public List<OptionIndustry> getOptIndustryList() {
		if (optIndustryList == null) {
			optIndustryList = ServiceFactory.getOptionIndustryService().listAll();
		}
		return optIndustryList;
	}

	public List<BaseOption> getJobTypeList() {
		if (jobTypeList == null) {
			jobTypeList = Member.getJobTypeList();
		}
		return jobTypeList;
	}

	public List<OptionDomain> getOptDomainList() {
		if (optDomainList == null) {
			optDomainList = ServiceFactory.getOptionDomainService().listAll();
		}
		return optDomainList;
	}

	
	
	
	
	
	
}
