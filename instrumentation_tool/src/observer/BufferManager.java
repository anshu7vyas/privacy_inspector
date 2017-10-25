package observer;

import util.Constants;
import util.SlidingBuffer;
import visitor.ContactObserver;
import visitor.DataInspector;
import visitor.IMEIObserver;
import visitor.LocationObserver;

/**
 * A singleton class that manages all the Sliding Buffers required for analysis.
 *
 * @author Anshul Vyas
 */
public class BufferManager implements Observer {

    private static BufferManager bufferManager = null;

    private BufferManager() { }

    /**
     * @return a new instance of the BufferManager
     */
    public static BufferManager getInstance() {
        if (bufferManager == null) {
            bufferManager = new BufferManager();
        }

        return bufferManager;
    }

    /**
     * Overrides the update() method of Observer Interface to get new Sliding buffers for each of 3 analysis,
     * namely, IMEI, Geolocation, Contacts
     *
     * @param object - different objects for different output streams
     */
    @Override
    public void update(Object object) {
        IMEIObserver imeiObserver = new IMEIObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.SLIDING_WINDOW_SIZE));
        imeiObserver.accept(DataInspector.getInstance());

        LocationObserver locationObserver = new LocationObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.DOUBLE_BYTE_SIZE));
        locationObserver.accept(DataInspector.getInstance());

        ContactObserver contactObserver = new ContactObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.CONTACT_INFO_BYTE_SIZE));
        contactObserver.accept(DataInspector.getInstance());

    }
}
