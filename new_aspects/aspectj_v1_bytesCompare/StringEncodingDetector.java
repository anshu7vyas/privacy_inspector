package aspectj;

public class StringEncodingDetector {

	public static String guessEncoding(byte[] bytes) {
    	String DEFAULT_ENCODING = "UTF-8";
    	org.mozilla.universalchardet.UniversalDetector detector =
        	new org.mozilla.universalchardet.UniversalDetector(null);
    	detector.handleData(bytes, 0, bytes.length);
    	detector.dataEnd();
    	String encoding = detector.getDetectedCharset();
    	detector.reset();
    	if (encoding == null) {
        	encoding = DEFAULT_ENCODING;
    	}
    	return encoding;
	}
}