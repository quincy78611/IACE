package iace.entity.sysAuth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

import iace.entity.sysAuth.sysApplication.SysApp;
import iace.entity.sysAuth.sysApplication.SysAppAbout;
import iace.entity.sysAuth.sysApplication.SysAppConsulting;
import iace.entity.sysAuth.sysApplication.SysAppCoopEx;
import iace.entity.sysAuth.sysApplication.SysAppEnterpriseNeed;
import iace.entity.sysAuth.sysApplication.SysAppFaq;
import iace.entity.sysAuth.sysApplication.SysAppIncubationCenter;
import iace.entity.sysAuth.sysApplication.SysAppLiterature;
import iace.entity.sysAuth.sysApplication.SysAppNews;
import iace.entity.sysAuth.sysApplication.SysAppOptionManage;
import iace.entity.sysAuth.sysApplication.SysAppPatent;
import iace.entity.sysAuth.sysApplication.SysAppQnrCoopWay;
import iace.entity.sysAuth.sysApplication.SysAppResearchPlan;
import iace.entity.sysAuth.sysApplication.SysAppSysLog;
import iace.entity.sysAuth.sysApplication.SysAppSysRole;
import iace.entity.sysAuth.sysApplication.SysAppSysUser;
import iace.entity.sysAuth.sysApplication.SysAppTalentedPeople;

public class SysAuth {
	private transient boolean enableAuth = true;
	
	private SysAppOptionManage optionManage = new SysAppOptionManage();
	
	private SysAppQnrCoopWay qnrCoopWay = new SysAppQnrCoopWay();
	private SysAppResearchPlan researchPlan = new SysAppResearchPlan();
	private SysAppPatent patent = new SysAppPatent();
	private SysAppConsulting consulting = new SysAppConsulting();
	private SysAppEnterpriseNeed enterpriseNeed = new SysAppEnterpriseNeed();
	private SysAppCoopEx coopEx = new SysAppCoopEx();
	private SysAppTalentedPeople talentedPeople = new SysAppTalentedPeople();
	private SysAppIncubationCenter incubationCenter = new SysAppIncubationCenter();
	private SysAppLiterature literature = new SysAppLiterature();
	
	private SysAppSysLog sysLog = new SysAppSysLog();
	private SysAppSysUser sysUser = new SysAppSysUser();
	private SysAppSysRole sysRole = new SysAppSysRole();
	
	private SysAppAbout about = new SysAppAbout();
	private SysAppFaq faq = new SysAppFaq();
	private SysAppNews news = new SysAppNews();
	
	public SysAuth() {
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("configs/iace.properties"));
			String enableAuthStr = prop.getProperty("enableAuth");
			this.enableAuth = Boolean.valueOf(enableAuthStr);
		} catch (IOException e) {
			System.err.println("can't read value from config file, set [enableAuth] to true");
			this.enableAuth = true;
		}
	}
	
	private List<SysApp> getSysAppList() {
		List<SysApp> sysApps = new ArrayList<SysApp>();
		sysApps.add(this.optionManage);
		sysApps.add(this.qnrCoopWay);
		sysApps.add(this.researchPlan);
		sysApps.add(this.patent);
		sysApps.add(this.consulting);
		sysApps.add(this.enterpriseNeed);
		sysApps.add(this.coopEx);
		sysApps.add(this.talentedPeople);
		sysApps.add(this.incubationCenter);
		sysApps.add(this.literature);
		sysApps.add(this.sysLog);
		sysApps.add(this.sysUser);
		sysApps.add(this.sysRole);
		sysApps.add(this.about);
		sysApps.add(this.faq);
		sysApps.add(this.news);
		return sysApps;
	}

	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public static SysAuth fromJsonString(String jsonString) {
		Gson gson = new Gson();
		SysAuth auth = gson.fromJson(jsonString, SysAuth.class);
		return auth;
	}
	
	public boolean hasEnableNamespace(String namespace) {
		if (this.enableAuth) {
			for (SysApp sysApp : getSysAppList()) {
				if (sysApp.hasEnableNamespace(namespace)) 
					return true;
			}
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hasEnableNamespaceStartWith(String namespace) {
		if (this.enableAuth) {
			for (SysApp sysApp : getSysAppList()) {
				if (sysApp.hasEnableNamespaceStartWith(namespace)) 
					return true;
			}
			return false;
		} else {
			return true;
		}
	}
	
	public boolean hasAuth(String namespace, String action) {
		if (this.enableAuth) {
			for (SysApp sysApp : getSysAppList()) {
				if (sysApp.hasAuth(namespace, action)) return true;
			}
			return false;
		} else {
			return true;
		}
	}

	public SysAppOptionManage getOptionManage() {
		return optionManage;
	}

	public SysAppQnrCoopWay getQnrCoopWay() {
		return qnrCoopWay;
	}

	public SysAppResearchPlan getResearchPlan() {
		return researchPlan;
	}

	public SysAppPatent getPatent() {
		return patent;
	}

	public SysAppConsulting getConsulting() {
		return consulting;
	}

	public SysAppEnterpriseNeed getEnterpriseNeed() {
		return enterpriseNeed;
	}

	public SysAppCoopEx getCoopEx() {
		return coopEx;
	}

	public SysAppTalentedPeople getTalentedPeople() {
		return talentedPeople;
	}

	public SysAppIncubationCenter getIncubationCenter() {
		return incubationCenter;
	}

	public SysAppLiterature getLiterature() {
		return literature;
	}

	public SysAppSysLog getSysLog() {
		return sysLog;
	}

	public SysAppSysUser getSysUser() {
		return sysUser;
	}

	public SysAppSysRole getSysRole() {
		return sysRole;
	}

	public boolean isEnableAuth() {
		return enableAuth;
	}

	public SysAppAbout getAbout() {
		return about;
	}

	public SysAppFaq getFaq() {
		return faq;
	}

	public SysAppNews getNews() {
		return news;
	}
	
	
}
