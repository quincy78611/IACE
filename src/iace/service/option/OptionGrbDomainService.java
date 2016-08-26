package iace.service.option;

import java.util.List;

import iace.dao.option.IOptionGrbDomainDao;
import iace.entity.option.OptionGrbDomain;

public class OptionGrbDomainService extends BaseOptionService<OptionGrbDomain> {
	
	private IOptionGrbDomainDao dao;
	
	public OptionGrbDomainService(IOptionGrbDomainDao dao) {
		super(dao, OptionGrbDomain.class);
		this.dao = dao;
	}

	public List<OptionGrbDomain> listForResearchPlan() {
		return this.dao.listForResearchPlan();
	}
	
	public List<OptionGrbDomain> listForTalentedPeople(long optionDomainId) {
		return this.dao.listForTalentedPeople(optionDomainId);
	}
}
