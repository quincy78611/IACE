package iace.service.httpRequestLog;

import iace.dao.httpRequestLog.IHttpRequestLogDao;
import iace.entity.httpRequestLog.HttpRequestLog;
import iace.service.BaseIaceService;

public class HttpRequestLogService extends BaseIaceService<HttpRequestLog> {

	public HttpRequestLogService(IHttpRequestLogDao dao) {
		super(dao);
	}

}
