package visitor;


import util.Constants;
import util.SlidingBuffer;

/**
 * Visitable class for Contacts - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class ContactObserver implements Visitable {

    private byte[] contactBuffer;

    private static ContactObserver contactObserver = null;

    public static ContactObserver getInstance(Object object) {
        if (contactObserver == null) {
            contactObserver = new ContactObserver(object);
        }
        return contactObserver;
    }

    private ContactObserver(Object object) {
        contactBuffer = new byte[Constants.CONTACT_INFO_BYTE_SIZE];
    }


    public void updateBuffer(Object object) {
        SlidingBuffer.getInstance(object).fillBuffer(contactBuffer);
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
