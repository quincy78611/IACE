package iace.entity.sysAuth.sysOperation;

public class SysOpExportTalentedPeopleEmail extends SysOp {

	public SysOpExportTalentedPeopleEmail() {
		super.displayName = "匯出產學人才Email列表";
		super.actions.add("exportEmail");
		super.actions.add("exportAllEmail");
		super.actions.add("exportNotAgreePDPLYetEmail");
		super.actions.add("exportPDPLList");
		super.actions.add("exportPDPLSummary");
	}

}
