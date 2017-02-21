package iace.dao.httpRequestLog;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

import iace.dao.BaseIaceDao;
import iace.entity.httpRequestLog.HttpRequestLog;

public class HttpRequestLogDao extends BaseIaceDao<HttpRequestLog> implements IHttpRequestLogDao {

	public HttpRequestLogDao() {
		super(HttpRequestLog.class);
	}

	@Override
	protected List<Order> getDefaultOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("createTime"));
		return orderList;
	}
}
