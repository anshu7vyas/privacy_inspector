package visitor;

import util.Constants;

/**
 * Created by av7 on 7/29/17.
 */
public class ContactInfo implements Visitable{
    
    private byte[] contactBuffer;
    
    public ContactInfo(byte[] contactBuffer) {
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
