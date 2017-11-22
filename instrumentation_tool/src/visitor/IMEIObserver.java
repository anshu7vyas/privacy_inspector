package visitor;

import util.Constants;
import util.SlidingBuffer;

/**
 * Visitable class for IMEI - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class IMEIObserver implements Visitable {

    private byte[] imeiBuffer;

    public IMEIObserver(Object object) {
        imeiBuffer = new byte[Constants.SLIDING_WINDOW_SIZE];
        SlidingBuffer.getInstance(object).fillBuffer(imeiBuffer);
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
     * @return imeiBuffer
     */
    public byte[] getIMEIBuffer() {
        return imeiBuffer;
    }
}
