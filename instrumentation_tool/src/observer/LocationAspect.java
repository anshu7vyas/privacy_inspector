package observer;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

import util.Constants;

/**
 * Instrumentation of Android API which returns the GPS coordinates
 *
 * @author Anshul Vyas
 */
@Aspect
public class LocationAspect {

    /**
     * AspectJ advice around the API android.location.Location.getLatitude(), a.k.a "the source" where we insert
     * the mock-up data.
     *
     * @param joinPoint
     * @return mock-up Latitude coordinate defined in the Constants.java
     * @throws Throwable
     */
    @Around("execution (double android.location.Location.getLatitude(..))")
    public double adviceGetLatitude(final ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        return Constants.ASPECT_LATITUDE;
    }

    /**
     * AspectJ advice around the API android.location.Location.getLongitude(), a.k.a "the source" where we insert
     * the mock-up data.
     *
     * @param joinPoint
     * @return mock-up Longitude coordinate defined in the Constants.java
     * @throws Throwable
     */
    @Around("execution (double android.location.Location.getLongitude(..))")
    public double adviceGetLongitude(final ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        return Constants.ASPECT_LONGITUDE;
    }
}
