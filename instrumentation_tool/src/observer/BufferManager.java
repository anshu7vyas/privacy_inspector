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
        IMEIObserver imeiObserver = new IMEIObserver(object);
        imeiObserver.accept(DataInspector.getInstance());

        LocationObserver locationObserver = new LocationObserver(object);
        locationObserver.accept(DataInspector.getInstance());

        ContactObserver contactObserver = new ContactObserver(object);
        contactObserver.accept(DataInspector.getInstance());

    }
}
