package iace.action;

import java.io.ByteArrayInputStream;

import core.util.Captcha;
import iace.interceptor.SessionInterceptor;

public class CaptchaAction extends BaseIaceAction {

	private static final long serialVersionUID = -2680029040967965443L;

	private ByteArrayInputStream imageStream;
	
	public CaptchaAction() {
	}

	public String generateCaptchaImage() {
		try {
			String captchaCode = Captcha.generateSecurityCode();
			this.imageStream = Captcha.generateImageAsInputStream(captchaCode);
			session.put(SessionInterceptor.SESSION_KEY_CAPTCHA_CODE, captchaCode);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
	
}
