package iace.entity.option;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OPT_SUBJECT")
public class OptionSubject extends BaseOption {

	
	private static final long serialVersionUID = 8466202865101688332L;

	@Transient
	public static int getCodeLength(int lv) {
		switch (lv) {
		case 1: return 1;
		case 2: return 3;
		case 3: return 7;
		case 4: return 9;
		default:
			throw new IllegalArgumentException("code Level out of bounds");
		}
	}
	
	/**
	 * 取得此代碼所代表的層級
	 * <table>
	 * 	<tr><th>LV</th><th>CODE</th><th>NAME</th></tr>
	 * 	<tr><th>1</th><td>B</td><td>生物科學類<td><tr>
	 * 	<tr><th>2</th><td>B10</td><td>醫學<td><tr>
	 * 	<tr><th>3</th><td>B101001</td><td>解剖<td><tr>
	 * 	<tr><th>4</th><td>B10100101</td><td>細胞及分子生物學<td><tr>
	 * </table>
	 * @return
	 */
	@Transient
	public int getLv() {
		switch (super.getCode().length()) {
		case 1: return 1;
		case 3: return 2;
		case 7: return 3;
		case 9: return 4;
		default:
			throw new IllegalArgumentException("代碼長度不合法");
		}
	}
	

	
	@Transient
	public String getLv1Code() {
		return this.getCode().substring(0, 1);
	}
	
	@Transient
	public String getLv2Code() {
		if (getLv() >= 2) return this.getCode().substring(1, 3);
		else return null;
	}
	
	@Transient
	public String getLv3Code() {
		if (getLv() >= 3) return this.getCode().substring(3, 7);
		else return null;
	}
	
	@Transient
	public String getLv4Code() {
		if (getLv() >= 4) return this.getCode().substring(7, 9);
		else return null;
	}
	
	@Override
	public String toString() {
		return "\"OptionSubject\" : {" + super.toString() + "}";
	}

}
