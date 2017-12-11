package visitor;

import observer.BufferManager;
import util.Constants;

/**
 * Visitable class for Contacts - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class ContactObserver implements Visitable {

    private byte[] contactBuffer;

    private static ContactObserver contactObserver = null;

    /**
     * Ensures single instance for ContactObserver
     * @return instance
     */
    public static ContactObserver getInstance() {
        if (contactObserver == null) {
            contactObserver = new ContactObserver();
        }
        return contactObserver;
    }

    /**
     * Assigning the buffer with the number of bytes required
     */
    private ContactObserver() {
        contactBuffer = new byte[Constants.CONTACT_INFO_BYTE_SIZE];
    }

    /**
     * Fills the buffer byte per byte
     * @param object
     */
    @Override
    public void updateBuffer(Object object) {
        BufferManager.getInstance(object).globalBuffer.fillBuffer(contactBuffer);
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
