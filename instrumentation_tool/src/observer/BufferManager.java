package observer;

import util.Constants;
import util.SlidingBuffer;
import visitor.ContactObserver;
import visitor.IMEIObserver;
import visitor.LocationObserver;
import visitor.SecurityChecker;

/**
 * Created by av7 on 7/29/17.
 */
public class BufferManager implements Observer {

    private static BufferManager bufferManager = null;

    private BufferManager() { }

    public static BufferManager getInstance() {
        if (bufferManager == null) {
            bufferManager = new BufferManager();
        }

        return bufferManager;
    }

    public void update(Object object) {
        IMEIObserver imeiObserver = new IMEIObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.IMEI_BYTE_SIZE));
        imeiObserver.accept(SecurityChecker.getInstance());

        LocationObserver locationObserver = new LocationObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.DOUBLE_BYTE_SIZE));
        locationObserver.accept(SecurityChecker.getInstance());

        ContactObserver contactObserver = new ContactObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.SLIDING_WINDOW_SIZE));
        contactObserver.accept(SecurityChecker.getInstance());

    }
}
