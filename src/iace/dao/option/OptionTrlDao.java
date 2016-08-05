package iace.dao.option;

import org.hibernate.Query;
import org.hibernate.Session;

import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;
import iace.entity.option.OptionTrl;
import iace.entity.patent.Patent;
import iace.entity.researchPlan.ResearchPlan;
import iace.entity.researchPlan.Technology;

public class OptionTrlDao extends BaseOptionDao<OptionTrl> {

	public OptionTrlDao() {
		super(OptionTrl.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		OptionTrl option = super.get(id);
		
		try {
			Session session = HibernateSessionFactory.getSession();
			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + ResearchPlan.class.getSimpleName() + " o "
						+ "WHERE o.trl.code = :optionCode AND o.isValid = :isValid ";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}
			{
				String hql = "SELECT count(*) "
						+ "FROM " + Technology.class.getSimpleName() + " o "
						+ "JOIN o.optionTrlList ol " 
						+ "WHERE ol.code = :optionCode AND o.isValid = :isValid ";
				Query query = session.createQuery(hql);
				query.setString("optionCode", option.getCode());
				query.setString("isValid", BaseEntity.TRUE);
				Long count = (Long) query.uniqueResult();
				if (count > 0) return true;
			}			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + Patent.class.getSimpleName() + " o "
						+ "WHERE o.trl.code = :optionCode AND o.isValid = :isValid ";
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
