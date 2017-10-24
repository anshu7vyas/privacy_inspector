package visitor;

/**
 * Interface for visitor pattern - Allows for one or more operation to be applied to a set of objects at runtime
 * (in our case - IMEI, Contacts, Geolocation),
 * decoupling the operations from the object structure.
 *
 * "visitor" base class with a visit() method for every "analysis" type
 * arguments are the elements being visited
 *
 * @author Anshul Vyas
 */
public interface Visitor {

    /**
     * visits the IMEIObserver element
     * @param imeiObserver
     */
    void visit(IMEIObserver imeiObserver);

    /**
     * visits the ContactObserver element
     * @param contactObserver
     */
    void visit(ContactObserver contactObserver);

    /**
     * visits the LocationObserver element
     * @param locationObserver
     */
    void visit(LocationObserver locationObserver);
}
