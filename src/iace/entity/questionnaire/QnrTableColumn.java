package iace.entity.questionnaire;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;
import iace.entity.option.OptionForQnr;
import iace.entity.option.OptionQnrInputType;
import iace.entity.option.OptionQnrSearchType;

@Entity
@Table(name = "QNR_TABLE_COL")
public class QnrTableColumn extends BaseEntity {

	private static final long serialVersionUID = 3176422161074472879L;

	public static final String DATA_TYPE_BOOLEAN = "DATA_TYPE_BOOLEAN";
	public static final String DATA_TYPE_DATE = "DATA_TYPE_DATE";
	public static final String DATA_TYPE_TIMESTAMP = "DATA_TYPE_TIMESTAMP";
	public static final String DATA_TYPE_STRING = "DATA_TYPE_STRING";
	public static final String DATA_TYPE_NUMBER = "DATA_TYPE_NUMBER";

	public static final String INPUT_TYPE_TEXTFIELD_TEXT = "INPUT_TYPE_TEXTFIELD_TEXT";
	public static final String INPUT_TYPE_TEXTFIELD_NUM = "INPUT_TYPE_TEXTFIELD_NUM";
	public static final String INPUT_TYPE_TEXTFIELD_DATE = "INPUT_TYPE_TEXTFIELD_DATE";
	public static final String INPUT_TYPE_SELECT = "INPUT_TYPE_SELECT";
	public static final String INPUT_TYPE_SELECT_OPTION = "INPUT_TYPE_SELECT_OPTION";
	public static final String INPUT_TYPE_CHECKBOX = "INPUT_TYPE_CHECKBOX";
	public static final String INPUT_TYPE_CHECKBOX_OPTION = "INPUT_TYPE_CHECKBOX_OPTION";
	public static final String INPUT_TYPE_HIDDEN = "INPUT_TYPE_HIDDEN";
	
	private long id;
	private String colName;
	private String question;
	
	private String dataType;
	private String inputType;
	
	private String fromOption;
	private String optionListString;
	private List<OptionForQnr> optionList;
	
	/**
	 * for java data type [String].
	 * if 0 < length <= 2000, then use Oracle DB type [NVARCHAR2],
	 * else use Oracle DB type [CLOB]
	 */
	private int length;
	
	/**
	 * for Oracle DB type [NUMBER (precision, scale)]
	 * max precision is 38, scale range is (-84 to 127)
	 * if precision <= 10 && scale == 0, use Java type [int] 
	 * if precision <= 19 && scale == 0, use Java type [long]
	 * if precision <= 38 && scale <= 38, use Java type [float]
	 * else use Java type [double]
	 */
	private int precision;
	private int scale;
	
	private boolean unique;
	private boolean nullable;
	private boolean isPk;
	private boolean hidden;
	private boolean isSearchCondition;
	
	private QnrTable qnrTemplate;
	
	@Transient
	public static List<OptionQnrInputType> getQnrInputTypes(){
		List<OptionQnrInputType> list = new ArrayList<OptionQnrInputType>();
		list.add(new OptionQnrInputType(INPUT_TYPE_TEXTFIELD_TEXT, "文字輸入框"));
		list.add(new OptionQnrInputType(INPUT_TYPE_TEXTFIELD_NUM, "數字輸入框"));
		list.add(new OptionQnrInputType(INPUT_TYPE_TEXTFIELD_DATE, "日期輸入框"));
		list.add(new OptionQnrInputType(INPUT_TYPE_SELECT, "下拉選單"));
		list.add(new OptionQnrInputType(INPUT_TYPE_SELECT_OPTION, "下拉選單(代碼表)"));
		list.add(new OptionQnrInputType(INPUT_TYPE_CHECKBOX, "多選框"));
		list.add(new OptionQnrInputType(INPUT_TYPE_CHECKBOX_OPTION, "多選框(代碼表)"));
		return list;
	}
	
	@Transient
	public boolean isValidSetting() {
		if (this.dataType == DATA_TYPE_NUMBER) {
			if (this.precision <= 38 && this.scale < 127) {
				return true;
			} else {
				return false;
			}
		}	
		
		return true;
	}
	
	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_QUESTIONNAIRE_ID")
	@SequenceGenerator(name = "SEQUENCE_QUESTIONNAIRE_ID", sequenceName = "SEQUENCE_QUESTIONNAIRE_ID", allocationSize = 1, initialValue = 1)	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "COL_NAME")
	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	@Column(name = "QUESTION", length = 500)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "DATA_TYPE")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
		if (dataType == DATA_TYPE_BOOLEAN) {
			this.length = 1;
		}
	}

	@Column(name = "INPUT_TYPE")
	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
		if (this.inputType.equals(INPUT_TYPE_TEXTFIELD_TEXT)) {
			setDataType(DATA_TYPE_STRING);
		} else if (this.inputType.equals(INPUT_TYPE_TEXTFIELD_NUM)) {
			setDataType(DATA_TYPE_NUMBER);
		} else if (this.inputType.equals(INPUT_TYPE_TEXTFIELD_DATE)) {
			setDataType(DATA_TYPE_DATE);
		} else if (this.inputType.equals(INPUT_TYPE_SELECT)) {
			setDataType(DATA_TYPE_STRING);
			setLength(2000);
		} else if (this.inputType.equals(INPUT_TYPE_SELECT_OPTION)) {
			setDataType(DATA_TYPE_STRING);
			setLength(10);
		} else if (this.inputType.equals(INPUT_TYPE_CHECKBOX)) {
			setDataType(DATA_TYPE_STRING);
			setLength(2000);
		} else if (this.inputType.equals(INPUT_TYPE_CHECKBOX_OPTION)) {
			setDataType(DATA_TYPE_STRING);
			setLength(2000);
		} else if (this.inputType.equals(INPUT_TYPE_HIDDEN)) {
			//do nothing
		} else {
			throw new IllegalArgumentException("Unsupported type");
		}		
	}

	@Column(name = "FROM_OPTION", length=30)
	public String getFromOption() {
		return fromOption;
	}

	public void setFromOption(String fromOption) {
		this.fromOption = fromOption;
	}

	@Column(name = "OPTION_LIST_STRING", length=2000)
	public String getOptionListString() {
		return optionListString;
	}

	public void setOptionListString(String optionListString) {
		this.optionListString = optionListString;
	}
	
	@Transient
	public List<OptionForQnr> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<OptionForQnr> optionList) {
		this.optionList = optionList;
	}

	@Column(name = "LENGTH")
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	@Column(name = "PRECISION")
	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	@Column(name = "SCALE")
	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}
	
	@Transient
	public Class<?> getJavaType() {
		switch (this.dataType) {
		case DATA_TYPE_BOOLEAN:
			return String.class;
		case DATA_TYPE_DATE:
			return Date.class;
		case DATA_TYPE_TIMESTAMP:
			return Timestamp.class;
		case DATA_TYPE_STRING: 
			return String.class;
		case DATA_TYPE_NUMBER:
			if (this.precision <= 10 && this.scale == 0) return int.class;
			if (this.precision <= 19 && this.scale == 0) return long.class;
			if (this.precision <= 38 && this.scale < 38) return float.class;
			return double.class;
		default:
			throw new IllegalArgumentException("Unsupported data type!");
		}
	}
	
	@Transient
	public int getSqlType() {
		switch (this.dataType) {
		case DATA_TYPE_BOOLEAN:
			return java.sql.Types.NVARCHAR;
		case DATA_TYPE_DATE:
			return java.sql.Types.DATE;
		case DATA_TYPE_TIMESTAMP:
			return java.sql.Types.TIMESTAMP;
		case DATA_TYPE_STRING:
			if (0 < this.length && this.length <= 2000) return java.sql.Types.NVARCHAR;
			else return java.sql.Types.CLOB;
		case DATA_TYPE_NUMBER:
			return java.sql.Types.NUMERIC;
		default:
			throw new IllegalArgumentException("Unsupported data type!");
		}
	}

	@Column(name = "UNIQUE_COL")
	@Type(type="true_false")
	public boolean getUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		if (this.isPk && unique == false) {
			throw new IllegalArgumentException("This column is primary key, must be unique!");
		}
		this.unique = unique;
	}

	@Column(name = "NULLABLE")
	@Type(type="true_false")
	public boolean getNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		if (this.isPk && nullable) {
			throw new IllegalArgumentException("This column is primary key, can't be null!");
		}
		this.nullable = nullable;
	}

	@Column(name = "IS_PK")
	@Type(type="true_false")
	public boolean getIsPk() {
		return isPk;
	}

	public void setIsPk(boolean isPk) {
		this.isPk = isPk;
		if (isPk) {
			this.nullable = false;
			this.unique = true;			
		}
	}

	@Column(name = "HIDDEN")
//	@Type(type= "org.hibernate.type.NumericBooleanType")
	@Type(type="true_false")
	public boolean getHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		this.setInputType(INPUT_TYPE_HIDDEN);
	}
	
	@Column(name = "IS_SEARCH_CONDITION")
	@Type(type="true_false")
	public boolean isSearchCondition() {
		return isSearchCondition;
	}

	public void setSearchCondition(boolean isSearchCondition) {
		this.isSearchCondition = isSearchCondition;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QNR_TABLE_ID", nullable = false, updatable = false)
	public QnrTable getQnrTemplate() {
		return qnrTemplate;
	}

	public void setQnrTemplate(QnrTable qnrTemplate) {
		this.qnrTemplate = qnrTemplate;
	}

	@Transient
	public List<OptionQnrSearchType> getQnrSearchTypeList() {
		List<OptionQnrSearchType> list = new ArrayList<OptionQnrSearchType>();

		if (this.inputType.equals(INPUT_TYPE_TEXTFIELD_TEXT)) {		
			list.add(OptionQnrSearchType.CONTAIN);
			list.add(OptionQnrSearchType.START);
			list.add(OptionQnrSearchType.END);
		} else if ( this.inputType.equals(INPUT_TYPE_TEXTFIELD_NUM) ||
					this.inputType.equals(INPUT_TYPE_TEXTFIELD_DATE)) {
			list.add(OptionQnrSearchType.EQ);
			list.add(OptionQnrSearchType.NEQ);
			list.add(OptionQnrSearchType.SM);
			list.add(OptionQnrSearchType.LG);
		} else if ( this.inputType.equals(INPUT_TYPE_CHECKBOX) || 
					this.inputType.equals(INPUT_TYPE_CHECKBOX_OPTION) ) {
			list.add(OptionQnrSearchType.CONTAIN);
		} else {
			list.add(OptionQnrSearchType.EQ);
			list.add(OptionQnrSearchType.NEQ);
		}
		return list;
	}

	@Transient
	public boolean isEmptyEntity() {
		if (this.colName != null) return false;
		if (this.dataType != null) return false;
		if (this.inputType != null) return false;
		if (this.question != null) return false;
		return true;
	}
	
	
}
