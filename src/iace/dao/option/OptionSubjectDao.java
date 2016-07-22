package iace.dao.option;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import core.dao.HibernateSessionFactory;
import iace.entity.BaseEntity;
import iace.entity.option.OptionSubject;

public class OptionSubjectDao extends BaseOptionDao<OptionSubject> implements IOptionSubjectDao {
	
	public OptionSubjectDao() {
		super(OptionSubject.class);
	}
	
	@Override
	public boolean hasBeenUsed(OptionSubject entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBeenUsed(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OptionSubject> listSpecificLv(int lv, String parentCode) {
		try {
			Session session = HibernateSessionFactory.getSession();
			String hql = "FROM "+this.optionEntityClass.getSimpleName() + " o "
						+ "WHERE length(o.code) = :codeLength "
						+ "AND o.code like :parentCode "
						+ "AND o.isValid = :isValid";
			Query query = session.createQuery(hql);
			query.setInteger("codeLength", OptionSubject.getCodeLength(lv));
			query.setString("parentCode", parentCode+"%");
			query.setString("isValid", BaseEntity.TRUE);
			List<OptionSubject> res = query.list();
			return res;			
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	
}
