package iace.entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;

import core.util.CloseableTool;

public class PatentPicture {
	private XSSFPicture pic;
	private byte[] data;

	public PatentPicture(XSSFPicture pic) {
		this.pic = pic;
		this.data = this.getPicData().getData();
	}
	
	public void save(String path, String fileName) throws IOException {
		FileOutputStream fos = null;
		File f = new File(path, fileName);
		try {
			fos = new FileOutputStream(f);
			fos.write(this.getData());
		} catch (IOException e) {
			throw e;
		} finally {
			CloseableTool.close(fos);
		}
	}

	public XSSFPicture getPic() {
		return pic;
	}

	public XSSFClientAnchor getAnchor() {
		return (XSSFClientAnchor) pic.getAnchor();
	}

	public XSSFPictureData getPicData() {
		return this.pic.getPictureData();
	}
	
	public String getFileExtension() {
		return this.getPicData().suggestFileExtension();
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public short getCol1() {
		return this.getAnchor().getCol1();
	}
	
	public short getCol2() {
		return this.getAnchor().getCol2();
	}

	public int getRow1() {
		return this.getAnchor().getRow1();
	}
	
	public int getRow2() {
		return this.getAnchor().getRow2();
	}
}
