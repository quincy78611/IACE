package iace.dao.option;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;
import iace.entity.option.OptionGrbDomain;
import iace.entity.researchPlan.ResearchPlan;

public class OptionGrbDomainDao extends BaseOptionDao<OptionGrbDomain> implements IOptionGrbDomainDao {

	public OptionGrbDomainDao() {
		super(OptionGrbDomain.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		OptionGrbDomain option = super.get(id);
		
		try {
			Session session = HibernateSessionFactory.getSession();
			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain1.code = :optionCode AND o.isValid = :isValid )";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain2.code = :optionCode AND o.isValid = :isValid )";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain3.code = :optionCode AND o.isValid = :isValid )";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain4.code = :optionCode AND o.isValid = :isValid )";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain5.code = :optionCode AND o.isValid = :isValid )";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain6.code = :optionCode AND o.isValid = :isValid )";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			
			// TODO Auto-generated method stub
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionGrbDomain> listForResearchPlan() {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("forResearchPlan", true));	
		criterionList.add(Restrictions.eq("isValid", BaseEntity.TRUE));	
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("priority"));
		orderList.add(Order.asc("code"));
		return (List<OptionGrbDomain>) super.listAll(optionEntityClass, orderList, criterionList);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OptionGrbDomain> listForTalentedPeople() {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(OptionGrbDomain.class);
			criteria.createAlias("mainDomain", "mainDomain",  JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.isNotNull("mainDomain"));
			
			criteria.addOrder(Order.asc("mainDomain.priority"));
			criteria.addOrder(Order.asc("mainDomain.code"));
			criteria.addOrder(Order.asc("priority"));
			criteria.addOrder(Order.asc("code"));
			
			List<OptionGrbDomain> entityList = criteria.list();
			return entityList;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Map<String, OptionGrbDomain> mapForTalentedPeople() {
		List<OptionGrbDomain> list = listForTalentedPeople();
		Map<String, OptionGrbDomain> map = new LinkedHashMap<String, OptionGrbDomain>();
		list.forEach(v -> map.put(v.getCode(), v));
		return map;
	}
	
	

	
}
