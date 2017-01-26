package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpDelete;
import iace.entity.sysAuth.sysOperation.SysOpUpdate;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppEPaperSubscriber extends SysApp {
	private SysOpView opView = new SysOpView();
	private SysOpUpdate opUpdate = new SysOpUpdate();
	private SysOpDelete opDelete = new SysOpDelete();
	
	public SysAppEPaperSubscriber() {
		super.displayName = "電子報訂閱者";
		super.namespaces.add("/epaperSubscriber");
	}

	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opView);
		operations.add(this.opUpdate);
		operations.add(this.opDelete);
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
	
	
}
