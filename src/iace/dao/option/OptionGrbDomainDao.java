package iace.dao.option;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.isNotNull("mainDomain"));	
		criterionList.add(Restrictions.eq("isValid", BaseEntity.TRUE));	
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("mainDomain.priority"));
		orderList.add(Order.asc("mainDomain.code"));
		orderList.add(Order.asc("priority"));
		orderList.add(Order.asc("code"));
		return (List<OptionGrbDomain>) super.listAll(optionEntityClass, orderList, criterionList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionGrbDomain> listForTalentedPeople(long optionDomainId) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("mainDomain.id", optionDomainId));	
		criterionList.add(Restrictions.eq("isValid", BaseEntity.TRUE));	
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(Order.asc("priority"));
		orderList.add(Order.asc("code"));
		return (List<OptionGrbDomain>) super.listAll(optionEntityClass, orderList, criterionList);
	}
	
	

	
}
