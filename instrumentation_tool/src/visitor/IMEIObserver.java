package visitor;

import observer.BufferManager;
import util.Constants;
import util.SlidingBuffer;

/**
 * Visitable class for IMEI - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class IMEIObserver implements Visitable {

    private byte[] imeiBuffer;

    private static IMEIObserver imeiObserver = null;

    public static IMEIObserver getInstance () {
        if (imeiObserver == null) {
            imeiObserver = new IMEIObserver();
        }
        return imeiObserver;
    }

    private IMEIObserver() {
        imeiBuffer = new byte[Constants.SLIDING_WINDOW_SIZE];

    }

    @Override
    public void updateBuffer(Object object) {
        BufferManager.getInstance(object).slidingBuffer.fillBuffer(imeiBuffer);
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
