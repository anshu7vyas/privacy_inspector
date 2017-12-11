package visitor;

/**
 * Interface for Visitable - objects that can be visited to run the analysis by the concrete implementation
 * of our DataInspector
 *
 * @author Anshul Vyas
 */
public interface Visitable {

    /**
     * accepts a particular visitor
     *
     * @param visitor
     */
    void accept(Visitor visitor);

    /**
     * Fills the buffer byte per byte given the Visitable
     * @param object
     */
    void updateBuffer(Object object);
}
