package visitor;

import util.Constants;

/**
 * Visitable class for Contacts - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class ContactObserver implements Visitable {

    private byte[] contactBuffer;

    public ContactObserver(byte[] contactBuffer) {
        this.contactBuffer = contactBuffer;
    }

    /**
     * Accepts the visitor - DataInspector
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    /**
     * @return contactBuffer
     */
    public byte[] getContactBuffer() {
        return contactBuffer;
    }
}
