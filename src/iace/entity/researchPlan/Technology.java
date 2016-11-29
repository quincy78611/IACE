package iace.entity.researchPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import iace.entity.BaseEntity;
import iace.entity.IntegrationSearch;
import iace.entity.option.OptionTrl;

@Entity
@Table(name = "TECHNOLOGY")
public class Technology extends BaseEntity implements IntegrationSearch {

	private static final long serialVersionUID = 1695680251829946337L;

	private long id;
	private ResearchPlan researchPlan;
	private String name;
	private String descriptoin;
	private List<OptionTrl> optionTrlList = new ArrayList<OptionTrl>();
	private String trlDesc;

	private int clickNum;

	@Id
	@Column(name = "ID", length = 19, unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TECHNOLOGY_ID")
	@SequenceGenerator(name = "SEQUENCE_TECHNOLOGY_ID", sequenceName = "SEQUENCE_TECHNOLOGY_ID", allocationSize = 1, initialValue = 1)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RESEARCH_PLAN_ID", nullable = false, updatable = false)
	public ResearchPlan getResearchPlan() {
		return researchPlan;
	}

	public void setResearchPlan(ResearchPlan researchPlan) {
		this.researchPlan = researchPlan;
	}

	@Column(name = "NAME", length = 2000)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	@Lob
	public String getDescriptoin() {
		return descriptoin;
	}

	public void setDescriptoin(String descriptoin) {
		this.descriptoin = descriptoin;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="TECHNOLOGY_TRL_RELATION", 
    	joinColumns={@JoinColumn(name="TECHNOLOGY_ID")}, 
    	inverseJoinColumns={@JoinColumn(name="TRL_CODE", referencedColumnName= "CODE")})
	@Fetch(FetchMode.SUBSELECT)
	public List<OptionTrl> getOptionTrlList() {
		return optionTrlList;
	}

	public void setOptionTrlList(List<OptionTrl> optionTrlList) {
		this.optionTrlList = optionTrlList;
	}
	
	@Transient
	public List<String> getOptionTrlCodes() {
		List<String> codes = new ArrayList<String>();
		if (this.optionTrlList != null) {
			this.optionTrlList.forEach(v -> codes.add(v.getCode()));
		}		
		return codes;
	}
	
	public void setOptionTrlCodes(List<String> codes) {
		this.optionTrlList = new ArrayList<OptionTrl>();
		for (String code : codes) {
			OptionTrl trl = new OptionTrl();
			trl.setCode(code.trim());
			this.optionTrlList.add(trl);
		}
	}

	@Transient
	public String getOptionTrlCodesString() {
		StringBuilder sb = new StringBuilder();
		if (this.optionTrlList != null) {
			this.optionTrlList.forEach(v -> sb.append(v.getCode()+";"));
		}		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param codeString must be like 'TRL1,TRL2'
	 */
	@Deprecated
	public void setOptionTrlCodesString(String codeString) {
		String[] codes = StringUtils.split(codeString, ",");	
		setOptionTrlCodes(Arrays.asList(codes));
	}
	
	@Column(name = "TRL_DESC")
	@Lob
	public String getTrlDesc() {
		return trlDesc;
	}

	public void setTrlDesc(String trlDesc) {
		this.trlDesc = trlDesc;
	}

	@Column(name = "CLICK_NUM")
	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}
	
	@Override
	public String toSysLog() {
		String s = 
				"ID: {"+this.id+"}, \r\n"+
				"研究計畫: {"+this.researchPlan.getName()+"}, \r\n"+
				"技術名稱: {"+this.name+"}, \r\n"+
				"技術簡述: {"+this.descriptoin+"}, \r\n"+
				"技術發展階段: {"+this.getOptionTrlCodesString()+"}, \r\n"+
				"技術發展階段說明: {"+this.trlDesc+"}, \r\n";
				
		return s;
	}

	@Override
	public String toLunceneContent() {
		String str = this.researchPlan.getName() + " " +
				this.researchPlan.getPlanNo() + " " +
				this.researchPlan.getKeyword() + " " +
				this.researchPlan.getProjkey() + " " +
				this.researchPlan.getGrb05Id() + " " +
				this.name + " " +
				this.descriptoin;
		return str;
	}

	
	

}
