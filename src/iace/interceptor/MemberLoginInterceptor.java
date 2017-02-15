package iace.interceptor;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import iace.entity.member.Member;

public class MemberLoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -3809884103890574475L;
	protected static Logger log = LogManager.getLogger(AbstractInterceptor.class);

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext actionContext = arg0.getInvocationContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		Member member = (Member) sessionMap.get(SessionInterceptor.SESSION_KEY_MEMBER);
		if (member == null) {
			ActionSupport action = (ActionSupport) arg0.getAction();
			action.addActionMessage("您尚未登入!");
			return Action.LOGIN;
		} else {
			log.debug("Member login : "+member.getAccount());
			return arg0.invoke();
		}
	}

}
