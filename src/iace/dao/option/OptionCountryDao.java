package iace.dao.option;

import org.hibernate.Query;
import org.hibernate.Session;

import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;
import iace.entity.option.OptionCountry;
import iace.entity.patent.Patent;

public class OptionCountryDao extends BaseOptionDao<OptionCountry> {

	public OptionCountryDao() {
		super(OptionCountry.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		OptionCountry option = super.get(id);
		
		try {
			Session session = HibernateSessionFactory.getSession();
			
			{
				String hql = "SELECT count(*) "
						+ "FROM " + Patent.class.getSimpleName() + " o "
						+ "WHERE o.country.code = :optionCode AND o.isValid = :isValid ";
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
