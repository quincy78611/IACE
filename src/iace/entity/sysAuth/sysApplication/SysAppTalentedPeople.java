package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpBatchImport;
import iace.entity.sysAuth.sysOperation.SysOpCreate;
import iace.entity.sysAuth.sysOperation.SysOpDelete;
import iace.entity.sysAuth.sysOperation.SysOpExportTalentedPeopleEmail;
import iace.entity.sysAuth.sysOperation.SysOpUpdate;
import iace.entity.sysAuth.sysOperation.SysOpView;

public class SysAppTalentedPeople extends SysApp {
	private SysOpView opView = new SysOpView();
	private SysOpCreate opCreate = new SysOpCreate();
	private SysOpUpdate opUpdate = new SysOpUpdate();
	private SysOpDelete opDelete = new SysOpDelete();
	private SysOpBatchImport opBatchImport = new SysOpBatchImport(); 
	private SysOpExportTalentedPeopleEmail opExportEmail = new SysOpExportTalentedPeopleEmail();
	
	public SysAppTalentedPeople() {
		super.displayName = "產學人才資料";
		super.namespaces.add("/talentedPeople");
		super.namespaces.add("/talentedPeopleRdResult");
		super.namespaces.add("/talentedPeopleTransferCase");
		super.namespaces.add("/talentedPeopleMainProject");
	}
	
	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opView);
		operations.add(this.opCreate);
		operations.add(this.opUpdate);
		operations.add(this.opDelete);
		operations.add(this.opBatchImport);
		operations.add(this.opExportEmail);
		return operations;
	}

	public SysOpView getOpView() {
		return opView;
	}
	
	public SysOpCreate getOpCreate() {
		return opCreate;
	}

	public void setOpCreate(SysOpCreate opCreate) {
		this.opCreate = opCreate;
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

	public SysOpExportTalentedPeopleEmail getOpExportEmail() {
		return opExportEmail;
	}

}
