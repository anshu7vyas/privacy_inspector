package observer;

/**
 * Created by av7 on 7/29/17.
 */
public interface Observable {
    void registerObserver(Observer newObserver);
    void unregisterObserver(Observer deleteObserver);
    void notifyObservers();
}
