package observer;



/**
 * Interface class for Observable - any object whose state may be of interest, and in whom another object may register an interest
 *
 * @author Anshul Vyas
 */
public interface Observable {

    /**
     * Adds an observer to the internal list of visitableList.
     *
     * @param newObserver
     */
    void registerObserver(Observer newObserver);

    /**
     * Deletes an observer from the internal list of visitableList.
     *
     * @param deleteObserver
     */
    void unregisterObserver(Observer deleteObserver);

    /**
     * Checks the internal flag to see if the observable has changed state and notifies all visitableList.
     *
     * @param object
     */
    void notifyObservers(Object object);

}
