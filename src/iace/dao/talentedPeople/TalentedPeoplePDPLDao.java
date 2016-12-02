package iace.dao.talentedPeople;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.talentedPeople.TalentedPeoplePDPL;

public class TalentedPeoplePDPLDao extends BaseIaceDao<TalentedPeoplePDPL> implements ITalentedPeoplePDPLDao {

	public TalentedPeoplePDPLDao() {
		super(TalentedPeoplePDPL.class);
	}

	@Override
	public TalentedPeoplePDPL getByTalentedPeopleID(long talentedPeopleID) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(TalentedPeoplePDPL.class);
			criteria.add(Restrictions.eq("talentedPeople.id", talentedPeopleID));
			return (TalentedPeoplePDPL) criteria.uniqueResult();
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
