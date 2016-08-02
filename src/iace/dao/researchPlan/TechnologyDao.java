package iace.dao.researchPlan;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.researchPlan.Technology;

public class TechnologyDao extends BaseIaceDao<Technology> implements ITechnologyDao {

	public TechnologyDao() {
		super(Technology.class);
	}

	@Override
	public Technology get(long id) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Technology.class);
			criteria.add(Restrictions.eq("id", id));
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			Technology t = (Technology) criteria.uniqueResult();
			
			//這裡這麼做是因為Technology.optionTrlList 設定為FetchType.LAZY
			//靠存取該欄位才會實際從資料庫載入
			t.getOptionTrlCodesString();
			
			return t;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	

}
