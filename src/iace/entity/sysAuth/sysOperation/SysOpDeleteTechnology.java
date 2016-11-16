package iace.entity.sysAuth.sysOperation;

public class SysOpDeleteTechnology extends SysOp {

	public SysOpDeleteTechnology() {
		super.displayName = "刪除研發成果";
		super.actions.add("updateTechnologySubmit");	
	}

}
