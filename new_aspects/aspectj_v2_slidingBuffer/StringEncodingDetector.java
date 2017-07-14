package aspectj;

public class StringEncodingDetector {

	public static String suggestEncoding(byte[] bytes) {
        final CharsetDetector cd = new CharsetDetector();
        cd.setText(bytes);

        final CharsetMatch charsetMatch = cd.detect();
        final String charSet = charsetMatch.getName();

        int confidence = charsetMatch.getConfidence();
        System.out.println("CharsetMatch: " + charSet + " " + confidence + "% confidence");
        return charSet;
    }
}