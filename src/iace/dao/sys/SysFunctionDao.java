package iace.dao.sys;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.sys.SysFunction;

public class SysFunctionDao extends BaseIaceDao<SysFunction> implements ISysFunctionDao {

	public SysFunctionDao() {
		super(SysFunction.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysFunction> listAll() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("isValid", BaseEntity.TRUE));	
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("namespace"));
		orderList.add(Order.asc("id"));
		return (List<SysFunction>) super.listAll(SysFunction.class, orderList, criterionList);
	}

	
}
