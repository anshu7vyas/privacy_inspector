package visitor;

import observer.BufferManager;
import util.Constants;

/**
 * Visitable class for IMEI - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class IMEIObserver implements Visitable {

    private byte[] imeiBuffer;

    private static IMEIObserver imeiObserver = null;

    /**
     * Ensures single instance for IMEIObserver
     * @return instance
     */
    public static IMEIObserver getInstance () {
        if (imeiObserver == null) {
            imeiObserver = new IMEIObserver();
        }
        return imeiObserver;
    }

    /**
     * Assigning the buffer with the number of bytes required
     */
    private IMEIObserver() {
        imeiBuffer = new byte[Constants.SLIDING_WINDOW_SIZE];
    }

    /**
     * Fills the buffer byte per byte
     * @param object
     */
    @Override
    public void updateBuffer(Object object) {
        BufferManager.getInstance(object).globalBuffer.fillBuffer(imeiBuffer);
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
