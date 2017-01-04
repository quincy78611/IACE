package iace.action;

import org.apache.commons.lang3.StringUtils;

import core.util.PagedList;
import iace.entity.customerService.ContactUs;
import iace.entity.customerService.ContactUsSearchModel;
import iace.interceptor.SessionInterceptor;
import iace.service.ServiceFactory;
import iace.service.customerService.ContactUsService;

public class ContactUsAction extends BaseIaceAction {
	private static final long serialVersionUID = 8144103319193703720L;

	private ContactUsService contactUsService = ServiceFactory.getContactUsService();
	
	private ContactUsSearchModel searchCondition = new ContactUsSearchModel();
	private PagedList<ContactUs> contactUsPagedList;
	
	private long id;
	private ContactUs contactUs;
	private boolean beenHandled;
	
	private String captchaCode;
	
	public ContactUsAction() {
		super.setTitle("客服信箱");
	}

	public String init() {
		return index();
	}
	
	public String index() {
		try {
			this.contactUsPagedList = this.contactUsService.searchBy(this.searchCondition);
			return SUCCESS;			
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String showDetail() {
		try {
			this.contactUs = this.contactUsService.get(id);
			if (this.contactUs == null) {
				super.addActionError("沒有對應的資料");
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
		super.validateNotBlankNLength(this.contactUs.getName(), 50, "contactUs.name");
		super.validateNotBlankNLength(this.contactUs.getPhone(), 30, "contactUs.phone");
		if (super.validateNotBlank(this.contactUs.getEmail(), "contactUs.email")) {
			super.validateEmail(this.contactUs.getEmail(), "contactUs.email");
		}
		super.validateNotBlank(this.contactUs.getMessage(), "contactUs.message");
		
		validateCaptcha();
	}
	
	public void validateCaptcha() {
		String correctCaptchaCode = (String)session.remove(SessionInterceptor.SESSION_KEY_CAPTCHA_CODE);
		if (correctCaptchaCode != null) { //後台不需要驗證碼所以會是null，此時就不做檢查
			if (StringUtils.equalsIgnoreCase(this.captchaCode, correctCaptchaCode) == false) {
				super.addFieldError("captchaCode", "驗證碼錯誤");
			}
		}
	}
	
	public String createSubmit() {
		try {
			this.contactUsService.create(this.contactUs);
			super.addActionMessage("留言成功");
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	public String update() {
		try {
			this.contactUs = this.contactUsService.get(id);
			if (this.contactUs == null) {
				super.addActionError("沒有對應的資料");
			}
			
			this.contactUs.setBeenHandled(this.beenHandled);
			this.contactUsService.update(this.contactUs, this.getCurrentSysUser(), false, null);
			return index();
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}
	
	//==========================================================================

	public ContactUsSearchModel getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(ContactUsSearchModel searchCondition) {
		this.searchCondition = searchCondition;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ContactUs getContactUs() {
		return contactUs;
	}

	public void setContactUs(ContactUs contactUs) {
		this.contactUs = contactUs;
	}

	public boolean isBeenHandled() {
		return beenHandled;
	}

	public void setBeenHandled(boolean beenHandled) {
		this.beenHandled = beenHandled;
	}

	public PagedList<ContactUs> getContactUsPagedList() {
		return contactUsPagedList;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}
	
}
