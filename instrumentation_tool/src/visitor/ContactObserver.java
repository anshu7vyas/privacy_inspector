package visitor;

import util.Constants;

/**
 * Created by av7 on 7/29/17.
 */
public class ContactObserver implements Visitable {

    private byte[] contactBuffer;

    public ContactObserver(byte[] contactBuffer) {
        this.contactBuffer = contactBuffer;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public byte[] getContactBuffer() {
        return contactBuffer;
    }
}
