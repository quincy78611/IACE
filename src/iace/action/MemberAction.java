package iace.action;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import core.util.PagedList;
import iace.entity.member.Member;
import iace.entity.member.MemberSearchModel;
import iace.entity.option.BaseOption;
import iace.entity.option.OptionDomain;
import iace.entity.option.OptionIndustry;
import iace.interceptor.SessionInterceptor;
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

	public String memberCenter() {
		try {
			if (super.session.get(SessionInterceptor.SESSION_KEY_MEMBER) != null) {
				this.member = (Member) super.session.get(SessionInterceptor.SESSION_KEY_MEMBER);
				return SUCCESS;
			} else {
				return LOGIN;
			}
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String loginSubmit() {
		try {
			Member member = this.memberService.getBy(this.member.getAccount(), this.member.getPassword());
			if (member == null) {
				super.addActionError("帳號或密碼錯誤!");
				return INPUT;
			} else {
				this.member = member;
				super.session.put(SessionInterceptor.SESSION_KEY_MEMBER, this.member);

				return SUCCESS;
			}
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String logout() {
		try {
			this.session.clear();
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String forgetPasswordSubmit() {
		try {
			Member member = this.memberService.getByAccount(this.member.getAccount());
			if (member == null) {
				super.addActionError("帳號不存在!");
				return INPUT;
			} else {
				if (member.getName().equals(this.member.getName()) == false) {
					super.addActionError("姓名錯誤!");
					return INPUT;
				} else {
					Properties props = new Properties();
					props.load(this.getClass().getClassLoader().getResourceAsStream("configs/mail.smtp.properties"));
					javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, null); // 取得與SMTP

					MimeMessage msg = new MimeMessage(session); // 取得一Mime的Message
					msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
					// set FROM
					msg.setFrom(new InternetAddress(props.getProperty("mail.default.from"), props.getProperty("mail.default.sender"), "UTF-8"));
					// set TO
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(member.getEmail()));
					// Set Subject: header field
					msg.setSubject("IACE會員密碼");
					// Now set the actual message
					msg.setText("您的密碼為 [" + member.getPassword() + "]");

					Transport.send(msg);
					super.addActionMessage("信件已發送，請至您的信箱收取補發密碼");
					return SUCCESS;
				}
			}
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
