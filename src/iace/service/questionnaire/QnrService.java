package iace.service.questionnaire;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import core.service.BaseService;
import core.util.PagedList;
import iace.dao.questionnaire.IQnrDao;
import iace.entity.questionnaire.QnrSearchConditionSet;
import iace.entity.questionnaire.QnrTable;
import iace.entity.questionnaire.QnrTableColumn;

public class QnrService {
	protected static Logger log = LogManager.getLogger(BaseService.class);
	
	private IQnrDao qnrDao;
	
	public QnrService(IQnrDao qnrDao) {
		this.qnrDao = qnrDao;
	}
	
	public Map<String, Object> get(QnrTable template, long id) throws SQLException {
		Map<String, Object> datas = this.qnrDao.get(template, id);
		for (QnrTableColumn q : template.getQuestionList()) {
			if (q.getInputType().equals(QnrTableColumn.INPUT_TYPE_CHECKBOX) ||
				q.getInputType().equals(QnrTableColumn.INPUT_TYPE_CHECKBOX_OPTION)	) {
				String optionsString = (String)datas.get(q.getColName());
				datas.put(q.getColName(), convertOptionStringToList(optionsString));
			}
		}		
		return datas;
	}
	
	public void create(QnrTable template, Map<String, Object> datas) throws SQLException, ParseException {		
		this.qnrDao.insert(template, datas);
	}
	
	public Object castDataToAppropriateType(String data, Class<?> dataType) throws ParseException {
		if (data == null || StringUtils.isBlank(data)) {
			return null;
		} else if (dataType == String.class) {
			return data;
		} else if (dataType == Date.class) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date parsed = format.parse(data);
			return new Date(parsed.getTime());
		} else if (dataType == Timestamp.class) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			java.util.Date parsed = format.parse(data);
			return new Timestamp(parsed.getTime());
		} else if (dataType == int.class) {
			return Integer.valueOf(data);
		} else if (dataType == long.class) {
			return Long.valueOf(data);
		} else if (dataType == float.class) {
			return Float.valueOf(data);
		} else if (dataType == double.class) {
			return Double.valueOf(data);
		} else {
			throw new IllegalArgumentException("Unsupported data type!");
		}
	}
	
	public String mergeStringArrayToString(String[] strs) {
		if (strs == null) return null;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<strs.length; i++) {
			sb.append(strs[i]);
			if (i < strs.length -1) sb.append(", "); 
		}
		return sb.toString();
	}

	public PagedList<Map<String, Object>> search(QnrSearchConditionSet conditions) throws SQLException {		
		PagedList<Map<String, Object>> res = this.qnrDao.search(conditions);
		
		for (Map<String, Object> datas : res.getList()) {
			for (QnrTableColumn q : conditions.getTemplate().getQuestionList()) {
				if (q.getInputType().equals(QnrTableColumn.INPUT_TYPE_CHECKBOX) ||
					q.getInputType().equals(QnrTableColumn.INPUT_TYPE_CHECKBOX_OPTION)	) {
					String optionsString = (String)datas.get(q.getColName());
					datas.put(q.getColName(), convertOptionStringToList(optionsString));
				}
			}
		}		
		return res;
	}
	
	private List<String> convertOptionStringToList(String optionsString) {
		ArrayList<String> optionList = new ArrayList<String>();
		if (StringUtils.isNotBlank(optionsString)) {
			for (String s : StringUtils.split(optionsString, ",")) {
				optionList.add(s.trim());
			}			
		}
		return optionList;
	}
	
	public boolean isTableHasData(QnrTable template) throws SQLException {
		return this.qnrDao.isTableHasData(template);
	}
	
}
