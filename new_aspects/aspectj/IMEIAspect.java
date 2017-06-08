package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class IMEIAspect {

Logging logger = Logging.getInstance();

public final static String ASPECT_IMEI = "915584987";

@Around("execution (String android.telephony.TelephonyManager.getDeviceId(..))")
    public String adviceGetIMEI(final ProceedingJoinPoint joinPoint) throws Throwable {
        return ASPECT_IMEI;
    }
}