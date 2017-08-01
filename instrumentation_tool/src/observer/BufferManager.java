package observer;

import util.Constants;
import util.SlidingBuffer;
import visitor.ContactInfo;
import visitor.IMEIInfo;
import visitor.LocationInfo;
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

    public void update() {
        IMEIInfo imeiInfo = new IMEIInfo(SlidingBuffer.getInstance().getCircularBuffer(Constants.IMEI_BYTE_SIZE));
        imeiInfo.accept(SecurityChecker.getInstance());

        LocationInfo locationInfo = new LocationInfo(SlidingBuffer.getInstance().getCircularBuffer(Constants.DOUBLE_BYTE_SIZE));
        locationInfo.accept(SecurityChecker.getInstance());

        ContactInfo contactInfo = new ContactInfo(SlidingBuffer.getInstance().getCircularBuffer(Constants.SLIDING_WINDOW_SIZE));
        contactInfo.accept(SecurityChecker.getInstance());

    }
}
