package iace.dao.marquee;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

import iace.dao.BaseIaceDao;
import iace.entity.marquee.Marquee;

public class MarqueeDao extends BaseIaceDao<Marquee> implements IMarqueeDao {

	public MarqueeDao() {
		super(Marquee.class);
	}
	
	@Override
	protected List<Order> getDefaultOrderList() {
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("sort"));
		orderList.add(Order.asc("id"));
		return orderList;
	}

}
