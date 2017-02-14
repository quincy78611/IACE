package iace.entity.sys;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import iace.entity.BaseEntity;

@Entity
@Table(name = "SYS_LOG")
public class SysLog extends BaseEntity {
	private static final long serialVersionUID = -3869735672852230110L;
	public static Map<String, String> namespaces;
	public static Map<String, String> actionNames;
	static {
		namespaces = new TreeMap<String, String>();
		namespaces.put("/about", "關於");
		namespaces.put("/activity", "活動人培");
		namespaces.put("/batchSendEmail", "批次發送郵件");
		namespaces.put("/consulting", "諮詢服務表");
		namespaces.put("/contactUs", "客服信箱");
		namespaces.put("/coopEx", "產學合作案例");
		namespaces.put("/enterpriseNeed", "企業需求單");
		namespaces.put("/epaper", "電子報");
		namespaces.put("/epaperSubscriber", "電子報訂閱者");
		namespaces.put("/faq", "常問集");
		namespaces.put("/file", "檔案中心");
		namespaces.put("/incubationCenter", "育成中心");
		namespaces.put("/literature", "文獻與法規");
		namespaces.put("/member", "會員");
		namespaces.put("/news", "公告訊息");
		namespaces.put("/patent", "專利");
		namespaces.put("/qnrCooperateWay", "產學合作問卷");
		namespaces.put("/rdFocus", "研發焦點");
		namespaces.put("/researchPlan", "研發成果");
		namespaces.put("/sysRole", "系統角色");
		namespaces.put("/sysUser", "系統使用者");
		namespaces.put("/f/talentedPeople", "產學人才");
		namespaces.put("/talentedPeople", "產學人才資料");
		namespaces.put("/talentedPeopleRdResult", "產學人才-重要研發成果");
		namespaces.put("/talentedPeopleTransferCase", "產學人才-成果移轉及授權案例");
		namespaces.put("/talentedPeopleMainProject", "產學人才-產學合作計畫案");
		namespaces.put("/videosArea", "影片專區");
		namespaces.put("/login", "登入/登出");
		actionNames = new TreeMap<String, String>();
		actionNames.put("loginSubmit", "登入");
		actionNames.put("logout", "登出");
		actionNames.put("batchImportSubmit", "批次匯入");
		actionNames.put("sendEmailSubmit", "批次發信");
		actionNames.put("create", "新增");
		actionNames.put("createSubmit", "新增");
		actionNames.put("createTechnologySubmit", "新增研發成果");
		actionNames.put("delete", "刪除");
		actionNames.put("deleteSubmit", "刪除");
		actionNames.put("updateTechnologySubmit", "刪除研發成果");
		actionNames.put("publish", "發佈");
		actionNames.put("update", "編輯");
		actionNames.put("updateSubmit", "編輯");
		actionNames.put("updateTechnologySubmit", "編輯研發成果");
		actionNames.put("upload", "上傳");
		actionNames.put("uploadFile", "上傳");
	}

	private transient boolean enableLog;
	
	private long id;
	
	private String namespace;
	private String actionName;
	
	private String ip;
	private SysUser sysUser;

	private String tableName;
	private String before;
	private String after;
	private transient BaseEntity beforeEntity;
	private transient BaseEntity afterEntity;
	
	@Transient
	public boolean isEnableLog() {
		return enableLog;
	}
	
	public void setEnableLog(boolean enableLog) {
		this.enableLog = enableLog;
	}

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SYS_LOG_ID")
	@SequenceGenerator(name = "SEQ_SYS_LOG_ID", sequenceName = "SEQ_SYS_LOG_ID", allocationSize = 1, initialValue = 1)	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "NAMESPACE")
	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	@Column(name = "ACTION_NAME")
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@ManyToOne
	@JoinColumn(name="SYS_USER_ID")
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "BEFORE")
	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	@Column(name = "AFTER")
	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	@Transient
	public BaseEntity getBeforeEntity() {
		return beforeEntity;
	}

	public void setBeforeEntity(BaseEntity beforeEntity) {
		this.beforeEntity = beforeEntity;
		if (this.beforeEntity != null) {
			Table table = this.beforeEntity.getClass().getAnnotation(Table.class);
			this.tableName = table.name();
//			this.before = this.beforeEntity.toSysLog();
			this.before = new Gson().toJson(beforeEntity);
			this.enableLog = true;
		}
	}

	@Transient
	public BaseEntity getAfterEntity() {
		return afterEntity;
	}

	public void setAfterEntity(BaseEntity afterEntity) {
		this.afterEntity = afterEntity;
		if (this.afterEntity != null) {
			Table table = this.afterEntity.getClass().getAnnotation(Table.class);
			this.tableName = table.name();
//			this.after = this.afterEntity.toSysLog();
			this.after = new Gson().toJson(afterEntity);
			this.enableLog = true;
		}
	}
	
	@Transient
	public String getBeforeJsonPrettyPrint() {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(this.before).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(json);
		return jsonString.replace("\n", "<br/>").replace(" ", "&nbsp;");
	}
	
	@Transient
	public String getAfterJsonPrettyPrint() {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(this.after).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(json);
		return jsonString.replace("\n", "<br/>").replace(" ", "&nbsp;&nbsp;");
	}
	
	@Deprecated
	@Transient
	public String[] getBeforeStrings() {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(this.before).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyPrint = gson.toJson(json);
		return prettyPrint.split("\n");

	}
	
	@Deprecated
	@Transient
	public String[] getAfterStrings() {
		return this.after.split(", \r\n");
	}
}
