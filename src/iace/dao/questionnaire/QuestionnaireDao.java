package iace.dao.questionnaire;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import core.dao.BaseDao;
import core.dao.DbConnection;
import core.dao.HibernateSessionFactory;
import core.util.PagedList;
import iace.entity.BaseEntity;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;
import iace.entity.questionnaire.SearchCondition;
import iace.entity.questionnaire.SearchConditionSet;

public class QuestionnaireDao implements IQuestionnaireDao {

	protected static Logger log = LogManager.getLogger(BaseDao.class);
	
	public static String defaultSchema = HibernateSessionFactory.getDefaultSchema();

	@Override
	public void createTable(QnrTable template) throws SQLException {
		Connection conn = null;
		Statement st = null;
		List<String> sqls = new ArrayList<String>();
		sqls.add(createTableSql(template));
		sqls.addAll(addCommentSql(template));
		sqls.addAll(setPrimaryKeySql(template));
		sqls.addAll(setNotNullSql(template));
		sqls.addAll(setUniqueSql(template));
		sqls.add(createSequenceSql(template));

		try {
			conn = DbConnection.getConnection();
			st = conn.createStatement();			
			for (String sql:sqls) {
				log.debug(sql);
				st.executeUpdate(sql);				
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(null, st, conn);
		}
	}

	private String createTableSql(QnrTable template) throws SQLException {
		List<String> fields = new ArrayList<String>();
		for (QnrTableColumn q: template.getQuestionList()) {
			switch (q.getSqlType()) {
			case java.sql.Types.DATE:
				fields.add(" \""+q.getColName()+"\" DATE ");
				break;
			case java.sql.Types.TIMESTAMP:
				fields.add(" \""+q.getColName()+"\" TIMESTAMP (6) ");
				break;
			case java.sql.Types.NUMERIC:
				String s = String.format(" \"%s\" NUMBER(%d,%d) ", 
						q.getColName(), q.getPrecision(), q.getScale());
				fields.add(s);
				break;
			case java.sql.Types.NVARCHAR:
				fields.add(" \""+q.getColName()+"\" NVARCHAR2 ("+q.getLength()+") ");
				break;
			case java.sql.Types.CLOB:
				fields.add(" \""+q.getColName()+"\" CLOB ");
				break;
			default:
				throw new IllegalArgumentException("Not supported dataType: "+q.getDataType());
			}
		}		

		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE \""+defaultSchema+"\".\""+template.getTableName()+"\" (");
		for (int i=0; i<fields.size(); i++) {
			sql.append(fields.get(i));
			if (i!=fields.size()-1) {
				sql.append(", ");
			}
		}		
		sql.append(")");		
		return sql.toString();
	}	

	private List<String> addCommentSql(QnrTable template) throws SQLException {
		List<String> sql_comments = new ArrayList<String>();
		sql_comments.add(String.format("COMMENT ON TABLE \"%s\".\"%s\" IS '%s'", 
				defaultSchema, template.getTableName(), template.getName()));		
		
		String baseCommentSql = "COMMENT ON COLUMN \"%s\".\"%s\".\"%s\" IS '%s'";		
		for (QnrTableColumn q : template.getQuestionList()) {
			if (q.getQuestion() != null)
				sql_comments.add(String.format(baseCommentSql, defaultSchema, 
						template.getTableName(), q.getColName(), q.getQuestion()));
		}
		
		return sql_comments;
	}

	private List<String> setPrimaryKeySql(QnrTable template) {
		List<String> sqls = new ArrayList<String>();
		String tableName = template.getTableName();
		for (QnrTableColumn qt : template.getQuestionList()) {
			if (qt.getIsPk()) {
				String colName = qt.getColName();
				sqls.add(String.format("CREATE UNIQUE INDEX \"%s\".\"PK_%s\" ON \"%s\".\"%s\"  (\"%s\")",
						defaultSchema, tableName, defaultSchema, tableName, colName));
				sqls.add(String.format("ALTER TABLE \"%s\".\"%s\" MODIFY (\"%s\" CONSTRAINT \"NN_%s_%s\" NOT NULL ENABLE)",
						defaultSchema, tableName, colName, tableName, colName));
				sqls.add(String.format("ALTER TABLE \"%s\".\"%s\" ADD CONSTRAINT \"PK_%s\" PRIMARY KEY (\"%s\") ENABLE", 
						defaultSchema, tableName, tableName, colName));
				break;
			}
		}
		return sqls;
	}
	
	private List<String> setNotNullSql(QnrTable template) throws SQLException {
		String baseSql = "ALTER TABLE \"%s\".\"%s\" MODIFY (\"%s\" NOT NULL ENABLE)";
		List<String> sqls = new ArrayList<String>();
		for (QnrTableColumn q : template.getQuestionList()) {
			if (q.getNullable() == false && q.getIsPk() == false)
				sqls.add(String.format(baseSql, defaultSchema, template.getTableName(), q.getColName()));
		}
		
		return sqls;
	}

	private List<String> setUniqueSql(QnrTable template) throws SQLException {
		String baseSql = "ALTER TABLE \"%s\".\"%s\" ADD CONSTRAINT \"%s_UK_%s\" UNIQUE (\"%s\") ENABLE";
		List<String> sqls = new ArrayList<String>();
		for (QnrTableColumn q : template.getQuestionList()) {
			if(q.getUnique() && q.getIsPk() == false) {
				sqls.add(String.format(baseSql, defaultSchema, template.getTableName(), 
						template.getTableName(), q.getColName(), q.getColName()));
			}
		}
		return sqls;
	}

	private String createSequenceSql(QnrTable template) throws SQLException {
		String sql = "CREATE SEQUENCE SEQUENCE_"+template.getTableName()+"_ID "
				+ "INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1";
		return sql;
	}
	
	@Override
	public void dropTable(QnrTable template) throws SQLException {
		Connection conn = null;
		Statement st = null;
		String sqlTable = "DROP TABLE \""+defaultSchema+"\".\""+template.getTableName()+"\"";
		String sqlSeq = "DROP SEQUENCE \""+defaultSchema+"\".\"SEQUENCE_"+template.getTableName()+"_ID\"";
		try {
			conn = DbConnection.getConnection();
			st = conn.createStatement();
			log.debug(sqlTable);
			st.executeUpdate(sqlTable);
			log.debug(sqlSeq);
			st.executeUpdate(sqlSeq);			
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(null, st, conn);
		}
	}
	
	@Override
	public long getNextIdFromSequence(QnrTable template) throws SQLException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select \""+defaultSchema+"\".\"SEQUENCE_"+template.getTableName()+"_ID\".nextval from dual";
		log.debug(sql);
		try {
			conn = DbConnection.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {
				long id = rs.getLong(1);
				log.debug("NEXT ID = "+ id);
				return id;
			} else {
				throw new SQLException("can't get next id");
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(rs, st, conn);
		}		
	}
	
	@Override 
	public Map<String, Object> get(QnrTable template, long id) throws SQLException {
		String sql = "SELECT * FROM \""+defaultSchema+"\".\""+template.getTableName()+"\" "
				+ "WHERE \"ID\" = ? AND \"IS_VALID\"= ?";		
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DbConnection.getConnection();
			st = conn.prepareStatement(sql);
			st.setLong(1, id);
			st.setString(2, BaseEntity.TRUE);
			rs = st.executeQuery();
			if (rs.next()) {
				Map<String, Object> res = new HashMap<String, Object>();
				for (int i=0; i<template.getQuestionList().size(); i++) {
					QnrTableColumn q = template.getQuestionList().get(i);
					Object data = getResultSetData(rs, q.getColName(), q.getJavaType());
					res.put(q.getColName(), data);
				}				
				return res;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(rs, st, conn);
		}
	}
	
	private Object getResultSetData(ResultSet rs, String colName, Class<?> type) throws SQLException  {
		Object data;
		if (type == Date.class) {
			data = rs.getDate(colName);
		} else if (type == Timestamp.class) {
			data = rs.getTimestamp(colName);
		} else if (type == String.class) {
			data = rs.getString(colName);
		} else if (type == int.class) {
			data = rs.getInt(colName);
		} else if (type == long.class) {
			data = rs.getLong(colName);
		} else if (type == float.class) {
			data = rs.getFloat(colName);
		} else if (type == double.class) {
			data = rs.getDouble(colName);
		} else {
			throw new IllegalArgumentException("Unsupported data type!");
		}
		return rs.wasNull() ? null : data;
	}

	@Override
	public void insert(QnrTable template, Map<String, Object> datas) throws SQLException {
		String sql = getInsertSqlBy(template);
		settingCommonFields(template, datas);
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DbConnection.getConnection();
			st = conn.prepareStatement(sql.toString());
			for (int i=0; i<template.getQuestionList().size(); i++) {
				QnrTableColumn q = template.getQuestionList().get(i);
				String colName = q.getColName();				
				Object data = datas.get(colName);
				setDataToPreparedStatement(st, q, i+1, data);
			}
			st .executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(null, st, conn);
		}
	}
	
	private void settingCommonFields(QnrTable template, Map<String, Object> datas) throws SQLException {
		long nextId = getNextIdFromSequence(template);
		datas.put("ID", nextId);
		datas.put("IS_VALID", BaseEntity.TRUE);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		datas.put("CREATE_TIME", now);
		datas.put("CREATE_USER", "Eric");
		datas.put("UPDATE_TIME", now);
		datas.put("UPDATE_USER", "Eric");
		datas.put("VER", now);
	}
	
	private String getInsertSqlBy(QnrTable template) {
		StringBuilder sql = new StringBuilder();
		sql.append("Insert into \""+defaultSchema+"\".\""+template.getTableName()+"\" (");		
		for (int i=0; i<template.getQuestionList().size(); i++) {
			String colName = template.getQuestionList().get(i).getColName();
			sql.append("\"").append(colName).append("\"");
			if (i < template.getQuestionList().size() - 1) {
				sql.append(", ");
			}
		}
		sql.append(") values (");
		for (int i=0; i<template.getQuestionList().size(); i++) {
			sql.append("?");
			
			if (i < template.getQuestionList().size() - 1) {
				sql.append(", ");
			}
		}	
		sql.append(")");
		log.debug(sql);
		return sql.toString();
	}

	private void setDataToPreparedStatement(PreparedStatement st, QnrTableColumn q, int index, Object data) throws SQLException {
		Class<?> dataType = q.getJavaType();
		if (data == null) {
			st.setNull(index, q.getSqlType());
		} else {
			if (dataType == String.class) {
				st.setString(index, (String)data);
			} else if (dataType == Date.class) {
				st.setDate(index, (Date)data);
			} else if (dataType == Timestamp.class) {
				st.setTimestamp(index, (Timestamp)data);
			} else if (dataType == int.class) {
				st.setInt(index, (int)data);
			} else if (dataType == long.class) {
				st.setLong(index, (long) data);
			} else if (dataType == float.class) {
				st.setFloat(index, (float) data);
			} else if (dataType == double.class) {
				st.setDouble(index, (double) data);
			} else {
				throw new IllegalArgumentException("Unsupported data type!");
			}
		}
	}
	
	@Override
	public PagedList<Map<String, Object>> search(SearchConditionSet conditions) throws SQLException {
		long totalItemCount = queryTotalRecordsCount(conditions);
		int pSize = conditions.getPageSize();
		int pIndex = conditions.getPageIndex();
		
		String tableN = conditions.getTemplate().getTableName();
		String sql = String.format("SELECT * FROM \"%s\".\"%s\" %s", 
				defaultSchema, tableN, conditions.getSqlString());
		String paginationSql = wrapSqlForPagination(sql, pSize, pIndex);
		log.debug(paginationSql);
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DbConnection.getConnection();
			st = conn.prepareStatement(paginationSql);
			for (int i=0; i< conditions.getConditions().size(); i++) {
				SearchCondition cond = conditions.getCondition(i);
				setDataToPreparedStatement(st, cond.getTableColumn(), i+1, cond.getSearchValue());
			}
			rs = st.executeQuery();
			PagedList<Map<String, Object>> pagedList = new PagedList<Map<String, Object>>(totalItemCount, pSize, pIndex);
			List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
			List<QnrTableColumn> questionList = conditions.getTemplate().getQuestionList();
			while (rs.next()) {
				Map<String, Object> dataRow = new HashMap<String, Object>();
				for (int i=0; i<questionList.size(); i++) {
					QnrTableColumn q = questionList.get(i);
					Object data = getResultSetData(rs, q.getColName(), q.getJavaType());
					dataRow.put(q.getColName(), data);
				}
				results.add(dataRow);
			}
			pagedList.setList(results);
			return pagedList;
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(rs, st, conn);
		}		
	}
	
	private long queryTotalRecordsCount(SearchConditionSet conditions) throws SQLException {
		String tableN = conditions.getTemplate().getTableName();
		String sql = String.format("SELECT COUNT(*) FROM \"%s\".\"%s\" %s", 
				defaultSchema, tableN, conditions.getSqlString());
		log.debug(sql);
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DbConnection.getConnection();
			st = conn.prepareStatement(sql);
			for (int i=0; i< conditions.getConditions().size(); i++) {
				SearchCondition cond = conditions.getCondition(i);
				setDataToPreparedStatement(st, cond.getTableColumn(), i+1, cond.getSearchValue());
			}
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getLong(1);				
			} else {
				return 0;
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			DbConnection.closeConnection(rs, st, conn);
		}
	}

	private String wrapSqlForPagination(String sql, int pageSize, int pageIndex) {
		// row number start with 1
		if (pageSize < 1) {
			throw new IllegalArgumentException("pageSize can't smaller than 1");
		} 
		if (pageIndex < 0) {
			throw new IllegalArgumentException("pageIndex can't smaller than 0");
		}
		int minRows = pageSize * pageIndex + 1 ;
		int maxRows = pageSize * pageIndex + pageSize;
		String newSql = 
				"SELECT * FROM "
				+ "( "
					+ "SELECT row_.*, ROWNUM rownum_ FROM "
					+ "( "
						+ sql
					+" ) row_ WHERE ROWNUM <= " + maxRows + " "
				+ ") WHERE rownum_ >= " + minRows;	
		return newSql;
	}

}
