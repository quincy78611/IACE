package core.util;

public class StringUtil {

	public static String filterHtml(String htmlString) {
		return htmlString.replaceAll("\\<[^>]*>","").replaceAll("&.*?;","");
	}

}
