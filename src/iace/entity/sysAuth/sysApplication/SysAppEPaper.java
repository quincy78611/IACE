package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpDelete;
import iace.entity.sysAuth.sysOperation.SysOpPublish;
import iace.entity.sysAuth.sysOperation.SysOpUpdate;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppEPaper extends SysApp {
	private SysOpView opView = new SysOpView();
	private SysOpUpdate opUpdate = new SysOpUpdate();
	private SysOpDelete opDelete = new SysOpDelete();
	private SysOpPublish opPublish = new SysOpPublish();
	
	public SysAppEPaper() {
		super.displayName = "電子報";
		super.namespaces.add("/epaper");	}

	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opView);
		operations.add(this.opUpdate);
		operations.add(this.opDelete);
		operations.add(this.opPublish);
		return operations;
	}

	public SysOpView getOpView() {
		return opView;
	}

	public SysOpUpdate getOpUpdate() {
		return opUpdate;
	}

	public SysOpDelete getOpDelete() {
		return opDelete;
	}

	public SysOpPublish getOpPublish() {
		return opPublish;
	}

	
}