package iace.entity.option;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "OPT_DOMAIN")
public class OptionDomain extends BaseOption {

	private static final long serialVersionUID = -9084518401945981749L;
	
	private List<OptionGrbDomain> subDomainList = new ArrayList<OptionGrbDomain>();

	@OneToMany(mappedBy = "mainDomain", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<OptionGrbDomain> getSubDomainList() {
		return subDomainList;
	}

	public void setSubDomainList(List<OptionGrbDomain> subDomainList) {
		this.subDomainList = subDomainList;
	}
	
	

}
