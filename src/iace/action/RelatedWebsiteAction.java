package iace.action;

import java.io.ByteArrayInputStream;

import iace.service.ServiceFactory;
import iace.service.relatedWebsite.RelatedWebsiteService;

public class RelatedWebsiteAction extends BaseIaceAction {
	private static final long serialVersionUID = -776812147682980154L;

	private RelatedWebsiteService relatedWebsiteService = ServiceFactory.getRelatedWebsiteService();

	private String fileName;
	private ByteArrayInputStream imageStream;

	public RelatedWebsiteAction() {
		super.setTitle("相關網站");
	}

	public String getImageByName() {
		try {
			this.imageStream = this.relatedWebsiteService.getImageByName(this.fileName);
			return SUCCESS;
		} catch (Exception e) {
			super.showExceptionToPage(e);
			return ERROR;
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

}
