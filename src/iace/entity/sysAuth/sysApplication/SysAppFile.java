package iace.entity.sysAuth.sysApplication;

import java.util.HashSet;
import java.util.Set;

import iace.entity.sysAuth.sysOperation.SysOp;
import iace.entity.sysAuth.sysOperation.SysOpUpload;

public class SysAppFile extends SysApp {
	private SysOpUpload opUpload = new SysOpUpload();
	
	public SysAppFile() {
		super.displayName = "檔案中心";
		super.namespaces.add("/file");
	}

	@Override
	public Set<SysOp> getOperationSet() {
		Set<SysOp> operations = new HashSet<SysOp>();
		operations.add(this.opUpload);
		return operations;
	}

	public SysOpUpload getOpUpload() {
		return opUpload;
	}
	
}
