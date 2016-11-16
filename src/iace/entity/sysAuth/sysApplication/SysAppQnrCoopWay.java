package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppQnrCoopWay extends SysApp {
	private SysOpView opView = new SysOpView();
	
	public SysAppQnrCoopWay() {
		super.displayName = "產學合作問卷";
		super.namespaces.add("/qnrCooperateWay");
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
