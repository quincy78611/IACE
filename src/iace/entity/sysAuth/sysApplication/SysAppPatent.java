package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpBatchImport;
import iace.entity.sysAuth.sysOperation.SysOpCreate;
import iace.entity.sysAuth.sysOperation.SysOpDelete;
import iace.entity.sysAuth.sysOperation.SysOpUpdate;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppPatent extends SysApp {
	private SysOpView opView = new SysOpView();
	private SysOpCreate opCreate = new SysOpCreate();
	private SysOpUpdate opUpdate = new SysOpUpdate();
	private SysOpDelete opDelete = new SysOpDelete();
	private SysOpBatchImport opBatchImport = new SysOpBatchImport();
	
	public SysAppPatent() {
		super.displayName = "專利";
		super.namespaces.add("/patent");
	}
	
	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opView);
		operations.add(this.opCreate);
		operations.add(this.opUpdate);
		operations.add(this.opDelete);
		operations.add(this.opBatchImport);
		return operations;
	}

	public SysOpView getOpView() {
		return opView;
	}

	public SysOpCreate getOpCreate() {
		return opCreate;
	}

	public SysOpUpdate getOpUpdate() {
		return opUpdate;
	}

	public SysOpDelete getOpDelete() {
		return opDelete;
	}

	public SysOpBatchImport getOpBatchImport() {
		return opBatchImport;
	}
}
