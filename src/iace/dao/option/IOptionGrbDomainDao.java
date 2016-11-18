package iace.dao.option;

import java.util.List;
import java.util.Map;

import iace.entity.option.OptionGrbDomain;

public interface IOptionGrbDomainDao extends IOptionDao<OptionGrbDomain> {
	
	public List<OptionGrbDomain> listForResearchPlan();
	
	public List<OptionGrbDomain> listForTalentedPeople();
	
	public Map<String, OptionGrbDomain> mapForTalentedPeople();
}
