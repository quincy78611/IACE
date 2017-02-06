package iace.dao.member;

import java.util.List;

import core.util.PagedList;
import iace.dao.IBaseIaceDao;
import iace.entity.member.Member;
import iace.entity.member.MemberSearchModel;

public interface IMemberDao extends IBaseIaceDao<Member> {
	public boolean isAccountExist(String account);
	
	public Member getByAccount(String account);
	
	public Member getBy(String account, String password);
	
	public PagedList<Member> searchBy(MemberSearchModel arg);
	
	public long queryTotalRecordsCount(MemberSearchModel arg);
	
	public List<String> allEmailList();
}
