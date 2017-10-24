package observer;

import util.Constants;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Instrumentation of Android API which returns the IMEI number
 *
 * @author Anshul Vyas
 */
@Aspect
public class IMEIAspect {

    /**
     * AspectJ advice around the API android.telephony.TelephonyManager.getDeviceId(), a.k.a "the source" where we insert
     * the mock-up data.
     *
     * @param joinPoint
     * @return mock-up IMEI defined in the Constants.java
     * @throws Throwable
     */
    @Around("execution (String android.telephony.TelephonyManager.getDeviceId(..))")
    public String adviceGetIMEI(final ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        return Constants.ASPECT_IMEI;
    }
}
