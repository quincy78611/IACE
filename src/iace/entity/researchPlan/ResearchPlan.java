package iace.entity.researchPlan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.OptionGrbDomain;
import iace.entity.option.OptionTrl;

@Entity
@Table(name = "RESEARCH_PLAN")
public class ResearchPlan extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = 6137186068641120935L;

	private long id;
	private int year;
	private String planNo;
	private String name;
	private String manager;
	
	private OptionGrbDomain grbDomain1; //研究領域
	private OptionGrbDomain grbDomain2;
	private OptionGrbDomain grbDomain3;
	private OptionGrbDomain grbDomain4;
	private OptionGrbDomain grbDomain5;
	private OptionGrbDomain grbDomain6;	
	
	private String keyword;
	private OptionTrl trl; //計畫發展階段
	private String projkey; //GRB計畫編號
	private String grb05Id; //成果報告ID
	
	private List<Technology> technologies = new ArrayList<Technology>();

	private int clickNum;
	
	private transient byte[] domainImg;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_RESEARCH_PLAN_ID")
	@SequenceGenerator(name = "SEQUENCE_RESEARCH_PLAN_ID", sequenceName = "SEQUENCE_RESEARCH_PLAN_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "YEAR", length = 4)
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "PLAN_NO", length = 100)
	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	@Column(name = "NAME", length = 2000)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MANAGER", length = 100)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@ManyToOne
	@JoinColumn(name="GRB_DOMAIN_CODE1", referencedColumnName= "CODE")
	public OptionGrbDomain getGrbDomain1() {
		return grbDomain1;
	}

	public void setGrbDomain1(OptionGrbDomain grbDomain1) {
		this.grbDomain1 = grbDomain1;
	}

	@ManyToOne
	@JoinColumn(name="GRB_DOMAIN_CODE2", referencedColumnName= "CODE")
	public OptionGrbDomain getGrbDomain2() {
		return grbDomain2;
	}

	public void setGrbDomain2(OptionGrbDomain grbDomain2) {
		this.grbDomain2 = grbDomain2;
	}

	@ManyToOne
	@JoinColumn(name="GRB_DOMAIN_CODE3", referencedColumnName= "CODE")
	public OptionGrbDomain getGrbDomain3() {
		return grbDomain3;
	}

	public void setGrbDomain3(OptionGrbDomain grbDomain3) {
		this.grbDomain3 = grbDomain3;
	}

	@ManyToOne
	@JoinColumn(name="GRB_DOMAIN_CODE4", referencedColumnName= "CODE")
	public OptionGrbDomain getGrbDomain4() {
		return grbDomain4;
	}

	public void setGrbDomain4(OptionGrbDomain grbDomain4) {
		this.grbDomain4 = grbDomain4;
	}

	@ManyToOne
	@JoinColumn(name="GRB_DOMAIN_CODE5", referencedColumnName= "CODE")
	public OptionGrbDomain getGrbDomain5() {
		return grbDomain5;
	}

	public void setGrbDomain5(OptionGrbDomain grbDomain5) {
		this.grbDomain5 = grbDomain5;
	}

	@ManyToOne
	@JoinColumn(name="GRB_DOMAIN_CODE6", referencedColumnName= "CODE")
	public OptionGrbDomain getGrbDomain6() {
		return grbDomain6;
	}

	public void setGrbDomain6(OptionGrbDomain grbDomain6) {
		this.grbDomain6 = grbDomain6;
	}
	
	@Transient
	public List<OptionGrbDomain> getGrbDomains() {
		List<OptionGrbDomain> grbDomains = new ArrayList<OptionGrbDomain>();
		if (this.grbDomain1 != null) grbDomains.add(this.grbDomain1);
		if (this.grbDomain2 != null) grbDomains.add(this.grbDomain2);
		if (this.grbDomain3 != null) grbDomains.add(this.grbDomain3);
		if (this.grbDomain4 != null) grbDomains.add(this.grbDomain4);
		if (this.grbDomain5 != null) grbDomains.add(this.grbDomain5);
		if (this.grbDomain6 != null) grbDomains.add(this.grbDomain6);
		return grbDomains;
	}
	
	public void setGrbDomains(List<OptionGrbDomain> grbDomains) {
		for (int i = 0; i < grbDomains.size(); i++) {
			switch (i + 1) {
			case 1:
				this.grbDomain1 = grbDomains.get(i);
				break;
			case 2:
				this.grbDomain2 = grbDomains.get(i);
				break;
			case 3:
				this.grbDomain3 = grbDomains.get(i);
				break;
			case 4:
				this.grbDomain4 = grbDomains.get(i);
				break;
			case 5:
				this.grbDomain5 = grbDomains.get(i);
				break;
			case 6:
				this.grbDomain6 = grbDomains.get(i);
				break;
			}
		}
	}
	
	@Transient
	public List<String> getGrbDomainCodes() {
		List<String> codes = new ArrayList<String>();
		if (getGrbDomains() != null) {
			getGrbDomains().forEach(v -> codes.add(v.getCode()));
		}
		return codes;
	}
	
	public void setGrbDomainCodes(List<String> codes) {
		List<OptionGrbDomain> grbDomainList = new ArrayList<OptionGrbDomain>();
		for (String grbCode : codes) {
			OptionGrbDomain grbDomain = new OptionGrbDomain();
			grbDomain.setCode(grbCode);
			grbDomainList.add(grbDomain);
		}
		this.setGrbDomains(grbDomainList);
	}
	
	@Transient
	public String grbDomainCodeString() {
		StringBuilder sb = new StringBuilder();
		for (OptionGrbDomain grb : getGrbDomains()) {
			sb.append(grb.getCode()).append(";");
		}
		
		return sb.toString();
	}

	@Column(name = "KEYWORD", length = 2000)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@ManyToOne
	@JoinColumn(name="TRL_CODE", referencedColumnName= "CODE")
	public OptionTrl getTrl() {
		return trl;
	}

	public void setTrl(OptionTrl trl) {
		this.trl = trl;
	}
	
	@Transient
	public String getTrlCode() {
		if (this.trl != null) {
			return trl.getCode();
		}
		return null;
	}
	
	public void setTrlCode(String code) {
		OptionTrl trl = new OptionTrl();
		trl.setCode(code);
		this.setTrl(trl);
	}

	@Column(name = "PROJKEY", length = 100)
	public String getProjkey() {
		return projkey;
	}

	public void setProjkey(String projkey) {
		this.projkey = projkey;
	}

	@Column(name = "GRB05_ID", length = 500)
	public String getGrb05Id() {
		return grb05Id;
	}

	public void setGrb05Id(String grb05Id) {
		this.grb05Id = grb05Id;
	}

	@OneToMany(mappedBy="researchPlan", cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}
	
	public void addTechnology(Technology technologies) {
		if (this.technologies == null) {
			this.technologies = new ArrayList<Technology>();
		}
		this.technologies.add(technologies);
	}
	
	public void addTechnology(List<Technology> technologies) {
		if (this.technologies == null) {
			this.technologies = new ArrayList<Technology>();
		}		
		this.technologies.addAll(technologies);
	} 
	
	@Column(name = "CLICK_NUM")
	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	
	@Transient
	public byte[] getDomainImg() {
		return domainImg;
	}

	public void setDomainImg(byte[] domainImg) {
		this.domainImg = domainImg;
	}
	
	@Transient
	public String getBase64DomainImg() {
		return Base64.encode(this.domainImg);
	}

	@Override
	public void create() {
		super.create();
		if (this.technologies != null) {
			for (Technology rnd : this.technologies) {
				rnd.create();
			}				
		}			
	}

	@Override
	public void update() {
		super.update();
		if (this.technologies != null) {
			for (Technology rnd : this.technologies) {
				rnd.update();
			}			
		}		
	}

	@Override
	public void delete() {
		super.delete();
		if (this.technologies != null) {
			for (Technology rnd : this.technologies) {
				rnd.delete();
			}			
		}
	}

	@Override
	public String toString() {
		return "ResearchPlan [id=" + id + ", year=" + year + ", planNo=" + planNo + ", name=" + name + ", manager=" + manager + ", grbDomain1=" + grbDomain1 + ", grbDomain2=" + grbDomain2 + ", grbDomain3=" + grbDomain3 + ", grbDomain4=" + grbDomain4 + ", grbDomain5=" + grbDomain5 + ", grbDomain6=" + grbDomain6 + ", keyword=" + keyword + ", trl=" + trl + ", projkey=" + projkey + ", grb05Id=" + grb05Id + ", technologies=" + technologies + "]";
	}

	@Deprecated
	@Override
	public String toSysLog() {
		StringBuffer sb = new StringBuffer();
		sb.append("ID: {"+this.id+"}, \r\n");
		sb.append("計畫名稱: {"+this.name+"}, \r\n");
		sb.append("計畫編號: {"+this.planNo+"}, \r\n");
		sb.append("計畫主持人: {"+this.manager+"}, \r\n");
		sb.append("計畫年度: {"+this.year+"}, \r\n");
		sb.append("GRB系統編號: {"+this.grb05Id+"}, \r\n");
		sb.append("計畫發展階段: {"+(this.trl == null ? null : this.trl.toSysLog())+"}, \r\n");
		sb.append("研究領域1: {"+(this.grbDomain1 == null ? null : this.grbDomain1.toSysLog())+"}, \r\n");
		sb.append("研究領域2: {"+(this.grbDomain2 == null ? null : this.grbDomain2.toSysLog())+"}, \r\n");
		sb.append("研究領域3: {"+(this.grbDomain3 == null ? null : this.grbDomain3.toSysLog())+"}, \r\n");
		sb.append("研究領域4: {"+(this.grbDomain4 == null ? null : this.grbDomain4.toSysLog())+"}, \r\n");
		sb.append("研究領域5: {"+(this.grbDomain5 == null ? null : this.grbDomain5.toSysLog())+"}, \r\n");
		sb.append("研究領域6: {"+(this.grbDomain6 == null ? null : this.grbDomain6.toSysLog())+"}, \r\n");
		sb.append("計畫關鍵詞: {"+this.keyword+"}, \r\n");
		
		return sb.toString();
	}

	@Override
	public String toLunceneContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name).append(" ");
		sb.append("\t"+this.manager+"\t").append(" ");
		if (StringUtils.isNotBlank(this.keyword)) {
			String[] keywords = this.keyword.replace("；", ";").split(";");
			for (String s : keywords) {
				sb.append("\t"+s+"\t").append(" ");
			}
		}
		for (Technology tec : this.technologies) {
			sb.append(tec.getName()+" "+tec.getDescriptoin()).append(" ");
		}

		return sb.toString();
	}

	

	
}
