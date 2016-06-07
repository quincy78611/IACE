package iace.dao.questionnaire;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.dao.BaseIaceDao;
import iace.entity.BaseEntity;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;

public class QnrTemplateDao extends BaseIaceDao<QnrTable> implements IQnrTemplateDao {

	public QnrTemplateDao() {
		super(QnrTable.class);
	}

	@Override
	public void create(QnrTable entity) {
		long nextId = getNextId()+1;
		log.debug(nextId);
		entity.setId(nextId);
		entity.setTableName("QNR"+nextId);
		for (int i=0; i<entity.getQuestionNum(); i++) {
			QnrTableColumn qtc = entity.getQuestionList().get(i);
			qtc.setColName("Q"+(i+1));
		}
		entity.addCommonTableFields();
		entity.getQuestionList().forEach(v -> v.create());
		entity.getQuestionList().forEach(v -> v.setQnrTemplate(entity));
		entity.create();
		super.create(entity);
	}
	
	@Override
	public void update(QnrTable entity) {
		String msg = "Don't call this method any more, "
				+ "please call delete method first and then can create method!";
		throw new IllegalArgumentException(msg);
	}

	
	private long getNextId() {
		try {
			Session session = HibernateSessionFactory.getSession();
			String sql = "select SEQUENCE_QUESTIONNAIRE_ID.nextval as num from dual";
		    Query query = session.createSQLQuery(sql)
		    		.addScalar("num", StandardBasicTypes.LONG );
			return ((Long) query.uniqueResult()).longValue();
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}		
	}

	@Override
	public PagedList<QnrTable> searchBy(int pageIndex, int pageSize, String qnrName) {
		long totalItemCount = queryTotalRecordCount(qnrName);
		PagedList<QnrTable> results = new PagedList<QnrTable>(totalItemCount, pageSize, pageIndex);
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(QnrTable.class);
			
			/**
			 * the field "questionList" default FetchMode setting in Entity
			 * is EAGER. So if doesn't set FetchMode to something else, it will 
			 * use default FetchMode which is EAGER here.
			 * Under this circumstances, the real SQL will be 
			 * "QNR_TABLE left outer join QNR_TABLE_COL",
			 * and it will return same QnrTable in many records depends on 
			 * how many QnrTableColumn it have.
			 * 
			 * On the contrary, if we specific set FetchMode to SELECT here,
			 * than it will select QnrTable and QnrTableColumn separated.
			 * and then put the QnrTableColumn into QnrTable.questionList.
			 * the size of result List<QnrTable> will be same with the number of QnrTable 
			 */
			criteria.setFetchMode("questionList", FetchMode.SELECT);
			
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			if (StringUtils.isNotBlank(qnrName)) {
				criteria.add(Restrictions.like("name", qnrName, MatchMode.ANYWHERE).ignoreCase());
			}
			
			criteria.addOrder(Order.asc("id"));
			criteria.setFirstResult(results.getItemStart()-1);
			criteria.setMaxResults(pageSize);
			
			@SuppressWarnings("unchecked")
			List<QnrTable> list = criteria.list();
			results.setList(list);
			return results;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	private long queryTotalRecordCount(String name) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Criteria criteria = session.createCriteria(QnrTable.class);
			criteria.setFetchMode("questionList", FetchMode.SELECT);
			criteria.add(Restrictions.eq("isValid", BaseEntity.TRUE));
			if (StringUtils.isNotBlank(name)) {
				criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE).ignoreCase());
			}

			criteria.setProjection(Projections.rowCount());
			Object count = criteria.uniqueResult();
			return (long) count;
		} catch (Exception e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	
}
