package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class HttpDoubleAspect {

	/**
	 * EPSILON = 0.001
	 */
    public final static double EPSILON = 0.001;
	SlidingBuffer longitudeBuffer;
	SlidingBuffer latitudeBuffer;


	Logging logger = Logging.getInstance();

	public byte[] mockupLatDBytes = ByteConversions.double2Bytes(AspectConstants.ASPECT_LATITUDE);
	public byte[] mockupLongDBytes = ByteConversions.double2Bytes(AspectConstants.ASPECT_LONGITUDE);
	public byte[] dumpBytes;

	@Before("execution (* android.app.Activity.onCreate(..))")
	public void beforeOnCreate(JoinPoint joinPoint) {
		longitudeBuffer = new SlidingBuffer(AspectConstants.DOUBLE_BYTE_SIZE);
		latitudeBuffer = new SlidingBuffer(AspectConstants.DOUBLE_BYTE_SIZE);
		//logger.printingLog(joinPoint.getTarget().getClass().toString());
	}

	//Double aspects
	@Around("execution(synchronized void org.apache.harmony.luni.internal.net.www.protocol.http.RetryableOutputStream.write(..))")
	public Object aroundWrite(final ProceedingJoinPoint joinPoint) throws Throwable {
		Object arg[] = joinPoint.getArgs();
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();

		dumpBytes = (byte[]) arg[0];

		for (int i = 0; i < dumpBytes.length; i++) {
            longitudeBuffer.add(dumpBytes[i]);
            if(longitudeBuffer.isFilled()) {
                byte[] longitudeDumpBytes = longitudeBuffer.getCircularBuffer();
                double longitudeDumpBytesDouble = ByteConversions.bytes2Double(longitudeDumpBytes);

                if (almostEqualDouble(longitudeDumpBytesDouble, AspectConstants.ASPECT_LONGITUDE)) {
                	logger.printLog("\n\nViolating security policy. Dummy Longitude has been found at the sink. Error 1.1", arg[0], stacktrace, joinPoint.getTarget().getClass().toString());
                }
            }
        }

        for (int i = 0; i < dumpBytes.length; i++) {
            latitudeBuffer.add(dumpBytes[i]);
            if(latitudeBuffer.isFilled()) {
                byte[] latitudeDumpBytes = longitudeBuffer.getCircularBuffer();
                double latitudeDumpBytesDouble = ByteConversions.bytes2Double(latitudeDumpBytes);

                if (almostEqualDouble(latitudeDumpBytesDouble, AspectConstants.ASPECT_LATITUDE)) {
                	logger.printLog("\n\nViolating security policy. Dummy Latitude has been found at the sink. Error 1.1", arg[0], stacktrace, joinPoint.getTarget().getClass().toString());
                }
            }
        }

		// if (ByteForByte.compareBytes(circBuffer, aspLatDBytes, index, AspectConstants.DOUBLE_BYTE_SIZE) != -1 || ByteForByte.compareBytes(circBuffer, aspLongDBytes, index, AspectConstants.DOUBLE_BYTE_SIZE) != -1) {
		// logger.printLog("\n\nViolating security policy. Dummy GPS coordinates have been found at the sink. Error 1", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
		// }
		/*if (ByteForByte.compareBytes(circBuffer, aspectIMEI, index, SIZE) != -1) {
		logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
		}*/

		Object obj = joinPoint.proceed();
		return obj;
	}

	public static boolean almostEqualDouble(double a, double b) {
		if (a == b) return true;
		return Math.abs(a - b) <= EPSILON * Math.max(Math.abs(a), Math.abs(b));
    }
}


