package iace.dao.option;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;
import iace.entity.option.OptionSchool;
import iace.entity.qnrCooperateWay.QnrCooperateWay;

public class OptionSchoolDao extends BaseOptionDao<OptionSchool> implements IOptionSchoolDao {

	public OptionSchoolDao() {
		super(OptionSchool.class);
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<OptionSchool> listUnfillQnrCooperateWay() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "SELECT s "
					+ "FROM " + OptionSchool.class.getSimpleName() + " s "
					+ "WHERE s.isValid = :isValid AND s.id not in "
					+ "( SELECT q.school.id "
					+ "	 FROM " + QnrCooperateWay.class.getSimpleName() + " q "
					+ "  WHERE q.isValid = :isValid"
					+ ")"
					+ "ORDER BY s.code ";
			Query query = session.createQuery(hql);
			query.setString("isValid", BaseEntity.TRUE);
			
			List<OptionSchool> res = query.list();
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
