package iace.dao.sys;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.sys.SysParameter;

public class SysParameterDao extends BaseIaceDao<SysParameter> implements ISysParameterDao {

	public SysParameterDao() {
		super(SysParameter.class);
	}

	@Override
	public SysParameter getByKey(String key) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(SysParameter.class);
			criteria.add(Restrictions.eq("key", key));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			SysParameter res = (SysParameter) criteria.uniqueResult();
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

}
