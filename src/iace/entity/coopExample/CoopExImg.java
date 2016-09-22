package iace.entity.coopExample;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import core.util.ThumbnailUtil;

@Entity
@Table(name = "COOP_EXAMPLE_IMG")
public class CoopExImg extends CoopExFile {

	private static final long serialVersionUID = 963064524540265328L;

	private transient byte[] byteImg;

	@Transient
	public byte[] getByteImg() {
		return byteImg;
	}

	public void setByteImg(byte[] byteImg) {
		this.byteImg = byteImg;
	}
	
	@Transient
	public String getBase64Img() throws IOException {
		if (this.byteImg != null) {
			return Base64.encode(this.byteImg);
		}
		return null;
	}
	
	@Transient
	public String getBase64Thumbnail(int width, int height) throws IOException {
		if (this.byteImg != null) {
			return Base64.encode(ThumbnailUtil.resize(this.byteImg, width, height, true));
		}
		return null;
	}
}
