package iace.entity.questionnaire;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import iace.entity.BaseEntity;

@Entity
@Table(name = "QNR_TABLE")
public class QnrTable extends BaseEntity {

	private static final long serialVersionUID = 5432790889914405357L;

	private long id;
	private String name; // 問卷名稱
	private String tableName;
	private List<QnrTableColumn> questionList;
	
	public void addCommonTableFields() {		
		QnrTableColumn idT = new QnrTableColumn();
		idT.setColName("ID");
		idT.setQuestion("主鍵");
		idT.setDataType(QnrTableColumn.DATA_TYPE_NUMBER);
		idT.setPrecision(19);
		idT.setScale(0);
		idT.setIsPk(true);
		idT.setHidden(true);
		this.questionList.add(0, idT);
		
		QnrTableColumn isValidT = new QnrTableColumn();
		isValidT.setColName("IS_VALID");
		isValidT.setQuestion("本資料是否有效(T/F)");
		isValidT.setDataType(QnrTableColumn.DATA_TYPE_STRING);
		isValidT.setLength(1);
		isValidT.setNullable(true);
		isValidT.setHidden(true);
		this.questionList.add(isValidT);
		
		QnrTableColumn createTimeT = new QnrTableColumn();
		createTimeT.setColName("CREATE_TIME");
		createTimeT.setQuestion("建立時間");
		createTimeT.setDataType(QnrTableColumn.DATA_TYPE_TIMESTAMP);
		createTimeT.setNullable(true);
		createTimeT.setHidden(true);
		this.questionList.add(createTimeT);
		
		QnrTableColumn createUserT = new QnrTableColumn();
		createUserT.setColName("CREATE_USER");
		createUserT.setQuestion("建立人員");
		createUserT.setDataType(QnrTableColumn.DATA_TYPE_STRING);
		createUserT.setLength(100);
		createUserT.setNullable(true);
		createUserT.setHidden(true);
		this.questionList.add(createUserT);
		
		QnrTableColumn updateTimeT = new QnrTableColumn();
		updateTimeT.setColName("UPDATE_TIME");
		updateTimeT.setQuestion("異動時間");
		updateTimeT.setDataType(QnrTableColumn.DATA_TYPE_TIMESTAMP);
		updateTimeT.setNullable(true);
		updateTimeT.setHidden(true);
		this.questionList.add(updateTimeT);
		
		QnrTableColumn updateUserT = new QnrTableColumn();
		updateUserT.setColName("UPDATE_USER");
		updateUserT.setQuestion("異動人員");
		updateUserT.setDataType(QnrTableColumn.DATA_TYPE_STRING);
		updateUserT.setLength(100);
		updateUserT.setNullable(true);
		updateUserT.setHidden(true);
		this.questionList.add(updateUserT);
		
		QnrTableColumn verT = new QnrTableColumn();
		verT.setColName("VER");
		verT.setQuestion("版本");
		verT.setDataType(QnrTableColumn.DATA_TYPE_TIMESTAMP);
		verT.setNullable(false);
		verT.setHidden(true);
		this.questionList.add(verT);		
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

	@Column(name = "NAME", length = 500, unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TABLE_NAME", length = 20, unique = true, nullable = false)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@OneToMany(mappedBy="qnrTemplate", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	public List<QnrTableColumn> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QnrTableColumn> questionList) {
		this.questionList = questionList;
	}

	@Transient
	public QnrTableColumn getQuestionByColName(String colName) {
		for (QnrTableColumn qt : this.questionList) {
			if (qt.getColName().equals(colName)) {
				return qt;
			}
		}
		return null;
	}
	
	@Transient
	public int getQuestionNum() {
		return this.questionList.size();
	}
	
	@Transient
	public List<String> getColNameList() {
		List<String> list = new ArrayList<String>();
//		this.questionList.forEach(v -> list.add(v.getColName()));
		for (int i=0; i< this.questionList.size(); i++) {
			list.add(this.questionList.get(i).getColName());
		}
		return list;
	}
}
