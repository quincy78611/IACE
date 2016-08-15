package iace.interceptor;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import iace.entity.sys.SysUser;

public class AuthInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2842338684637763143L;
	protected static Logger log = LogManager.getLogger(AbstractInterceptor.class);

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		log.debug("AuthInterceptor");
		String namespace = arg0.getProxy().getNamespace();
		String actionName = arg0.getProxy().getActionName();

		ActionContext actionContext = arg0.getInvocationContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		SysUser user = (SysUser) sessionMap.get(SessionInterceptor.SESSION_KEY_SYS_USER);
		if (user == null) {
			return Action.LOGIN;
		} else if (user.hasAuth(namespace, actionName)) {
			return arg0.invoke();
		} else {
			ActionSupport action = (ActionSupport) arg0.getAction();
			action.addActionError("沒有權限!");
			return Action.ERROR;
		}

	}

}
