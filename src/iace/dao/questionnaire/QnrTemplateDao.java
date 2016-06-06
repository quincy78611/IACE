package iace.dao.questionnaire;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import core.dao.HibernateSessionFactory;
import iace.dao.BaseIaceDao;
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
	
}
