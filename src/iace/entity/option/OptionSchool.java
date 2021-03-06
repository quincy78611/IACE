package iace.entity.option;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.util.AESEncrypter;

@Entity
@Table(name = "OPT_SCHOOL")
public class OptionSchool extends BaseOption {

	private static final long serialVersionUID = 4644255723852421978L;
	
	private String encryptedId;

	@Deprecated
	@Transient
	public String getEncryptedId() {
		this.encryptedId = AESEncrypter.encrypt(AESEncrypter.KEY, String.valueOf(super.getId()));
		return encryptedId;
	}
	
	@Transient
	public String getUrlEncodeId() {
		try {
			String encryptedId = AESEncrypter.encrypt(AESEncrypter.KEY, String.valueOf(super.getId()));
			return URLEncoder.encode(encryptedId, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

}
