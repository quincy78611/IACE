package iace.dao.httpRequestLog;

import iace.dao.BaseIaceDao;
import iace.entity.httpRequestLog.HttpRequestLog;

public class HttpRequestLogDao extends BaseIaceDao<HttpRequestLog> implements IHttpRequestLogDao {

	public HttpRequestLogDao() {
		super(HttpRequestLog.class);
	}

}
