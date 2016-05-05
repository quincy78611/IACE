package iace.dao;

import java.util.List;

import iace.entity.OptionIndustry;

public interface IOptionIndustryDao {
	public List<OptionIndustry> listAll();
	
	public OptionIndustry get(long id);
	
	public void create(OptionIndustry entity);
	
	public void update(OptionIndustry entity);	
	
	public void delete(OptionIndustry entity);
	public void delete(long id);
	
	public boolean isCodeExist(String code);
}
