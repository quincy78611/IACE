package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpBatchImport;

public class SysAppLiterature extends SysApp {
	private SysOpBatchImport opBatchImport = new SysOpBatchImport();
	
	public SysAppLiterature() {
		super.displayName = "文獻與法規";
		super.namespaces.add("/literature");
	}
	
	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opBatchImport);
		return operations;
	}

	public SysOpBatchImport getOpBatchImport() {
		return opBatchImport;
	}

	
}
