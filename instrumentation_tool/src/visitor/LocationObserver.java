package visitor;

import observer.BufferManager;
import util.Constants;

/**
 * Visitable class for Geolocation - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class LocationObserver implements Visitable {

    private byte[] locationBuffer;

    private static LocationObserver locationObserver = null;

    /**
     * Ensures single instance for LocationObserver
     * @return instance
     */
    public static LocationObserver getInstance() {
        if (locationObserver == null) {
            locationObserver = new LocationObserver();
        }
        return locationObserver;
    }

    /**
     * Assigning the buffer with the number of bytes required
     */
    private LocationObserver() {
        locationBuffer = new byte[Constants.DOUBLE_BYTE_SIZE];
    }

    /**
     * Fills the buffer byte per byte
     * @param object
     */
    @Override
    public void updateBuffer(Object object) {
        BufferManager.getInstance(object).globalBuffer.fillBuffer(locationBuffer);
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
