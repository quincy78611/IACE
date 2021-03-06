package iace.entity.option;

public class OptionQnrDataType {

	private String code;
	private String name;
	
	public OptionQnrDataType() {}
	
	public OptionQnrDataType(String code, String name) {
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
}
