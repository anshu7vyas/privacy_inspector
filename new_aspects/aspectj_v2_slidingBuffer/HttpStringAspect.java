package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Aspect
public class HttpStringAspect {

	SlidingBuffer imeiBuffer;

	Logging logger = Logging.getInstance();

	public byte[] aspLatSBytes= ByteConversions.double2Str2Bytes(AspectConstants.ASPECT_LATITUDE);
	public byte[] aspLongSBytes = ByteConversions.double2Str2Bytes(AspectConstants.ASPECT_LONGITUDE);
	public byte[] mockupIMEI = ByteConversions.str2Bytes(AspectConstants.ASPECT_IMEI);
	public byte[] dumpBytes;

	@Before("execution (* android.app.Activity.onCreate(..))")
	public void beforeOnCreate(JoinPoint joinPoint) {
		imeiBuffer = new byte[AspectConstants.IMEI_BYTE_SIZE];
		//logger.printingLog(joinPoint.getTarget().getClass().toString());
	}

	//String aspects
	@Around("execution(synchronized void org.apache.harmony.luni.internal.net.www.protocol.http.RetryableOutputStream.write(..))")
	public Object adviceWrite(final ProceedingJoinPoint joinPoint) throws Throwable {
		Object arg[] = joinPoint.getArgs();
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	   
		dumpBytes = serializeObject(arg[0]);	//(byte[]) arg[0];

		for (int i = 0; i <= dumpBytes.length; i++) {
            imeiBuffer.add(dumpBytes[i]);
            //System.out.println("imeiBuffer = " + imeiBuffer);
            if (imeiBuffer.isFilled()) {
				byte[] imeiDumpBytes = imeiBuffer.getCircularBuffer();                       //15 digits filled in the circular buffer

				viewEncoding = StringEncodingDetector.guessEncoding(imeiDumpBytes);          //detect encoding
                //System.out.println("detected encoding = " + viewEncoding);
				if (viewEncoding == "UTF-8") {
					//byte[] mockupIMEIbytes = mockupIMEI.getBytes(viewEncoding);

					String transformedDump = ByteConversions.byte2String(imeiDumpBytes);
					//System.out.println("converted byte2string dump = " + convertedDump);
					String mockupTransform = ByteConversions.byte2String(mockupIMEI);
					//System.out.println("converted mockupImei byte2string = " + mockupConversion);

					if (transformedDump.equals(mockupTransform)) {
						logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", arg[0], stacktrace, joinPoint.getTarget().getClass().toString());
						break;
					}
				}
			}
		}

		// if (ByteForByte.compareBytes(circBuffer, aspIMEI, index, AspectConstants.IMEI_BYTE_SIZE) != -1) {
		// 	logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", arg[0], stacktrace, joinPoint.getTarget().getClass().toString());
		// }

		Object obj = joinPoint.proceed();
		return obj;
	}

	/**
	 * Helper function -test the method
	 */
	public static byte[] serializeObject(Object obj) throws IOException
	{
		ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bytesOut);
		oos.writeObject(obj);
		oos.flush();
		byte[] bytes = bytesOut.toByteArray();
		bytesOut.close();
		oos.close();
		return bytes;
	}

}


