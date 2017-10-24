package observer;

/**
 * Interface class for Observer - any object that wishes to be notified when the state of another object changes
 *
 * @author Anshul Vyas
 */
public interface Observer {

    /**
     * Called when a change has occurred in the state of the observable.
     * @param ob
     */
    void update(Object ob);
}
