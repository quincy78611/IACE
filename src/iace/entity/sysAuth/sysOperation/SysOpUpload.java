package iace.entity.sysAuth.sysOperation;

public class SysOpUpload extends SysOp {

	public SysOpUpload() {
		super.displayName = "上傳";
		super.actions.add("upload");
		super.actions.add("uploadFile");
		super.actions.add("ckEditorUploadFile");
	}

}
