package observer;

import util.Constants;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class IMEIAspect {

    @Around("execution (String android.telephony.TelephonyManager.getDeviceId(..))")
    public String adviceGetIMEI(final ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        return Constants.ASPECT_IMEI;
    }
}
