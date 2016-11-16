package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppSysLog extends SysApp {
	private SysOpView opView = new SysOpView();
	
	public SysAppSysLog() {
		super.displayName = "系統LOG";
		super.namespaces.add("/sysLog");
	}

	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opView);
		return operations;
	}
	
	public SysOpView getOpView() {
		return opView;
	}

	
}
