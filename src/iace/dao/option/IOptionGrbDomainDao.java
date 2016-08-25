package iace.dao.option;

import java.util.List;

import iace.entity.option.OptionGrbDomain;

public interface IOptionGrbDomainDao extends IOptionDao<OptionGrbDomain> {
	
	public List<OptionGrbDomain> listForResearchPlan();
}
