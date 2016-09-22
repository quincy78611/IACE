package core.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.xhtmlrenderer.util.ImageUtil;

public class ThumbnailUtil {

	public static byte[] resize(byte[] byteArrayImg, int width, int height, boolean keepRatio) throws IOException {
		BufferedImage orgImage = ImageIO.read(new ByteArrayInputStream(byteArrayImg));
		if (keepRatio) {
			float widthRatio = (float)width / (float)orgImage.getWidth() ;
			float heightRatio = (float)height / (float)orgImage.getHeight();
			float ratio = Math.min(widthRatio, heightRatio);
			width = (int) (orgImage.getWidth() * ratio);
			height = (int) (orgImage.getHeight() * ratio);
		}
		
		BufferedImage imageBuff = ImageUtil.getScaledInstance(orgImage, width, height);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(imageBuff, "jpg", buffer);
		return buffer.toByteArray();
	}
}
