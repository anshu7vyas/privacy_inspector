package observer;

/**
 * Created by av7 on 7/29/17.
 */
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
