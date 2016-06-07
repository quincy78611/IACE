package iace.dao.questionnaire;

import java.sql.SQLException;
import java.util.Map;

import core.util.PagedList;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.SearchConditionSet;

public interface IQuestionnaireDao {
	public void createTable(QnrTable template) throws SQLException;
	public void dropTable(QnrTable template) throws SQLException;
	public long getNextIdFromSequence(QnrTable template) throws SQLException;
	public Map<String, Object> get(QnrTable template, long id) throws SQLException;
	public void insert(QnrTable template, Map<String, Object> datas) throws SQLException;
	public PagedList<Map<String, Object>> search(SearchConditionSet conditions) throws SQLException;
}
