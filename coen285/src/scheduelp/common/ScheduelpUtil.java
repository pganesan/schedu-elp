package scheduelp.common;

public class ScheduelpUtil {

	public static Integer parseInt(String num) throws ScheduelpException {
		Integer iNum = 0;

		try {
			iNum = Integer.parseInt(num);
		} catch (NumberFormatException nfe) {
			throw new ScheduelpException(nfe.getMessage());
		}

		return iNum;
	}

	public static boolean isNull(String str) {
		boolean isNull = false;

		if (str == null || "".equals(str))
			isNull = true;

		return isNull;
	}
}
