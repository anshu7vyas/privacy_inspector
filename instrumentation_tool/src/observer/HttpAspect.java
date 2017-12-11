package observer;

//import java.nio.charset.StandardCharsets;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * Instrumentation of write() API in the Http Output Stream, a.k.a "the sink" where we inspect for the mock-up data
 *
 * @author Anshul Vyas
 */
@Aspect
public class HttpAspect implements Observable {

    /**
     *  Array for storing the bytes coming via Output Stream
     */
    public byte[] dumpBytes;

    private BufferManager bufferManager;


    @Override
    public void registerObserver(Object object) {
        bufferManager = BufferManager.getInstance(object);
    }

    @Override
    public void unregisterObserver(Observer deleteObserver) {
    }

    @Override
    public void notifyObservers(Object object){
        bufferManager.update(object);
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

        registerObserver(o);

        for (int i = 0; i < dumpBytes.length; i++) {
            bufferManager.globalBuffer.add(dumpBytes[i]);
            notifyObservers(o);
        }

        return joinPoint.proceed();
    }

//    public void aroundWrite(byte[] buffer, Object o) {
//
//
//        registerObserver(o);
//
//        for (int i = 0; i < buffer.length; i++) {
//            bufferManager.globalBuffer.add(buffer[i]);
//            notifyObservers(o);
//        }
//    }
//
//    public static void main(String[] args) {
//        HttpAspect httpAspect = new HttpAspect();
//
//        Object obj = 0x9999;
//        Object obj1 = 0x9911;
//        byte[] buffer = "424242424242424 34.93281 76.76571 Neanderthals Supersapiens 742-742-4242".getBytes(StandardCharsets.UTF_8);
//        byte[] buf2 = "37.794916 -122.393117".getBytes(StandardCharsets.UTF_8);
//
//        //httpAspect.registerObserver(BufferManager.getInstance());
//        httpAspect.aroundWrite(buffer, obj);
//        httpAspect.aroundWrite(buf2, obj1);
//    }

}



