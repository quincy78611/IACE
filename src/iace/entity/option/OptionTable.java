package iace.entity.option;

import java.util.ArrayList;
import java.util.List;

public class OptionTable {

	private String code;
	private String name;
	
	public OptionTable() {}
	
	public OptionTable(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<OptionTable> listAll() {
		List<OptionTable> list = new ArrayList<OptionTable>();
		list.add(new OptionTable("OPT_COUNTRY", "專利國別"));
		list.add(new OptionTable("OPT_COM_LOCATION", "公司地域別"));
		list.add(new OptionTable("CONSULTING", "諮詢類型代"));
		list.add(new OptionTable("OPT_COOPERATE_MODE", "合作模式"));
		list.add(new OptionTable("OPT_GRB_DOMAIN", "GRB領域別"));
		list.add(new OptionTable("OPT_HAD_TEC_SRC", "企業已有技術來源"));
		list.add(new OptionTable("OPT_INDUSTRY", "產業/領域別"));
		list.add(new OptionTable("OPT_ORG_CLASS", "單位類別"));
		list.add(new OptionTable("OPT_ORG_TYPE", "單位類型"));
		list.add(new OptionTable("OPT_TRL", "發展階段"));
		list.add(new OptionTable("OPT_SUBJECT", "科技部學門"));
		return list;
	}

}
