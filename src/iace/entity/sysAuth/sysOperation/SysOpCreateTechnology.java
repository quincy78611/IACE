package iace.entity.sysAuth.sysOperation;

public class SysOpCreateTechnology extends SysOp {

	public SysOpCreateTechnology() {
		super.displayName = "新增研發成果";
		super.actions.add("createTechnology");
		super.actions.add("createTechnologySubmit");
	}

}
