package visitor;

/**
 * Created by av7 on 7/29/17.
 */
public class IMEIInfo implements Visitable {

    private byte[] imeiBuffer;

    public IMEIInfo(byte[] imeiBuffer) {
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
