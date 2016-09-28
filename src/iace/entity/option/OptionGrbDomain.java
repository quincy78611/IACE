package iace.entity.option;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import core.util.ThumbnailUtil;

@Entity
@Table(name = "OPT_GRB_DOMAIN")
public class OptionGrbDomain extends BaseOption {

	private static final long serialVersionUID = 8701955362454487326L;

	private Boolean forResearchPlan;
	private OptionDomain mainDomain;
	
	private transient byte[] byteImg;

	@Column(name = "FOR_RESEARCH_PLAN")
	@Type(type = "true_false")
	public Boolean getForResearchPlan() {
		return forResearchPlan;
	}

	public void setForResearchPlan(Boolean forResearchPlan) {
		this.forResearchPlan = forResearchPlan;
	}

	@ManyToOne
	@JoinColumn(name = "OPT_DOMAIN_ID", referencedColumnName = "ID")
	public OptionDomain getMainDomain() {
		return mainDomain;
	}

	public void setMainDomain(OptionDomain mainDomain) {
		this.mainDomain = mainDomain;
	}

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
