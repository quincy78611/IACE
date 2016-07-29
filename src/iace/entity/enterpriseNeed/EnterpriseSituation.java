package iace.entity.enterpriseNeed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import iace.entity.BaseEntity;
import iace.entity.option.OptionCooperateMode;
import iace.entity.option.OptionHadTecSrc;

@Entity
@Table(name = "ENTERPRISE_SITUATION")
public class EnterpriseSituation extends BaseEntity {

	private static final long serialVersionUID = -4902000698677155773L;

	private long id;
	private EnterpriseInfo enterpriseInfo;
	private OptionHadTecSrc optionHadTecSrc;
	private double hadTecSrcRation;
	private Boolean hasComCoopExp;
	private String coopTopic;
	private String coopPros;
	private String coopCons;
	private Boolean hasAcademiaCoopExp;
	private String academiaTopic;
	private String academiaIntention;
	private String academiaPros;
	private String academiaCons;
	private OptionCooperateMode optionCooperateMode;
	private String specificTopic;
	private String otherCoopTarget;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_ENTERPRISE_ID")
	@SequenceGenerator(name = "SEQUENCE_ENTERPRISE_ID", sequenceName = "SEQUENCE_ENTERPRISE_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ENTERPRISE_INFO_ID", referencedColumnName= "ID")
	public EnterpriseInfo getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}

	@ManyToOne
	@JoinColumn(name="OPT_HAD_TEC_SRC_ID", referencedColumnName= "ID")	
	public OptionHadTecSrc getOptionHadTecSrc() {
		return optionHadTecSrc;
	}

	public void setOptionHadTecSrc(OptionHadTecSrc optionHadTecSrc) {
		this.optionHadTecSrc = optionHadTecSrc;
	}

	@Column(name = "HAD_TECH_SRC_RATION", precision=10, scale=2)
	public double getHadTecSrcRation() {
		return hadTecSrcRation;
	}

	public void setHadTecSrcRation(double hadTecSrcRation) {
		this.hadTecSrcRation = hadTecSrcRation;
	}

	@Column(name = "HAS_COM_COOP_EXP")
	@Type(type="true_false")
	public Boolean getHasComCoopExp() {
		return hasComCoopExp;
	}

	public void setHasComCoopExp(Boolean hasComCoopExp) {
		this.hasComCoopExp = hasComCoopExp;
	}

	@Column(name = "COOP_TOPIC", length = 1000)
	public String getCoopTopic() {
		return coopTopic;
	}

	public void setCoopTopic(String coopTopic) {
		this.coopTopic = coopTopic;
	}

	@Column(name = "COOP_PROS", length = 1000)
	public String getCoopPros() {
		return coopPros;
	}

	public void setCoopPros(String coopPros) {
		this.coopPros = coopPros;
	}

	@Column(name = "COOP_CONS", length = 1000)
	public String getCoopCons() {
		return coopCons;
	}

	public void setCoopCons(String coopCons) {
		this.coopCons = coopCons;
	}

	@Column(name = "HAS_ACADEMIA_COOP_EXP")
	@Type(type="true_false")
	public Boolean getHasAcademiaCoopExp() {
		return hasAcademiaCoopExp;
	}

	public void setHasAcademiaCoopExp(Boolean hasAcademiaCoopExp) {
		this.hasAcademiaCoopExp = hasAcademiaCoopExp;
	}

	@Column(name = "ACADEMIA_TOPIC", length = 1000)
	public String getAcademiaTopic() {
		return academiaTopic;
	}

	public void setAcademiaTopic(String academiaTopic) {
		this.academiaTopic = academiaTopic;
	}

	@Column(name = "ACADEMIA_INTENTION", length = 1000)
	public String getAcademiaIntention() {
		return academiaIntention;
	}

	public void setAcademiaIntention(String academiaIntention) {
		this.academiaIntention = academiaIntention;
	}

	@Column(name = "ACADEMIA_PROS", length = 1000)
	public String getAcademiaPros() {
		return academiaPros;
	}

	public void setAcademiaPros(String academiaPros) {
		this.academiaPros = academiaPros;
	}

	@Column(name = "ACADEMIA_CONS", length = 1000)
	public String getAcademiaCons() {
		return academiaCons;
	}

	public void setAcademiaCons(String academiaCons) {
		this.academiaCons = academiaCons;
	}

	@ManyToOne
	@JoinColumn(name="OPT_COOPERATE_MODE_ID", referencedColumnName= "ID")	
	public OptionCooperateMode getOptionCooperateMode() {
		return optionCooperateMode;
	}

	public void setOptionCooperateMode(OptionCooperateMode optionCooperateMode) {
		this.optionCooperateMode = optionCooperateMode;
	}
	
	@Transient
	public boolean getHasSpecificTopic() {
		return this.specificTopic != null;
	}
	
	public void setHasSpecificTopic(boolean has) {
		if (has == false) {
			this.specificTopic = null;
		}
	}

	@Column(name = "SPECIFIC_TOPIC", length = 1000)
	public String getSpecificTopic() {
		return specificTopic;
	}

	public void setSpecificTopic(String specificTopic) {
		this.specificTopic = specificTopic;
	}
	
	@Transient
	public boolean getHasOtherCoopTarget() {
		return this.otherCoopTarget != null;
	}
	
	public void setHasOtherCoopTarget(boolean has) {
		if (has == false) {
			this.otherCoopTarget = null;
		}
	}

	@Column(name = "OTHER_COOP_TARGET", length = 1000)
	public String getOtherCoopTarget() {
		return otherCoopTarget;
	}

	public void setOtherCoopTarget(String otherCoopTarget) {
		this.otherCoopTarget = otherCoopTarget;
	}

	
	
}
