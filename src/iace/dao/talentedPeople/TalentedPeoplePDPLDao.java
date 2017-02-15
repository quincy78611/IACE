package iace.dao.talentedPeople;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
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

	@Override
	public long queryCount(Boolean agreePDPL) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(super.entityClass);
			if (agreePDPL == null) {
				criteria.add(Restrictions.isNull("agreePDPL"));
			} else {
				criteria.add(Restrictions.eq("agreePDPL", agreePDPL));
			}
			criteria.setProjection(Projections.countDistinct("id"));

			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	
}
