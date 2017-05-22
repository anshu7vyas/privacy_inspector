package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class HttpStringAspect {

  private static int index = 0;
  private static byte[] circBuffer = new byte[AspectConstants.IMEI_BYTE_SIZE];

  Logging logger = Logging.getInstance();

  // private boolean http_flag = false;
  // private boolean location_flag = false;

  public byte[] aspLatSBytes= ByteForByte.double2Str2Bytes(AspectConstants.ASPECT_LATITUDE);
  public byte[] aspLongSBytes = ByteForByte.double2Str2Bytes(AspectConstants.ASPECT_LONGITUDE);
  public byte[] aspIMEI = ByteForByte.str2Bytes(AspectConstants.ASPECT_IMEI);
  public byte[] httpBytes;


  //initial aspects screenshots
  /*  @Before("execution (* java.net.HttpURLConnection.getOutputStream(..))")
      public void adviceHttpURLConnection(JoinPoint joinPoint) {
          http_flag = true;
          Object arg[] = joinPoint.getArgs();
          System.out.printf("Http connection expected:   '%s'%n", joinPoint);
          StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
          //System.out.println("HttpFlag_Raised" + http_flag);
  }*/

  //String aspects
  @Around("execution(synchronized void org.apache.harmony.luni.internal.net.www.protocol.http.RetryableOutputStream.write(..))")
  public Object adviceWrite(final ProceedingJoinPoint joinPoint) throws Throwable {
      Object arg[] = joinPoint.getArgs();
      StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
       
      httpBytes = (byte[]) arg[0];
      copy2StrBuffer(httpBytes);

      // if (ByteForByte.compareBytes(circBuffer, aspLatSBytes, index, /**/) != -1 || ByteForByte.compareBytes(circBuffer, aspLatSBytes, index, AspectConstants.STRING_BYTE_SIZE) != -1) {
      //   logger.printLog("\n\nViolating security policy. Dummy GPS coordinates have been found at the sink. Error 1", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
      // }
      if (ByteForByte.compareBytes(circBuffer, aspIMEI, index, AspectConstants.IMEI_BYTE_SIZE) != -1) {
        logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
      }

      Object obj = joinPoint.proceed();
      return obj;

  }

  public static void copy2StrBuffer(byte[] arr) {
      for (int i = 0; i <= arr.length; i++) {
        circBuffer[index] = arr[i];
        index = (index+1)%(AspectConstants.IMEI_BYTE_SIZE);
      }
   }
}


