package aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

import android.location.Location;

@Aspect
public class LocationAspect {

	Logging logger = Logging.getInstance();

	private boolean http_flag = false;
	private boolean location_flag = false;
	// private double lat = 0.0;
	// private double longi = 0.0;
	Location location = new Location("aspectProvider");

	@Around("execution (double android.location.Location.getLatitude(..))")
	    public double adviceGetLatitude(final ProceedingJoinPoint joinPoint) throws Throwable {
	        return AspectConstants.ASPECT_LATITUDE;
	    }

	@Around("execution (double android.location.Location.getLongitude(..))")
	    public double adviceGetLongitude(final ProceedingJoinPoint joinPoint) throws Throwable {
	        return AspectConstants.ASPECT_LONGITUDE;
	    }

	@Around("execution (* android.location.LocationManager.getLastKnownLocation(..))")
	    public Object adviceLocationManager(final ProceedingJoinPoint joinPoint) throws Throwable {
	        System.out.printf("Method invocation Detected:   '%s'%n", joinPoint);
	        location_flag = true;
	        System.out.println("LocationFlag_Raised" + location_flag);
	        
	        Location location = (Location) joinPoint.proceed();
	        if (location instanceof Location) {
	            location.setLatitude(AspectConstants.ASPECT_LATITUDE);
	            location.setLongitude(AspectConstants.ASPECT_LONGITUDE);
	        }
	        return location;
	    }

	    //Location Basic Aspects
	 // @Before("execution (* android.location.LocationManager.requestLocationUpdates(..))")
	 //    public void adviceEnter(JoinPoint joinPoint) {
	 //        System.out.printf("Method invocation Detected:   '%s'%n", joinPoint);
	 //    }

	// @Before("execution (* android.location.LocationListener.onLocationChanged(..))")
	//     public void adviceLocationListener(JoinPoint joinPoint) {
	//         System.out.printf("Method invocation Detected:   '%s'%n", joinPoint);
	//     }

	// @After("execution (* android.location.LocationManager.removeUpdates(..))")
	//     public void adviceExit(JoinPoint joinPoint) {
	//         System.out.printf("Exit method:   '%s'%n", joinPoint);
	//     }
}
