package visitor;

/**
 * Visitable class for IMEI - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class IMEIObserver implements Visitable {

    private byte[] imeiBuffer;

    public IMEIObserver(byte[] imeiBuffer) {
        this.imeiBuffer = imeiBuffer;
    }

    /**
     * Accepts the visitor - SecurityChecker
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return imeiBuffer
     */
    public byte[] getIMEIBuffer() {
        return imeiBuffer;
    }
}
