package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpBatchImport;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppIncubationCenter extends SysApp {
	private SysOpView opView = new SysOpView();
	private SysOpBatchImport opBatchImport = new SysOpBatchImport(); 
	
	public SysAppIncubationCenter() {
		super.displayName = "育成中心";
		super.namespaces.add("/incubationCenter");
	}
	
	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opView);
		operations.add(this.opBatchImport);
		return operations;
	}

	public SysOpView getOpView() {
		return opView;
	}

	public SysOpBatchImport getOpBatchImport() {
		return opBatchImport;
	}

}
