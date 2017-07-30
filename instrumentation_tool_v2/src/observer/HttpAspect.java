package observer;

import util.SlidingBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by av7 on 7/29/17.
 */
public class HttpAspect implements Observable {
    List<Observer> observers = new ArrayList<Observer>();
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(){
        for(Observer observer: observers) {
            observer.update();
        }
    }

    public Object aroundWrite(final ProceedingJoinPoint joinPoint) throws Throwable {
        Object arg[] = joinPoint.getArgs();

        dumpBytes = (byte[]) arg[0]; 	//serializeObject(arg[0]);

        for (int i = 0; i < dumpBytes.length; i++) {
            SlidingBuffer.getInstance().add(dumpBytes[i]);
            notifyObservers();
        }
     Object obj = joinPoint.proceed();
        return obj;
    }

}
