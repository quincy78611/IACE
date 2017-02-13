package iace.interceptor;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import iace.action.BaseIaceAction;
import iace.entity.sys.SysLog;
import iace.entity.sys.SysUser;
import iace.service.ServiceFactory;
import iace.service.sys.SysLogService;

public class SysActionLogInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -3792595290720190628L;
	protected static Logger log = LogManager.getLogger(AbstractInterceptor.class);
	
	private SysLogService sysLogService = ServiceFactory.getSysLogService();

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {

		if (arg0.getAction() instanceof BaseIaceAction) {
			try {
				return arg0.invoke();
			} catch (Exception e) {
				throw e;
			} finally {
				BaseIaceAction action = (BaseIaceAction) arg0.getAction();
				SysLog sysLog = action.getSysLog();
				if (sysLog.isEnableLog()) {
					sysLog.setNamespace(arg0.getProxy().getNamespace());
					sysLog.setActionName(arg0.getProxy().getActionName());
					sysLog.setIp(ServletActionContext.getRequest().getRemoteAddr());
					ActionContext actionContext = arg0.getInvocationContext();
					Map<String, Object> sessionMap = actionContext.getSession();
					if (sysLog.getSysUser() == null) {
						SysUser sysUser = (SysUser) sessionMap.get(SessionInterceptor.SESSION_KEY_SYS_USER);
						sysLog.setSysUser(sysUser);
					}

					this.sysLogService.create(sysLog);
				}
			}
		} else {
			return arg0.invoke();
		}
	}

}
