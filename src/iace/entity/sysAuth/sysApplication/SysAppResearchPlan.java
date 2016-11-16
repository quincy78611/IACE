package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpBatchImport;
import iace.entity.sysAuth.sysOperation.SysOpCreate;
import iace.entity.sysAuth.sysOperation.SysOpCreateTechnology;
import iace.entity.sysAuth.sysOperation.SysOpDeleteTechnology;
import iace.entity.sysAuth.sysOperation.SysOpUpdate;
import iace.entity.sysAuth.sysOperation.SysOpUpdateTechnology;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppResearchPlan extends SysApp {
	private SysOpView opView = new SysOpView();
	private SysOpCreate opCreate = new SysOpCreate();
	private SysOpUpdate opUpdate = new SysOpUpdate();
	private SysOpCreateTechnology opCreateTechnology = new SysOpCreateTechnology();
	private SysOpUpdateTechnology opUpdateTechnology = new SysOpUpdateTechnology();
	private SysOpDeleteTechnology opDeleteTechnology = new SysOpDeleteTechnology();
	private SysOpBatchImport opBatchImport = new SysOpBatchImport();
	
	public SysAppResearchPlan() {
		super.displayName = "研究計畫";
		super.namespaces.add("/researchPlan");
	}
	
	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opView);
		operations.add(this.opCreate);
		operations.add(this.opUpdate);
		operations.add(this.opCreateTechnology);
		operations.add(this.opUpdateTechnology);
		operations.add(this.opDeleteTechnology);
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

	public SysOpCreateTechnology getOpCreateTechnology() {
		return opCreateTechnology;
	}

	public SysOpUpdateTechnology getOpUpdateTechnology() {
		return opUpdateTechnology;
	}

	public SysOpDeleteTechnology getOpDeleteTechnology() {
		return opDeleteTechnology;
	}

	public SysOpBatchImport getOpBatchImport() {
		return opBatchImport;
	}

}
