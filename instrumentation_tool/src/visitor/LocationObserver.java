package visitor;

import observer.BufferManager;
import util.Constants;
import util.SlidingBuffer;

/**
 * Visitable class for Geolocation - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class LocationObserver implements Visitable {

    private byte[] locationBuffer;

    private static LocationObserver locationObserver = null;

    public static LocationObserver getInstance() {
        if (locationObserver == null) {
            locationObserver = new LocationObserver();
        }
        return locationObserver;
    }

    private LocationObserver() {
        locationBuffer = new byte[Constants.DOUBLE_BYTE_SIZE];
    }

    @Override
    public void updateBuffer(Object object) {
        BufferManager.getInstance(object).slidingBuffer.fillBuffer(locationBuffer);
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
     * @return locationBuffer
     */
    public byte[] getLocationBuffer() {
        return locationBuffer;
    }
}
