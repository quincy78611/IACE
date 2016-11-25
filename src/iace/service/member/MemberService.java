package iace.service.member;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import core.util.PagedList;
import iace.dao.member.IMemberDao;
import iace.dao.option.IOptionDao;
import iace.entity.member.Member;
import iace.entity.member.MemberSearchModel;
import iace.entity.option.OptionDomain;
import iace.entity.option.OptionIndustry;
import iace.service.BaseIaceService;

public class MemberService extends BaseIaceService<Member> {
	private IMemberDao memberDao;
	private IOptionDao<OptionIndustry> optIndustryDao;
	private IOptionDao<OptionDomain> optDomainDao;
	
	public MemberService(IMemberDao dao, IOptionDao<OptionIndustry> optIndustryDao, IOptionDao<OptionDomain> optDomainDao) {
		super(dao);
		this.memberDao = dao;
		this.optIndustryDao = optIndustryDao;
		this.optDomainDao = optDomainDao;
	}
	
	public boolean isAccountExist(String account) {
		return this.memberDao.isAccountExist(account);
	}
	
	public Member getBy(String account, String password) {
		return this.memberDao.getBy(account, password);
	}

	public PagedList<Member> searchBy(MemberSearchModel arg) {
		return this.memberDao.searchBy(arg);
	}

	@Override
	public void create(Member entity) throws IOException, SQLException {
		setOptIndustryList(entity);
		setOptDomain(entity);
		super.create(entity);
	}

	@Override
	public void update(Member entity) throws IOException, SQLException {
		setOptIndustryList(entity);
		setOptDomain(entity);
		super.update(entity);
	}
	
	private void setOptIndustryList(Member entity) {
		ArrayList<OptionIndustry> optList = new ArrayList<OptionIndustry>();
		for (long id : entity.getOptIndustryIdList()) {
			OptionIndustry opt = this.optIndustryDao.get(id);
			optList.add(opt);
		}
		entity.setOptIndustryList(optList);
	}
	
	private void setOptDomain(Member entity) {
		OptionDomain opt = this.optDomainDao.get(entity.getOptDomain().getId());
		entity.setOptDomain(opt);
	}
}
