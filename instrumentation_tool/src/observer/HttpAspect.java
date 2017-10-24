package observer;

import util.SlidingBuffer;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Instrumentation of write() API in the Http Output Stream, a.k.a "the sink" where we inspect for the mock-up data
 *
 * @author Anshul Vyas
 */
@Aspect
public class HttpAspect implements Observable {

    /* Array for storing the bytes coming via Output Stream */
    public byte[] dumpBytes;

    /* Arraylist of all observers */
    List<Observer> observers = new ArrayList<Observer>();

    public HttpAspect() {
        registerObserver(BufferManager.getInstance());
    }

    @Override
    public void registerObserver(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregisterObserver(Observer deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);
        observers.remove(observerIndex);
    }

    @Override
    public void notifyObservers(Object object){
        for(Observer observer: observers) {
            observer.update(object);
        }
    }

    /**
     * AspectJ advice around the API org.apache.harmony.luni.internal.net.www.protocol.http.RetryableOutputStream.write()
     * The method where we run different analysis on the bytes we are getting from the Output stream
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(synchronized void org.apache.harmony.luni.internal.net.www.protocol.http.RetryableOutputStream.write(..))")
    public Object aroundWrite(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object o = joinPoint.getThis();
        Object arg[] = joinPoint.getArgs();

        dumpBytes = (byte[]) arg[0];

        for (int i = 0; i < dumpBytes.length; i++) {
            SlidingBuffer.getInstance(o).add(dumpBytes[i]);
            notifyObservers(o);
        }

        Object obj = joinPoint.proceed();
        return obj;
    }

}


