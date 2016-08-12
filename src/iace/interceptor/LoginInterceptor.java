package iace.interceptor;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import iace.entity.sys.SysUser;

public class LoginInterceptor extends AbstractInterceptor {
	public static final String SESSION_KEY_SYS_USER = "sysUser";

	private static final long serialVersionUID = 3572155150298091288L;
	protected static Logger log = LogManager.getLogger(AbstractInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		log.debug("LoginInterceptor");
		
		ActionContext actionContext = arg0.getInvocationContext(); 
		Map<String, Object> sessionMap = actionContext.getSession();
		SysUser user = (SysUser) sessionMap.get(SESSION_KEY_SYS_USER);
		if (user == null) {
			return Action.LOGIN;
		} else {
			return arg0.invoke();
		}
	}

}
