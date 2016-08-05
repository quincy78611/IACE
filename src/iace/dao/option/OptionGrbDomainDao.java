package iace.dao.option;

import org.hibernate.Query;
import org.hibernate.Session;

import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;
import iace.entity.option.OptionGrbDomain;
import iace.entity.researchPlan.ResearchPlan;

public class OptionGrbDomainDao extends BaseOptionDao<OptionGrbDomain> {

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
						+ "WHERE ( o.grbDomain1.code = :optionCode AND o.isValid = :isValid ";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain2.code = :optionCode AND o.isValid = :isValid ";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain3.code = :optionCode AND o.isValid = :isValid ";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain4.code = :optionCode AND o.isValid = :isValid ";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain5.code = :optionCode AND o.isValid = :isValid ";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE ( o.grbDomain6.code = :optionCode AND o.isValid = :isValid ";
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

}
