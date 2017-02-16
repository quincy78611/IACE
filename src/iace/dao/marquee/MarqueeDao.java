package iace.dao.marquee;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.marquee.Marquee;

public class MarqueeDao extends BaseIaceDao<Marquee> implements IMarqueeDao {

	public MarqueeDao() {
		super(Marquee.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Marquee> listAll() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("isValid", BaseEntity.TRUE));	
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.desc("sort"));
		return (List<Marquee>) super.listAll(this.entityClass, orderList, criterionList);
	}

}
