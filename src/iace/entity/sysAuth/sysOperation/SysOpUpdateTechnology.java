package iace.entity.sysAuth.sysOperation;

public class SysOpUpdateTechnology extends SysOp {

	public SysOpUpdateTechnology() {
		super.displayName = "編輯研發成果";
		super.actions.add("updateTechnology");
		super.actions.add("updateTechnologySubmit");
	}

}
