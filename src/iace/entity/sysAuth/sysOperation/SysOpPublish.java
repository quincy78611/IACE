package iace.entity.sysAuth.sysOperation;

public class SysOpPublish extends SysOp {

	public SysOpPublish() {
		super.displayName = "發佈";
		super.actions.add("publish");
		super.actions.add("sendTestEmail");
	}

}
