package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class HttpAspect {

Logging logger = Logging.getInstance();

private boolean http_flag = false;
private boolean location_flag = false;
private String byteArrString;
public final static double ASPECT_LATITUDE = 91.1;//22.719568;
public final static double ASPECT_LONGITUDE = 91.1;//75.857727;
public final static String ASPECT_IMEI = "915584987";
public byte[] aspectLatitude = BytesCompare.doubleToByteArray(ASPECT_LATITUDE);
public byte[] aspectLongitude = BytesCompare.doubleToByteArray(ASPECT_LONGITUDE);
public byte[] aspectIMEI = ASPECT_IMEI.getBytes();
public byte[] httpBytes;


//initial aspects screenshots
@Before("execution (* java.net.HttpURLConnection.getOutputStream(..))")
    public void adviceHttpURLConnection(JoinPoint joinPoint) {
        http_flag = true;
        Object arg[] = joinPoint.getArgs();
        System.out.printf("Http connection expected:   '%s'%n", joinPoint);
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        
        //System.out.println("HttpFlag_Raised" + http_flag);
    }

//final aspects
   @Around("execution(synchronized void org.apache.harmony.luni.internal.net.www.protocol.http.RetryableOutputStream.write(..))")
   public Object adviceWrite(final ProceedingJoinPoint joinPoint) throws Throwable {
       Object arg[] = joinPoint.getArgs();
       StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
       
       httpBytes = (byte[]) arg[0];

       if (BytesCompare.compareBytes(httpBytes, aspectLatitude) != -1 && BytesCompare.compareBytes(httpBytes, aspectLongitude) != -1) {
        logger.printLog("\n\nViolating security policy. Dummy GPS coordinates have been found at the sink. Error 1", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
       }
       if (BytesCompare.compareBytes(httpBytes, aspectIMEI) != -1) {
        logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
       }


       /*byteArrString = new String((byte[]) arg[0]);
       if (byteArrString.contains(Double.toString(ASPECT_LATITUDE)) && byteArrString.contains(Double.toString(ASPECT_LONGITUDE))) {
        logger.printLog("\n\nViolating security policy. Dummy GPS coordinates have been found at the sink. Error 1", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());         
        //logger.insertToErrorHashMap(arg,0,"Violating security policy. Dummy GPS coordinates have been found at the sink. Error 1");
        //logger.printingLog(joinPoint.getTarget().getClass().toString());    
        //System.out.printf("%n***************Aspect coordinates confirmed ************=> %s%n", byteArrString); 
       } 
       if (byteArrString.contains(ASPECT_IMEI)) {
        logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", arg[0],stacktrace, joinPoint.getTarget().getClass().toString());
       }*/
       //System.out.printf("%n=> %s%n", byteArrString);
       Object obj = joinPoint.proceed();
       //System.out.printf("Exiting method %s%n", joinPoint);
       return obj;

   }
}


