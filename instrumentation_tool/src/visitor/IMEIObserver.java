package visitor;

/**
 * Created by av7 on 7/29/17.
 */
public class IMEIObserver implements Visitable {

    private byte[] imeiBuffer;

    public IMEIObserver(byte[] imeiBuffer) {
        this.imeiBuffer = imeiBuffer;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public byte[] getIMEIBuffer() {
        return imeiBuffer;
    }
}
