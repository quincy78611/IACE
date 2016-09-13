package iace.dao.researchPlan;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

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

	@Override
	public List<Long> getAllId(Long researchPlanGrbId) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(Technology.class);
			criteria.createAlias("researchPlan", "rp");
			Criterion[] rests = new Criterion[6];
			for (int i=0; i<6; i++) {
				criteria.createAlias("rp.grbDomain"+(i+1), "grbD"+(i+1),  JoinType.LEFT_OUTER_JOIN);
				rests[i] = Restrictions.eq("grbD"+(i+1)+".id", researchPlanGrbId);
			}				
			criteria.add(Restrictions.or(rests));
			criteria.setProjection( Projections.property("id") );

			@SuppressWarnings("unchecked")
			List<Long> res = criteria.list();
			return res;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	

}
