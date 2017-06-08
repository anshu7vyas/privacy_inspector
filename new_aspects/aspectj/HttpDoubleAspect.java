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

  private static int index = 0;
  //private static int SIZE = 8;
  private static byte[] circBuffer = new byte[AspectConstants.DOUBLE_BYTE_SIZE];


  Logging logger = Logging.getInstance();


  //private String byteArrString;
  // public final static double ASPECT_LATITUDE = 91.1;//22.719568;
  // public final static double ASPECT_LONGITUDE = 91.1;//75.857727;
  // public final static String ASPECT_IMEI = "424242424242424"; //15 digits
  public byte[] aspLatDBytes = ByteForByte.double2Bytes(AspectConstants.ASPECT_LATITUDE);
  public byte[] aspLongDBytes = ByteForByte.double2Bytes(AspectConstants.ASPECT_LONGITUDE);
  //public byte[] aspIMEI = AspectConstants.str2Btyes(AspectConstants.ASPECT_IMEI);
  public byte[] httpBytes;


  //Double aspects
  @Around("execution(synchronized void org.apache.harmony.luni.internal.net.www.protocol.http.RetryableOutputStream.write(..))")
  public Object adviceWrite(final ProceedingJoinPoint joinPoint) throws Throwable {
      Object arg[] = joinPoint.getArgs();
      StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
       
      httpBytes = (byte[]) arg[0];
      copyToBuffer(httpBytes);

      if (ByteForByte.compareBytes(circBuffer, aspLatDBytes, index, AspectConstants.DOUBLE_BYTE_SIZE) != -1 || ByteForByte.compareBytes(circBuffer, aspLongDBytes, index, AspectConstants.DOUBLE_BYTE_SIZE) != -1) {
        logger.printLog("\n\nViolating security policy. Dummy GPS coordinates have been found at the sink. Error 1", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
      }
      /*if (ByteForByte.compareBytes(circBuffer, aspectIMEI, index, SIZE) != -1) {
        logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
      }*/

      Object obj = joinPoint.proceed();
      return obj;

  }

  public static void copyToBuffer(byte[] arr) {
      for (int i = 0; i <= arr.length; i++) {
        circBuffer[index] = arr[i];
        index = (index+1)%(AspectConstants.DOUBLE_BYTE_SIZE);
      }
  }
}


